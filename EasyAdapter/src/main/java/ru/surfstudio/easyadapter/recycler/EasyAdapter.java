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
package ru.surfstudio.easyadapter.recycler;


import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ru.surfstudio.easyadapter.recycler.controller.BaseItemController;
import ru.surfstudio.easyadapter.recycler.controller.BindableItemController;
import ru.surfstudio.easyadapter.recycler.controller.NoDataItemController;
import ru.surfstudio.easyadapter.recycler.holder.BaseViewHolder;
import ru.surfstudio.easyadapter.recycler.item.BaseItem;
import ru.surfstudio.easyadapter.recycler.item.NoDataItem;

/**
 * Adapter for RecyclerView with two features:
 * 1) adapter automatically calls necessary methods notify... after call {@link #setItems(ItemList)} or {@link #setData(Collection, BindableItemController)}
 * 2) adapter provides mechanism for simple configuring complex list with different types of items, see {@link ItemList}
 * <p>
 * You do need subclassing this class in most cases
 */
public class EasyAdapter extends RecyclerView.Adapter {

    private List<BaseItem> items = new ArrayList<>();
    private List<ItemInfo> lastItemsInfo = new ArrayList<>();
    private SparseArray<BaseItemController> supportedItemControllers = new SparseArray<>();
    private boolean autoNotifyOnSetItemsEnabled = true;
    private BaseItem<BaseViewHolder> firstInvisibleItem = new NoDataItem<>(new FirstInvisibleItemController());

    public EasyAdapter() {
        setHasStableIds(true);
    }

    public void setAutoNotifyOnSetItemsEnabled(boolean enableAutoNotifyOnSetItems) {
        this.autoNotifyOnSetItemsEnabled = enableAutoNotifyOnSetItems;
    }

    @Override
    public final int getItemViewType(int position) {
        return items.get(position).getItemController().viewType();
    }

    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return supportedItemControllers.get(viewType).createViewHolder(parent);
    }

    @Override
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BaseItem item = items.get(position);
        item.getItemController().bind(holder, item);
    }

    @Override
    public final int getItemCount() {
        return items.size();
    }

    /**
     * set data with controller for rendering
     * adapter automatically calls necessary methods notify... if {@link #autoNotifyOnSetItemsEnabled} sets
     *
     * @param data
     * @param itemController controller for data
     * @param <T>            type of data
     */
    public <T> void setData(@NonNull Collection<T> data, @NonNull BindableItemController<T, ? extends RecyclerView.ViewHolder> itemController) {
        setItems(ItemList.create(data, itemController));
    }

    /**
     * set Items for rendering
     * adapter automatically calls necessary methods notify... if {@link #autoNotifyOnSetItemsEnabled} sets
     *
     * @param items
     */
    public void setItems(@NonNull ItemList items) {
        setItems(items, autoNotifyOnSetItemsEnabled);
    }

    /**
     * set Items for rendering
     *
     * @param items
     * @param autoNotify need call {@link #autoNotify()}
     */
    protected void setItems(@NonNull ItemList items, boolean autoNotify) {
        this.items.clear();
        if (items.isEmpty() || items.get(0) != firstInvisibleItem) {
            this.items.add(firstInvisibleItem);
        }
        this.items.addAll(items);
        if (autoNotify) {
            autoNotify();
        }
        updateSupportedItemControllers(this.items);
    }

    /**
     * automatically calls necessary methods notify...
     */
    public void autoNotify() {
        final List<ItemInfo> newItemInfo = extractItemInfo();
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(
                new AutoNotifyDiffCallback(lastItemsInfo, newItemInfo));
        diffResult.dispatchUpdatesTo(this);
        lastItemsInfo = newItemInfo;
    }

    protected ItemList getItems() {
        return new ItemList(items);
    }

    private void updateSupportedItemControllers(List<BaseItem> items) {
        supportedItemControllers.clear();
        for (BaseItem item : items) {
            BaseItemController itemController = item.getItemController();
            supportedItemControllers.put(itemController.viewType(), itemController);
        }
    }

    @Override
    public final long getItemId(int position) {
        BaseItem item = items.get(position);
        return item.getItemController().getItemId(item);
    }

    public final long getItemHash(int position) {
        BaseItem item = items.get(position);
        return item.getItemController().getItemHash(item);
    }

    private List<ItemInfo> extractItemInfo() {
        int itemCount = getItemCount();
        List<ItemInfo> currentItemsInfo = new ArrayList<>(itemCount);
        for (int i = 0; i < itemCount; i++) {
            currentItemsInfo.add(new ItemInfo(
                    getItemId(i),
                    getItemHash(i)));
        }
        return currentItemsInfo;
    }

    private class ItemInfo {
        private long id;
        private long hash;

        ItemInfo(long id, long hash) {
            this.id = id;
            this.hash = hash;
        }

        long getId() {
            return id;
        }

        long getHash() {
            return hash;
        }
    }

    private class AutoNotifyDiffCallback extends DiffUtil.Callback {

        private final List<ItemInfo> lastItemsInfo;
        private final List<ItemInfo> newItemsInfo;

        AutoNotifyDiffCallback(List<ItemInfo> lastItemsInfo, List<ItemInfo> newItemsInfo) {
            this.lastItemsInfo = lastItemsInfo;
            this.newItemsInfo = newItemsInfo;
        }

        @Override
        public int getOldListSize() {
            return lastItemsInfo.size();
        }

        @Override
        public int getNewListSize() {
            return newItemsInfo.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return lastItemsInfo.get(oldItemPosition).getId() ==
                    newItemsInfo.get(newItemPosition).getId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return lastItemsInfo.get(oldItemPosition).getHash() ==
                    newItemsInfo.get(newItemPosition).getHash();
        }
    }

    /**
     * Empty first element for saving scroll position after notify... calls
     */
    private class FirstInvisibleItemController extends NoDataItemController<BaseViewHolder> {
        @Override
        public BaseViewHolder createViewHolder(ViewGroup parent) {
            ViewGroup.LayoutParams lp = new RecyclerView.LayoutParams(0, 0);
            View itemView = new View(parent.getContext());
            itemView.setLayoutParams(lp);
            return new BaseViewHolder(itemView);
        }
    }
}
