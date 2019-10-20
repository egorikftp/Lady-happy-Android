package com.egoriku.ladyhappy.extensions

import android.net.Uri

inline fun String.toUri(): Uri = Uri.parse(this)