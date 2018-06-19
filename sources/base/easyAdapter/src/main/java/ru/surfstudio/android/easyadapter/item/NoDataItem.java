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
package ru.surfstudio.android.easyadapter.item;


import android.support.v7.widget.RecyclerView;

import ru.surfstudio.android.easyadapter.ItemList;
import ru.surfstudio.android.easyadapter.controller.NoDataItemController;


/**
 * Item for {@link ItemList} without data
 *
 * @param <H> type of ViewHolder
 */
public final class NoDataItem<H extends RecyclerView.ViewHolder>
        extends BaseItem<H> {

    public NoDataItem(NoDataItemController<H> itemController) {
        super(itemController);
    }
}
