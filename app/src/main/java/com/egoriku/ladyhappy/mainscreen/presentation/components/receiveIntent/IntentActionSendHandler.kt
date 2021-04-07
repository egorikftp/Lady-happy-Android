package com.egoriku.ladyhappy.mainscreen.presentation.components.receiveIntent

import android.content.Intent
import android.net.Uri

class IntentActionSendHandler {

    fun extract(intent: Intent, isRestore: Boolean, onResult: (List<Uri>) -> Unit) {
        val isLaunchedFromHistory = intent.flags and Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY == 0

        if (isLaunchedFromHistory && intent.action != null && !isRestore) {
            when (intent.action) {
                Intent.ACTION_SEND -> if (intent.type?.startsWith("image/") == true) {
                    val imageUri = intent.getParcelableExtra<Uri>(Intent.EXTRA_STREAM)

                    check(imageUri != null)

                    onResult(listOf(imageUri))
                }
                Intent.ACTION_SEND_MULTIPLE -> if (intent.type?.startsWith("image/") == true) {
                    val imageUris = intent.getParcelableArrayListExtra<Uri>(Intent.EXTRA_STREAM)

                    check(imageUris != null)

                    onResult(imageUris)
                }
            }
        }
    }
}