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
package ru.surfstudio.easyadapter.recycler.controller;


import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import ru.surfstudio.easyadapter.recycler.EasyAdapter;
import ru.surfstudio.easyadapter.recycler.ItemList;
import ru.surfstudio.easyadapter.recycler.item.BaseItem;

/**
 * Base Controller for item of RecyclerView. It used with {@link EasyAdapter} and {@link ItemList}
 * It responsible for interaction with item.
 * @param <H> type of ViewHolder
 * @param <I> type of Item
 */
public abstract class BaseItemController<H extends RecyclerView.ViewHolder, I extends BaseItem> {
    public static final long NO_ID = RecyclerView.NO_ID;

    public abstract void bind(H holder, I item);

    public abstract H createViewHolder(ViewGroup parent);

    public int viewType() {
        return getClass().getCanonicalName().hashCode();
    }

    /**
     * must return unique value
     * method is used for automatically call notify... methods, see {@link EasyAdapter}
     * @param item
     * @return item id
     */
    public long getItemId(I item){
        return NO_ID;
    }

    /**
     * method is used for automatically call notify... methods, see {@link EasyAdapter}
     * @param item
     * @return hash of item's data
     */
    public abstract long getItemHash(I item);
}
