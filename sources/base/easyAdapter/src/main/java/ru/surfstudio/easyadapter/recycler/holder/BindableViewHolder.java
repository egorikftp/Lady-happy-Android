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
import android.view.View;
import android.view.ViewGroup;

import ru.surfstudio.easyadapter.recycler.controller.BindableItemController;

/**
 * ViewHolder with rendering one block of data
 * Used with {@link BindableItemController}
 * This holder also has some convenient features, see {@link BaseViewHolder}
 *
 * @param <T> data type
 */
public abstract class BindableViewHolder<T> extends BaseViewHolder {

    public BindableViewHolder(ViewGroup parent, @LayoutRes int layoutRes) {
        super(parent, layoutRes);
    }

    public BindableViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(T data);
}
