package com.egoriku.ladyhappy.leakcanary

import leakcanary.LeakCanary

class LeakCanaryInitializer {

    fun init() {
        LeakCanary.config = LeakCanary.config.copy(
            dumpHeap = false
        )
    }
}