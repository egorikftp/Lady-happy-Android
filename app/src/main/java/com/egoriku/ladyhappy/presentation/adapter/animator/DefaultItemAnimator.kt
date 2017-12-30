package com.egoriku.ladyhappy.presentation.adapter.animator

import ru.surfstudio.easyadapter.recycler.animator.BaseItemAnimator

class DefaultItemAnimator : BaseItemAnimator() {

    companion object {
        private const val ADD_DURATION: Long = 200
        private const val REMOVE_DURATION: Long = 350
        private const val MOVE_DURATION: Long = 350
        private const val CHANGE_DURATION: Long = 200
    }

    init {
        addDuration = ADD_DURATION
        removeDuration = REMOVE_DURATION
        moveDuration = MOVE_DURATION
        changeDuration = CHANGE_DURATION
        supportsChangeAnimations = false
    }
}