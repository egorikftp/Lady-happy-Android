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
package ru.surfstudio.android.easyadapter.animator;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;

import ru.surfstudio.android.easyadapter.holder.BaseViewHolder;


/**
 * ItemAnimator with support custom animations for View Holders, see {@link BaseViewHolder}
 */
public class BaseItemAnimator extends DefaultItemAnimator {

    @Override
    public final void onRemoveStarting(RecyclerView.ViewHolder item) {
        super.onRemoveStarting(item);
        if (!(item instanceof BaseViewHolder) || !((BaseViewHolder) item).animateRemove()) {
            onRemoveStartingInternal(item);
        }
    }

    @Override
    public final void onAddStarting(RecyclerView.ViewHolder item) {
        super.onAddStarting(item);
        if (!(item instanceof BaseViewHolder) || !((BaseViewHolder) item).animateInsert()) {
            onAddStartingInternal(item);
        }
    }

    @Override
    public final void onChangeStarting(RecyclerView.ViewHolder item, boolean oldItem) {
        super.onChangeStarting(item, oldItem);
        if (!(item instanceof BaseViewHolder) || !((BaseViewHolder) item).animateChange()) {
            onChangeStartingInternal(item);
        }
    }

    protected void onRemoveStartingInternal(RecyclerView.ViewHolder item) {
        //empty
    }

    protected void onAddStartingInternal(RecyclerView.ViewHolder item) {
        //empty
    }

    protected void onChangeStartingInternal(RecyclerView.ViewHolder item) {
        //empty
    }
}
