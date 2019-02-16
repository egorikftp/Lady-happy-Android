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


import ru.surfstudio.android.easyadapter.ItemList;
import ru.surfstudio.android.easyadapter.controller.DoubleBindableItemController;
import ru.surfstudio.android.easyadapter.holder.DoubleBindableViewHolder;

/**
 * Item for {@link ItemList} with two block data
 *
 * @param <T1> type of first data
 * @param <T2> type of second data
 * @param <H>  type of ViewHolder
 */
public class DoubleBindableItem<T1, T2, H extends DoubleBindableViewHolder<T1, T2>> extends BaseItem<H> {
    private T1 firstData;
    private T2 secondData;

    public DoubleBindableItem(T1 firstData, T2 secondData, DoubleBindableItemController<T1, T2, H> itemController) {
        super(itemController);
        this.firstData = firstData;
        this.secondData = secondData;
    }

    public T1 getFirstData() {
        return firstData;
    }

    public T2 getSecondData() {
        return secondData;
    }
}
