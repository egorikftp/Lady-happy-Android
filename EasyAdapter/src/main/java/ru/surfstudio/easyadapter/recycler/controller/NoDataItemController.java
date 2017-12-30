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

import ru.surfstudio.easyadapter.recycler.EasyAdapter;
import ru.surfstudio.easyadapter.recycler.ItemList;
import ru.surfstudio.easyadapter.recycler.item.NoDataItem;

/**
 * Controller for item of RecyclerView without data {@link NoDataItem}
 * It used with {@link EasyAdapter} and {@link ItemList}
 *
 * @param <H> type of ViewHolder
 */
public abstract class NoDataItemController<H extends RecyclerView.ViewHolder>
        extends BaseItemController<H, NoDataItem<H>> {

    @Override
    public long getItemId(NoDataItem<H> item) {
        return getClass().getCanonicalName().hashCode();
    }

    @Override
    public void bind(H holder, NoDataItem<H> item) {
        //empty
    }

    @Override
    public long getItemHash(NoDataItem<H> item) {
        return 0;
    }
}
