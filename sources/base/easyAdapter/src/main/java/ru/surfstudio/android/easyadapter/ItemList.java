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
package ru.surfstudio.android.easyadapter;


import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;

import ru.surfstudio.android.easyadapter.controller.BindableItemController;
import ru.surfstudio.android.easyadapter.controller.DoubleBindableItemController;
import ru.surfstudio.android.easyadapter.controller.NoDataItemController;
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder;
import ru.surfstudio.android.easyadapter.item.BaseItem;
import ru.surfstudio.android.easyadapter.item.BindableItem;
import ru.surfstudio.android.easyadapter.item.DoubleBindableItem;
import ru.surfstudio.android.easyadapter.item.NoDataItem;


/**
 * List of items for RecyclerView, used with {@link EasyAdapter}
 */
public class ItemList extends ArrayList<BaseItem> {

    public interface BindableItemControllerProvider<T> {
        BindableItemController<T, ? extends BindableViewHolder<T>> provide(T data);
    }

    public ItemList(int initialCapacity) {
        super(initialCapacity);
    }

    public ItemList(Collection<? extends BaseItem> items) {
        super(items);
    }

    public ItemList() {
    }

    public static ItemList create() {
        return new ItemList();
    }

    public static ItemList create(Collection<BaseItem> items) {
        return new ItemList(items);
    }

    public static <T> ItemList create(Collection<T> data,
                                      final BindableItemController<T, ? extends RecyclerView.ViewHolder> itemController) {
        return create(data, new BindableItemControllerProvider<T>() {
            @Override
            public BindableItemController<T, ? extends BindableViewHolder<T>> provide(T data) {
                return itemController;
            }
        });
    }

    public static <T> ItemList create(Collection<T> data,
                                      BindableItemControllerProvider<T> itemControllerProvider) {
        ItemList items = new ItemList(data.size());
        for (T dataItem : data) {
            items.addItem(new BindableItem<>(dataItem, itemControllerProvider.provide(dataItem)));
        }
        return items;
    }

    public static ItemList create(NoDataItemController<? extends RecyclerView.ViewHolder> itemController) {
        return create().add(itemController);
    }

    //single insert

    public ItemList insert(int index, BaseItem item) {
        this.add(index, item);
        return this;
    }

    public ItemList insertIf(boolean condition,
                             int index,
                             BaseItem baseItem) {
        return condition ? insert(index, baseItem) : this;
    }

    public <T> ItemList insert(int index,
                               T data,
                               BindableItemController<T, ? extends RecyclerView.ViewHolder> itemController) {
        return insert(index, new BindableItem<>(data, itemController));
    }

    public <T> ItemList insertIf(boolean condition,
                                 int index,
                                 T data,
                                 BindableItemController<T, ? extends RecyclerView.ViewHolder> itemController) {
        return insertIf(condition, index, new BindableItem<>(data, itemController));
    }

    public <T1, T2> ItemList insert(int index,
                                    T1 firstData,
                                    T2 secondData,
                                    DoubleBindableItemController<T1, T2, ? extends RecyclerView.ViewHolder> itemController) {
        return insert(index, new DoubleBindableItem<>(firstData, secondData, itemController));
    }

    public <T1, T2> ItemList insertIf(boolean condition,
                                      int index,
                                      T1 firstData,
                                      T2 secondData,
                                      DoubleBindableItemController<T1, T2, ? extends RecyclerView.ViewHolder> itemController) {
        return insertIf(condition, index, new DoubleBindableItem<>(firstData, secondData, itemController));
    }

    public ItemList insert(int index,
                           NoDataItemController<? extends RecyclerView.ViewHolder> itemController) {
        return this.insert(index, new NoDataItem<>(itemController));
    }

    public ItemList insertIf(boolean condition,
                             int index,
                             NoDataItemController<? extends RecyclerView.ViewHolder> itemController) {
        return insertIf(condition, index, new NoDataItem<>(itemController));
    }

    //single add

    public ItemList addItem(BaseItem item) {
        return insert(this.size(), item);
    }

    public ItemList addIf(boolean condition,
                          BaseItem item) {
        return condition ? addItem(item) : this;
    }

    public <T> ItemList add(T data,
                            BindableItemController<T, ? extends RecyclerView.ViewHolder> itemController) {
        return addItem(new BindableItem<>(data, itemController));
    }

    public <T> ItemList addIf(boolean condition,
                              T data,
                              BindableItemController<T, ? extends RecyclerView.ViewHolder> itemController) {
        return addIf(condition, new BindableItem<>(data, itemController));
    }

    public <T1, T2> ItemList add(T1 firstData,
                                 T2 secondData,
                                 DoubleBindableItemController<T1, T2, ? extends RecyclerView.ViewHolder> itemController) {
        return addItem(new DoubleBindableItem<>(firstData, secondData, itemController));
    }

    public <T1, T2> ItemList addIf(boolean condition,
                                   T1 firstData,
                                   T2 secondData,
                                   DoubleBindableItemController<T1, T2, ? extends RecyclerView.ViewHolder> itemController) {
        return addIf(condition, new DoubleBindableItem<>(firstData, secondData, itemController));
    }

    public ItemList add(NoDataItemController<? extends RecyclerView.ViewHolder> itemController) {
        return addItem(new NoDataItem<>(itemController));
    }

    public ItemList addIf(boolean condition,
                          NoDataItemController<? extends RecyclerView.ViewHolder> itemController) {
        return addIf(condition, new NoDataItem<>(itemController));
    }

    //collection insert

    public ItemList insertAll(int index,
                              Collection<BaseItem> items) {
        this.addAll(index, items);
        return this;
    }

    public ItemList addAllItems(Collection<BaseItem> items) {
        return insertAll(this.size(), items);
    }

    public ItemList insertAllIf(boolean condition,
                                int index,
                                Collection<BaseItem> items) {
        return condition ? insertAll(index, items) : this;
    }

    public ItemList addAllIf(boolean condition, Collection<BaseItem> items) {
        return insertAllIf(condition, this.size(), items);
    }

    public <T> ItemList insertAll(int index,
                                  Collection<T> data,
                                  final BindableItemController<T, ? extends RecyclerView.ViewHolder> itemController) {
        return insertAll(index, data, new BindableItemControllerProvider<T>() {
            @Override
            public BindableItemController<T, ? extends BindableViewHolder<T>> provide(T data) {
                return itemController;
            }
        });
    }

    public <T> ItemList addAll(Collection<T> data,
                               BindableItemController<T, ? extends RecyclerView.ViewHolder> itemController) {
        return insertAll(this.size(), data, itemController);
    }

    public <T> ItemList insertAllIf(boolean condition,
                                    int index,
                                    Collection<T> data,
                                    final BindableItemController<T, ? extends RecyclerView.ViewHolder> itemController) {
        return insertAllIf(condition, index, data, new BindableItemControllerProvider<T>() {
            @Override
            public BindableItemController<T, ? extends BindableViewHolder<T>> provide(T data) {
                return itemController;
            }
        });
    }

    public <T> ItemList addAllIf(boolean condition,
                                 Collection<T> data,
                                 BindableItemController<T, ? extends RecyclerView.ViewHolder> itemController) {
        return insertAllIf(condition, this.size(), data, itemController);
    }

    public <T> ItemList insertAll(int index,
                                  Collection<T> data,
                                  BindableItemControllerProvider<T> itemControllerProvider) {
        return insertAll(index, create(data, itemControllerProvider));
    }

    public <T> ItemList addAll(Collection<T> data,
                               BindableItemControllerProvider<T> itemControllerProvider) {
        return insertAll(this.size(), data, itemControllerProvider);
    }

    public <T> ItemList insertAllIf(boolean condition,
                                    int index,
                                    Collection<T> data,
                                    BindableItemControllerProvider<T> itemControllerProvider) {
        return insertAllIf(condition, index, create(data, itemControllerProvider));
    }

    public <T> ItemList addAllIf(boolean condition,
                                 Collection<T> data,
                                 BindableItemControllerProvider<T> itemControllerProvider) {
        return insertAllIf(condition, this.size(), data, itemControllerProvider);
    }

    //add headers

    public ItemList addHeader(BaseItem item) {
        return insert(0, item);
    }

    public ItemList addHeaderIf(boolean condition, BaseItem item) {
        return condition ? addHeader(item) : this;
    }

    public <T> ItemList addHeader(T data,
                                  BindableItemController<T, ? extends RecyclerView.ViewHolder> itemController) {
        return addHeader(new BindableItem<>(data, itemController));
    }

    public <T> ItemList addHeaderIf(boolean condition,
                                    T data,
                                    BindableItemController<T, ? extends RecyclerView.ViewHolder> itemController) {
        return addHeaderIf(condition, new BindableItem<>(data, itemController));
    }

    public <T1, T2> ItemList addHeader(T1 firstData,
                                       T2 secondData,
                                       DoubleBindableItemController<T1, T2, ? extends RecyclerView.ViewHolder> itemController) {
        return addHeader(new DoubleBindableItem<>(firstData, secondData, itemController));
    }

    public <T1, T2> ItemList addHeaderIf(boolean condition,
                                         T1 firstData,
                                         T2 secondData,
                                         DoubleBindableItemController<T1, T2, ? extends RecyclerView.ViewHolder> itemController) {
        return addHeaderIf(condition, new DoubleBindableItem<>(firstData, secondData, itemController));
    }

    public ItemList addHeader(NoDataItemController<? extends RecyclerView.ViewHolder> itemController) {
        return addHeader(new NoDataItem<>(itemController));
    }

    public ItemList addHeaderIf(boolean condition,
                                NoDataItemController<? extends RecyclerView.ViewHolder> itemController) {
        return addHeaderIf(condition, new NoDataItem<>(itemController));
    }

    // add footer

    public ItemList addFooter(BaseItem item) {
        return insert(this.size(), item);
    }

    public ItemList addFooterIf(boolean condition, BaseItem item) {
        return condition ? addFooter(item) : this;
    }

    public <T> ItemList addFooter(T data,
                                  BindableItemController<T, ? extends RecyclerView.ViewHolder> itemController) {
        return addFooter(new BindableItem<>(data, itemController));
    }

    public <T> ItemList addFooterIf(boolean condition,
                                    T data,
                                    BindableItemController<T, ? extends RecyclerView.ViewHolder> itemController) {
        return condition ? addFooter(data, itemController) : this;
    }

    public <T1, T2> ItemList addFooter(T1 firstData,
                                       T2 secondData,
                                       DoubleBindableItemController<T1, T2, ? extends RecyclerView.ViewHolder> itemController) {
        return addFooter(new DoubleBindableItem<>(firstData, secondData, itemController));
    }

    public <T1, T2> ItemList addFooterIf(boolean condition,
                                         T1 firstData,
                                         T2 secondData,
                                         DoubleBindableItemController<T1, T2, ? extends RecyclerView.ViewHolder> itemController) {
        return addFooterIf(condition, new DoubleBindableItem<>(firstData, secondData, itemController));
    }

    public ItemList addFooter(NoDataItemController<? extends RecyclerView.ViewHolder> itemController) {
        return addFooter(new NoDataItem<>(itemController));
    }

    public ItemList addFooterIf(boolean condition,
                                NoDataItemController<? extends RecyclerView.ViewHolder> itemController) {
        return addFooterIf(condition, new NoDataItem<>(itemController));
    }
}
