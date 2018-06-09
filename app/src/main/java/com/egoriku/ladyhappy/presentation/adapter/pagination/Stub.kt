package com.egoriku.ladyhappy.presentation.adapter.pagination

import java.util.*

data class Stub(val id: Long = rnd.nextLong()) {
    companion object {
        val rnd = Random()
    }
}

fun generateStubs(count: Int): List<Stub> {
    return (0 until count)
            .map { Stub() }
            .toList()
}