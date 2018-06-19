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


import ru.surfstudio.easyadapter.recycler.EasyAdapter;
import ru.surfstudio.easyadapter.recycler.ItemList;
import ru.surfstudio.easyadapter.recycler.holder.DoubleBindableViewHolder;
import ru.surfstudio.easyadapter.recycler.item.DoubleBindableItem;

/**
 * Controller for item of RecyclerView with two block of data {@link DoubleBindableItem}
 * It used with {@link EasyAdapter} and {@link ItemList}
 *
 * @param <H>  type of ViewHolder
 * @param <T1> first data type
 * @param <T2> second data type
 */
public abstract class DoubleBindableItemController<T1, T2, H extends DoubleBindableViewHolder<T1, T2>>
        extends BaseItemController<H, DoubleBindableItem<T1, T2, H>> {

    @Override
    public final void bind(H holder, DoubleBindableItem<T1, T2, H> item) {
        bind(holder, item.getFirstData(), item.getSecondData());
    }

    public void bind(H holder, T1 firstData, T2 secondData) {
        holder.bind(firstData, secondData);
    }

    @Override
    public final long getItemId(DoubleBindableItem<T1, T2, H> item) {
        return getItemId(item.getFirstData(), item.getSecondData());
    }

    @Override
    public final long getItemHash(DoubleBindableItem<T1, T2, H> item) {
        return getItemHash(item.getFirstData(), item.getSecondData());
    }

    /**
     * must return unique value
     * method is used for automatically call notify... methods, see {@link EasyAdapter}
     * @return item id
     */
    protected abstract long getItemId(T1 firstData, T2 secondData);

    /**
     * method is used for automatically call notify... methods, see {@link EasyAdapter}
     * @return hash of item's data
     */
    protected long getItemHash(T1 firstData, T2 secondData) {
        return (firstData == null ? 0 : firstData.hashCode())
                + (secondData == null ? 0 : secondData.hashCode());
    }
}
