package com.egoriku.ladyhappy.settings.presentation.viewmodel

import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.play.core.ktx.requestReview
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager
import kotlinx.coroutines.*

class ReviewViewModel(private val reviewManager: ReviewManager) : ViewModel() {

    private var reviewInfo: Deferred<ReviewInfo>? = null

    @MainThread
    fun preWarmReview() {
        if (reviewInfo == null) {
            reviewInfo = viewModelScope.async {
                reviewManager.requestReview()
            }
        }
    }

    fun startReview(callback: (reviewInfo: ReviewInfo, reviewManager: ReviewManager) -> Unit) {
        viewModelScope.launch {
            val reviewInfo = obtainReviewInfo()

            if (reviewInfo != null) {
                callback.invoke(reviewInfo, reviewManager)
            }
        }
    }

    private suspend fun obtainReviewInfo(): ReviewInfo? = withContext(Dispatchers.Main.immediate) {
        if (reviewInfo?.isCompleted == true && reviewInfo?.isCancelled == false) {
            reviewInfo?.getCompleted().also {
                reviewInfo = null
            }
        } else null
    }
}