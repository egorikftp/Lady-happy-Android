package com.egoriku.ladyhappy.presentation.ui.adapter.model;


import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@IntDef({SectionType.CATEGORIES, SectionType.NEWS})
public @interface SectionType {
    int CATEGORIES = 1;
    int NEWS = 2;
}
