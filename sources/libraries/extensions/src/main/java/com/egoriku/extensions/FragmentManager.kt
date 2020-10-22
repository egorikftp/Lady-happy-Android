package com.egoriku.extensions

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner

fun FragmentManager.setFragmentResultListenerWrapper(
        requestKey: String,
        lifecycleOwner: LifecycleOwner,
        listener: ((requestKey: String, bundle: Bundle) -> Unit)
) {
    setFragmentResultListener(requestKey, lifecycleOwner, { s: String, bundle: Bundle ->
        listener.invoke(s, bundle)
    })
}
