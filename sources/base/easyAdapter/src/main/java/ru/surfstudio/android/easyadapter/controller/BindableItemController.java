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
package ru.surfstudio.android.easyadapter.controller;

import ru.surfstudio.android.easyadapter.EasyAdapter;
import ru.surfstudio.android.easyadapter.ItemList;
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder;
import ru.surfstudio.android.easyadapter.item.BindableItem;

/**
 * Controller for item of RecyclerView with one block of data {@link BindableItem}.
 * It used with {@link EasyAdapter} and {@link ItemList}
 *
 * @param <H> type of ViewHolder
 * @param <T> type of data
 */
public abstract class BindableItemController<T, H extends BindableViewHolder<T>>
        extends BaseItemController<H, BindableItem<T, H>> {

    @Override
    public final void bind(H holder, BindableItem<T, H> item) {
        bind(holder, item.getData());
    }

    public void bind(H holder, T data) {
        holder.bind(data);
    }

    @Override
    public final String getItemId(BindableItem<T, H> item) {
        return getItemId(item.getData());
    }

    @Override
    public final String getItemHash(BindableItem<T, H> item) {
        return getItemHash(item.getData());
    }

    /**
     * must return unique value
     * method is used for automatically call notify... methods, see {@link EasyAdapter}
     *
     * @return item id
     */
    protected abstract String getItemId(T data);

    /**
     * method is used for automatically call notify... methods, see {@link EasyAdapter}
     *
     * @return hash of data
     */
    protected String getItemHash(T data) {
        return String.valueOf(data == null ? 0 : data.hashCode());
    }
}
