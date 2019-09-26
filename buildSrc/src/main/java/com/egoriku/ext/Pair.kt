package com.egoriku.ext

infix fun <A, B> A.andKapt(that: B): Pair<A, B> = Pair(this, that)