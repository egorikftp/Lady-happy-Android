/*
 * Copyright 2016 Maxim Tuev.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ru.surfstudio.easyadapter.recycler.holder;


import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.surfstudio.easyadapter.recycler.animator.BaseItemAnimator;

/**
 * Base ViewHolder with convenient features:
 * 1) has constructor with item layout resource id
 * 2) support custom animation, when used with {@link BaseItemAnimator}
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(ViewGroup parent, @LayoutRes int layoutRes) {
        super(LayoutInflater.from(parent.getContext()).inflate(layoutRes, parent, false));
    }

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    /**
     * Override this method, if you want has custom animation "insert" for this holder
     *
     * @return true, if holder has custom animation
     */
    public boolean animateInsert() {
        return false;
    }

    /**
     * Override this method, if you want has custom animation "change" for this holder
     *
     * @return true, if holder has custom animation
     */
    public boolean animateChange() {
        return false;
    }

    /**
     * Override this method, if you want has custom animation "remove" for this holder
     *
     * @return true, if holder has custom animation
     */
    public boolean animateRemove() {
        return false;
    }
}
