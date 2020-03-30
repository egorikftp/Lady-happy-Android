package com.egoriku.ladyhappy.login.presentation.util

import android.text.NoCopySpan
import android.text.style.ClickableSpan

//https://stackoverflow.com/questions/28539216/android-textview-leaks-with-setmovementmethod
abstract class ClickableSpan : ClickableSpan(), NoCopySpan