package com.egoriku.ladyhappy.mainscreen.presentation.components.inAppReview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egoriku.ladyhappy.core.IAppPreferences
import com.google.android.play.core.ktx.requestReview
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager
import kotlinx.coroutines.launch
import kotlin.time.ExperimentalTime
import kotlin.time.days

class ReviewViewModel(
    private val reviewManager: ReviewManager,
    private val appPreferences: IAppPreferences
) : ViewModel() {

    fun submitReview(callback: (reviewInfo: ReviewInfo, reviewManager: ReviewManager) -> Unit) {
        if (appPreferences.launchCount > 5 && isLastRequestOld()) {
            appPreferences.lastAskForReview = System.currentTimeMillis()

            viewModelScope.launch {
                val reviewInfo = reviewManager.requestReview()

                callback.invoke(reviewInfo, reviewManager)
            }
        }
    }

    @OptIn(ExperimentalTime::class)
    private fun isLastRequestOld(): Boolean {
        val lastAskForReview = appPreferences.lastAskForReview

        return lastAskForReview == 0L ||
                System.currentTimeMillis() - lastAskForReview >= 7.days.inMilliseconds
    }
}