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

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import ru.surfstudio.android.easyadapter.controller.BaseItemController;
import ru.surfstudio.android.easyadapter.controller.BindableItemController;
import ru.surfstudio.android.easyadapter.controller.NoDataItemController;
import ru.surfstudio.android.easyadapter.holder.BaseViewHolder;
import ru.surfstudio.android.easyadapter.item.BaseItem;
import ru.surfstudio.android.easyadapter.item.NoDataItem;

/**
 * Adapter for RecyclerView with two features:
 * 1) adapter automatically calls necessary methods notify... after call {@link #setItems(ItemList)} or {@link #setData(Collection, BindableItemController)}
 * 2) adapter provides mechanism for simple configuring complex list with different types of items, see {@link ItemList}
 * <p>
 * You do need subclassing this class in most cases
 */
public class EasyAdapter extends RecyclerView.Adapter {

    public static final int INFINITE_SCROLL_LOOPS_COUNT = 100;

    private List<BaseItem> items = new ArrayList<>();
    private List<ItemInfo> lastItemsInfo = new ArrayList<>();
    private SparseArray<BaseItemController> supportedItemControllers = new SparseArray<>();
    private boolean autoNotifyOnSetItemsEnabled = true;
    private boolean firstInvisibleItemEnabled = true;
    private BaseItem<BaseViewHolder> firstInvisibleItem = new NoDataItem<>(new FirstInvisibleItemController());

    private boolean infiniteScroll;

    public EasyAdapter() {
        setHasStableIds(true);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        initLayoutManager(recyclerView.getLayoutManager());
    }

    @Override
    public final int getItemViewType(int position) {
        return items.get(getListPosition(position)).getItemController().viewType();
    }

    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return supportedItemControllers.get(viewType).createViewHolder(parent);
    }

    @Override
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BaseItem item = items.get(getListPosition(position));
        item.getItemController().bind(holder, item);
    }

    @Override
    public final int getItemCount() {
        return infiniteScroll ? INFINITE_SCROLL_LOOPS_COUNT * items.size() : items.size();
    }

    @Override
    public final long getItemId(int position) {
        return getItemStringId(position).hashCode();
    }

    public final String getItemStringId(int position) {
        BaseItem item = items.get(getListPosition(position));
        return item.getItemController().getItemId(item);
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
     * Sets if auto notify is enabled.
     *
     * @see #autoNotify()
     */
    public void setAutoNotifyOnSetItemsEnabled(boolean enableAutoNotifyOnSetItems) {
        this.autoNotifyOnSetItemsEnabled = enableAutoNotifyOnSetItems;
    }

    /**
     * Toggle whether {@link FirstInvisibleItemController} is enabled
     *
     * @see FirstInvisibleItemController
     */
    public void setFirstInvisibleItemEnabled(boolean enableFirstInvisibleItem) {
        this.firstInvisibleItemEnabled = enableFirstInvisibleItem;
    }

    /**
     * @param infiniteScroll make list infinite scrolling
     */
    public void setInfiniteScroll(boolean infiniteScroll) {
        this.infiniteScroll = infiniteScroll;
    }

    /**
     * automatically calls necessary methods notify...
     */
    public void autoNotify() {
        final List<ItemInfo> newItemInfo = extractRealItemInfo();
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(
                new AutoNotifyDiffCallback(lastItemsInfo, newItemInfo));
        diffResult.dispatchUpdatesTo(this);
        lastItemsInfo = newItemInfo;
    }


    public final String getItemHash(int position) {
        BaseItem item = items.get(getListPosition(position));
        return item.getItemController().getItemHash(item);
    }

    /**
     * set Items for rendering
     *
     * @param items
     * @param autoNotify need call {@link #autoNotify()}
     */
    protected void setItems(@NonNull ItemList items, boolean autoNotify) {
        this.items.clear();
        if (firstInvisibleItemEnabled && (items.isEmpty() || items.get(0) != firstInvisibleItem)) {
            this.items.add(firstInvisibleItem);
        }
        this.items.addAll(items);

        if (autoNotify) {
            autoNotify();
        }
        updateSupportedItemControllers(this.items);
    }

    protected ItemList getItems() {
        return new ItemList(items);
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

    private void updateSupportedItemControllers(List<BaseItem> items) {
        supportedItemControllers.clear();
        for (BaseItem item : items) {
            BaseItemController itemController = item.getItemController();
            supportedItemControllers.put(itemController.viewType(), itemController);
        }
    }

    /**
     * extract real items info, despite of infinite or ordinary scroll
     */
    private List<ItemInfo> extractRealItemInfo() {
        int itemCount = items.size();
        List<ItemInfo> currentItemsInfo = new ArrayList<>(itemCount);
        for (int i = 0; i < itemCount; i++) {
            currentItemsInfo.add(new ItemInfo(
                    getItemStringId(i),
                    getItemHash(i)));
        }
        return currentItemsInfo;
    }

    private void initLayoutManager(LayoutManager layoutManager) {
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager castedLayoutManager = (GridLayoutManager) layoutManager;
            final GridLayoutManager.SpanSizeLookup existingLookup = castedLayoutManager.getSpanSizeLookup();

            castedLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (position == 0) {
                        //full first invisible element
                        return castedLayoutManager.getSpanCount();
                    } else {
                        return existingLookup.getSpanSize(position);
                    }
                }
            });
        }
    }

    private int getListPosition(int adapterPosition) {
        return infiniteScroll
                ? adapterPosition % items.size()
                : adapterPosition;
    }

    private class AutoNotifyDiffCallback extends DiffUtil.Callback {
        private final String MAGIC_NUMBER = String.valueOf(3578121127L); // used for making ids unique

        private final List<ItemInfo> lastItemsInfo;
        private final List<ItemInfo> newItemsInfo;

        AutoNotifyDiffCallback(List<ItemInfo> lastItemsInfo,
                               List<ItemInfo> newItemsInfo) {
            this.lastItemsInfo = lastItemsInfo;
            this.newItemsInfo = newItemsInfo;
        }

        @Override
        public int getOldListSize() {
            if (infiniteScroll) {
                return lastItemsInfo.size() * INFINITE_SCROLL_LOOPS_COUNT;
            } else {
                return lastItemsInfo.size();
            }
        }

        @Override
        public int getNewListSize() {
            if (infiniteScroll) {
                return newItemsInfo.size() * INFINITE_SCROLL_LOOPS_COUNT;
            } else {
                return newItemsInfo.size();
            }
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            if (infiniteScroll) {
                //magic numbers make every element id unique
                String lastItemsFakeItemId = lastItemsInfo.get(oldItemPosition % lastItemsInfo.size()).getId() +
                        String.valueOf(oldItemPosition) +
                        MAGIC_NUMBER;
                String newItemsFakeItemId = newItemsInfo.get(newItemPosition % newItemsInfo.size()).getId() +
                        String.valueOf(newItemPosition) +
                        MAGIC_NUMBER;

                return lastItemsFakeItemId.equals(newItemsFakeItemId);
            }
            return lastItemsInfo
                    .get(oldItemPosition)
                    .getId()
                    .equals(newItemsInfo.get(newItemPosition).getId());
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            if (infiniteScroll) {
                oldItemPosition = oldItemPosition % lastItemsInfo.size();
                newItemPosition = newItemPosition % newItemsInfo.size();
            }
            return lastItemsInfo
                    .get(oldItemPosition)
                    .getHash()
                    .equals(newItemsInfo.get(newItemPosition).getHash());
        }
    }

    private class ItemInfo {
        private String id;
        private String hash;

        ItemInfo(String id, String hash) {
            this.id = id;
            this.hash = hash;
        }

        String getId() {
            return id;
        }

        String getHash() {
            return hash;
        }
    }

    /**
     * Empty first element for saving scroll position after notify... calls
     */
    private class FirstInvisibleItemController extends NoDataItemController<BaseViewHolder> {
        @Override
        public BaseViewHolder createViewHolder(ViewGroup parent) {
            ViewGroup.LayoutParams lp = new RecyclerView.LayoutParams(1, 1); // установить размер 1px, иначе проблемы с swipe-to-refresh и drag&drop https://github.com/airbnb/epoxy/issues/74
            View itemView = new View(parent.getContext());
            itemView.setLayoutParams(lp);
            return new BaseViewHolder(itemView);
        }
    }
}
