package com.egoriku.ladyhappy.mainscreen.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.manager.SupportRequestManagerFragment
import com.egoriku.ladyhappy.R
import com.egoriku.ladyhappy.core.IFeatureProvider
import com.egoriku.ladyhappy.core.INavigationHolder
import com.egoriku.ladyhappy.core.feature.*
import com.egoriku.ladyhappy.core.sharedmodel.key.DYNAMIC_FEATURE_BUNDLE_RESULT_KEY
import com.egoriku.ladyhappy.core.sharedmodel.key.DYNAMIC_FEATURE_REQUEST_KEY
import com.egoriku.ladyhappy.core.sharedmodel.key.FULL_PERCENT
import com.egoriku.ladyhappy.core.sharedmodel.params.PostCreatorParams
import com.egoriku.ladyhappy.core.sharedmodel.toNightMode
import com.egoriku.ladyhappy.databinding.ActivityMainBinding
import com.egoriku.ladyhappy.extensions.*
import com.egoriku.ladyhappy.mainscreen.common.Constants.Tracking
import com.egoriku.ladyhappy.mainscreen.presentation.components.dynamicDelivery.DynamicFeatureEvent
import com.egoriku.ladyhappy.mainscreen.presentation.components.dynamicDelivery.DynamicFeatureViewModel
import com.egoriku.ladyhappy.mainscreen.presentation.components.dynamicDelivery.DynamicScreen
import com.egoriku.ladyhappy.mainscreen.presentation.components.inAppReview.ReviewViewModel
import com.egoriku.ladyhappy.mainscreen.presentation.components.inAppUpdates.InAppUpdateEvent
import com.egoriku.ladyhappy.mainscreen.presentation.components.inAppUpdates.InAppUpdateViewModel
import com.egoriku.ladyhappy.mainscreen.presentation.components.receiveIntent.IntentActionSendHandler
import com.egoriku.ladyhappy.mainscreen.presentation.deeplink.AssistanceDeepLinkParser
import com.egoriku.ladyhappy.mainscreen.presentation.screen.*
import com.egoriku.ladyhappy.mainscreen.presentation.screen.params.DeepLinkScreen
import com.egoriku.ladyhappy.mainscreen.presentation.screen.params.ScreenParams
import com.egoriku.ladyhappy.navigation.navigator.platform.ActivityScopeNavigator
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.install.model.ActivityResult
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.ktx.AppUpdateResult
import com.google.android.play.core.ktx.bytesDownloaded
import com.google.android.play.core.ktx.totalBytesToDownload
import com.google.android.play.core.splitcompat.SplitCompat
import com.google.android.play.core.splitinstall.SplitInstallManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.scope.ScopeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

private const val INSTALL_CONFIRMATION_REQ_CODE = 1
private const val UPDATE_CONFIRMATION_REQ_CODE = 2
private const val KEY_SELECTED_MENU_ITEM = "selected_item"

class MainActivity : ScopeActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind, R.id.contentFullScreen)

    private val appUpdateManager: AppUpdateManager by inject()
    private val splitInstallManager: SplitInstallManager by inject()
    private val featureProvider: IFeatureProvider by inject()
    private val navigatorHolder: INavigationHolder by inject()

    private val dynamicFeatureViewModel by viewModel<DynamicFeatureViewModel>()
    private val inAppUpdateViewModel by viewModel<InAppUpdateViewModel>()
    private val reviewViewModel by viewModel<ReviewViewModel>()
    private val viewModel by viewModel<MainActivityViewModel>(state = { bundleOf() })

    private val navigator = ActivityScopeNavigator(
        activity = this,
        containerId = R.id.container,
        fullScreenContainerId = R.id.contentFullScreen
    )

    private var snackBar: Snackbar by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (!hasM()) {
            window.statusBarColor = Color.BLACK
        }

        setSupportActionBar(binding.toolbarMainActivity)

        snackBar = Snackbar.make(
            binding.bottomNavigation,
            R.string.in_app_update_available,
            Snackbar.LENGTH_INDEFINITE
        ).apply {
            anchorView = binding.bottomNavigation
        }

        viewModel.screenTitle.observe(this) {
            binding.toolbarContent.headerBarLogoText.setText(it)
        }

        when (savedInstanceState) {
            null -> viewModel.replaceWith(
                screen = CatalogScreen(featureProvider),
                params = ScreenParams(
                    screenNameResId = R.string.navigation_view_catalog_header,
                    trackingScreenName = Tracking.TRACKING_FRAGMENT_CATALOG
                )
            )
            else -> with(savedInstanceState.getInt(KEY_SELECTED_MENU_ITEM)) {
                binding.bottomNavigation.selectedItemId = this
            }
        }

        with(binding.bottomNavigation) {
            setOnItemSelectedListener { item ->
                consume {
                    mapItemIdToScreen(item.itemId)
                }
            }

            setOnItemReselectedListener {}
        }

        viewModel.theme.observe(this) {
            AppCompatDelegate.setDefaultNightMode(it.toNightMode())
        }

        reviewViewModel.submitReview { reviewInfo, reviewManager ->
            reviewManager.launchReviewFlow(this, reviewInfo)
        }

        expandAppBarLayoutInPage()

        subscribeForInAppUpdate()
        subscribeForDynamicFeatureEvents()

        subscribeForDynamicFeatureRequest()

        intent?.handleGoogleAssistanceSearchIntent()
        intent?.handleSendImageIntent(isRestore = savedInstanceState != null)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.handleGoogleAssistanceSearchIntent()
        intent?.handleSendImageIntent(isRestore = false)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            UPDATE_CONFIRMATION_REQ_CODE -> when (resultCode) {
                Activity.RESULT_OK -> viewModel.trackInAppUpdateSuccess()
                Activity.RESULT_CANCELED -> viewModel.trackInAppUpdateCanceled()
                ActivityResult.RESULT_IN_APP_UPDATE_FAILED -> viewModel.trackInAppUpdateFailed()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEY_SELECTED_MENU_ITEM, binding.bottomNavigation.selectedItemId)
        super.onSaveInstanceState(outState)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        SplitCompat.install(this)
    }

    private fun subscribeForInAppUpdate() {
        lifecycleScope.launch {
            inAppUpdateViewModel.updateStatus.collect { updateResult: AppUpdateResult ->
                updateUpdateButton(updateResult)

                // If it's an immediate update, launch it immediately and finish Activity
                // to prevent the user from using the app until they update.
                when (updateResult) {
                    is AppUpdateResult.Available -> handleImmediateUpdate(updateResult)
                }
            }
        }

        lifecycleScope.launch {
            inAppUpdateViewModel.events.collect { event ->
                when (event) {
                    is InAppUpdateEvent.ToastEvent -> toast(event.message)
                    is InAppUpdateEvent.StartUpdateEvent -> {
                        val updateType = when {
                            event.immediate -> AppUpdateType.IMMEDIATE
                            else -> AppUpdateType.FLEXIBLE
                        }
                        appUpdateManager.startUpdateFlowForResult(
                            event.updateInfo,
                            updateType,
                            this@MainActivity,
                            UPDATE_CONFIRMATION_REQ_CODE
                        )
                    }
                }
            }
        }
    }

    private fun handleImmediateUpdate(updateResult: AppUpdateResult.Available) {
        if (inAppUpdateViewModel.shouldLaunchImmediateUpdate(updateResult.updateInfo)) {
            val isStartUpdateFlowForResultSuccess = appUpdateManager.startUpdateFlowForResult(
                updateResult.updateInfo,
                AppUpdateType.IMMEDIATE,
                this@MainActivity,
                UPDATE_CONFIRMATION_REQ_CODE
            )
            if (isStartUpdateFlowForResultSuccess) {
                finish()
            }
        }
    }

    @Suppress("OptionalWhenBraces")
    private fun subscribeForDynamicFeatureEvents() {
        Log.d("kek", "subscribeForDynamicFeatureEvents: ")
        lifecycleScope.launch {
            dynamicFeatureViewModel.events.collect { event ->
                when (event) {
                    is DynamicFeatureEvent.NavigationEvent -> {
                        Log.d("kek", "subscribeForDynamicFeatureEvents: NavigationEvent")
                        binding.dynamicFeatureStatusContainer.gone()

                        when (val screen = event.screen) {
                            is DynamicScreen.PostCreator -> {
                                viewModel.navigateTo(
                                    screen = PostCreatorScreen(
                                        className = screen.className,
                                        params = screen.params
                                    )
                                )
                            }
                            is DynamicScreen.Edit -> {
                                viewModel.navigateTo(
                                    screen = EditScreen(
                                        className = screen.className,
                                        params = screen.editParams
                                    )
                                )
                            }
                        }
                    }
                    is DynamicFeatureEvent.InstallConfirmationEvent ->
                        splitInstallManager.startConfirmationDialogForResult(
                            event.status,
                            this@MainActivity,
                            INSTALL_CONFIRMATION_REQ_CODE
                        )
                    is DynamicFeatureEvent.Downloading -> {
                        Log.d("kek", "subscribeForDynamicFeatureEvents: Downloading")

                        binding.dynamicFeatureStatusContainer.visible()
                        binding.dynamicFeatureStatus.text =
                            getString(R.string.dynamic_delivery_downloading)
                        binding.dynamicFeatureProgress.setProgressCompat(event.progress, true)
                    }
                    is DynamicFeatureEvent.Installing -> {
                        Log.d("kek", "subscribeForDynamicFeatureEvents: Installing")
                        binding.dynamicFeatureStatus.text =
                            getString(R.string.dynamic_delivery_installing)
                    }
                    is DynamicFeatureEvent.InstallErrorEvent -> {
                        binding.dynamicFeatureStatusContainer.gone()

                        toast(getString(R.string.dynamic_delivery_feature_not_available))
                    }
                }
            }
        }
    }

    private fun subscribeForDynamicFeatureRequest() {
        supportFragmentManager.setFragmentResultListenerWrapper(
            requestKey = DYNAMIC_FEATURE_REQUEST_KEY,
            lifecycleOwner = this
        ) { _, bundle ->
            when (val feature =
                bundle.getParcelable<DynamicFeature>(DYNAMIC_FEATURE_BUNDLE_RESULT_KEY)) {
                is DynamicFeature.PostCreator -> dynamicFeatureViewModel.invokePostCreator(feature.postCreatorParams)
                is DynamicFeature.Edit -> dynamicFeatureViewModel.invokeEdit(feature.editParams)
            }
        }
    }

    private fun Intent.handleGoogleAssistanceSearchIntent() {
        AssistanceDeepLinkParser().process(intent = this) { featureScreen ->
            when (featureScreen) {
                is DeepLinkScreen.Search -> viewModel.replaceWith(
                    screen = SearchScreen(featureProvider, featureScreen.searchQuery),
                    params = ScreenParams(
                        screenNameResId = R.string.navigation_view_search_header,
                        trackingScreenName = Tracking.TRACKING_FRAGMENT_SEARCH
                    )
                )
                is DeepLinkScreen.Catalog -> preselectAndNavigate(R.id.menuCatalog)
                is DeepLinkScreen.About -> preselectAndNavigate(R.id.menuLanding)
                is DeepLinkScreen.News -> preselectAndNavigate(R.id.menuPhotoReport)
                is DeepLinkScreen.Settings -> preselectAndNavigate(R.id.menuSettings)
                is DeepLinkScreen.Unknown -> toast(getString(R.string.deeplink_unknown_screen))
            }
        }
    }

    private fun Intent.handleSendImageIntent(isRestore: Boolean) {
        IntentActionSendHandler().extract(intent = this, isRestore = isRestore) {
            dynamicFeatureViewModel.invokePostCreator(
                postCreatorParams = PostCreatorParams(images = it)
            )
        }
    }

    private fun preselectAndNavigate(@IdRes menuItemId: Int) {
        binding.bottomNavigation.selectedItemId = menuItemId
        mapItemIdToScreen(menuItemId)
    }

    private fun mapItemIdToScreen(@IdRes menuItemId: Int) {
        when (menuItemId) {
            R.id.menuLanding -> viewModel.replaceWith(
                screen = LandingScreen(featureProvider),
                params = ScreenParams(
                    screenNameResId = R.string.navigation_view_landing_header,
                    trackingScreenName = Tracking.TRACKING_FRAGMENT_LANDING
                )
            )
            R.id.menuPhotoReport -> viewModel.replaceWith(
                screen = PhotoReportScreen(featureProvider),
                params = ScreenParams(
                    screenNameResId = R.string.navigation_view_photo_report_header,
                    trackingScreenName = Tracking.TRACKING_FRAGMENT_PHOTO_REPORT
                )
            )
            R.id.menuCatalog -> viewModel.replaceWith(
                screen = CatalogScreen(featureProvider),
                params = ScreenParams(
                    screenNameResId = R.string.navigation_view_catalog_header,
                    trackingScreenName = Tracking.TRACKING_FRAGMENT_CATALOG
                )
            )
            R.id.menuSettings -> viewModel.replaceWith(
                screen = SettingsScreen(featureProvider),
                params = ScreenParams(
                    screenNameResId = R.string.navigation_view_settings_header,
                    trackingScreenName = Tracking.TRACKING_FRAGMENT_SETTINGS
                )
            )
        }
    }

    private fun expandAppBarLayoutInPage() {
        supportFragmentManager.addOnBackStackChangedListener {
            val fragments = supportFragmentManager.fragments
                .filterNot { it is SupportRequestManagerFragment }

            if (fragments.isNotEmpty()) {
                changeAppBarState(fragments.last())
            }
        }

        supportFragmentManager.addFragmentOnAttachListener { _, fragment ->
            changeAppBarState(fragment)
        }
    }

    private fun changeAppBarState(fragment: Fragment) {
        when (fragment) {
            is CatalogFeature,
            is AboutUsFeature,
            is PhotoReportsFeature,
            is SettingsFeature -> with(binding) {
                appBarLayout.setExpanded(true)
            }
        }
    }

    private fun updateUpdateButton(updateResult: AppUpdateResult) {
        when (updateResult) {
            AppUpdateResult.NotAvailable -> {
                logD("No update available")
                snackBar.dismiss()
            }
            is AppUpdateResult.Available -> with(snackBar) {
                setText(R.string.in_app_update_available)
                setAction(R.string.in_app_update_now) {
                    inAppUpdateViewModel.invokeUpdate()
                }
                show()
            }
            is AppUpdateResult.InProgress -> with(snackBar) {
                val updateProgress: Int = calculateUpdateProgress(updateResult)

                setText(getString(R.string.in_app_update_downloading, updateProgress))
                setAction(null) {}
                show()
            }
            is AppUpdateResult.Downloaded -> with(snackBar) {
                setText(R.string.in_app_update_download_finished)
                setAction(R.string.in_app_update_complete) {
                    inAppUpdateViewModel.invokeUpdate()
                }
                show()
            }
        }
    }

    private fun calculateUpdateProgress(updateResult: AppUpdateResult.InProgress): Int {
        val installState = updateResult.installState

        return when (installState.totalBytesToDownload) {
            0L -> 0
            else -> (installState.bytesDownloaded / installState.totalBytesToDownload * FULL_PERCENT).toInt()
        }
    }
}