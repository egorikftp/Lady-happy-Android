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
package ru.surfstudio.android.easyadapter.pagination;

import android.view.ViewGroup;

import java.util.Collection;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import ru.surfstudio.android.easyadapter.EasyAdapter;
import ru.surfstudio.android.easyadapter.ItemList;
import ru.surfstudio.android.easyadapter.controller.BindableItemController;
import ru.surfstudio.android.easyadapter.controller.NoDataItemController;
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder;
import ru.surfstudio.android.easyadapter.item.NoDataItem;


/**
 * Adapter with pagination Footer, based on {@link EasyAdapter}
 * Adapter emit event "onShowMore" when user scroll to bottom or click on footer with state {@link PaginationState#ERROR}.
 * Adapter can emit this event if user scroll only if state is {@link PaginationState#READY}
 */
public abstract class BasePaginationableAdapter extends EasyAdapter {

    private OnShowMoreListener onShowMoreListener;
    private boolean blockShowMoreEvent = true;

    public interface OnShowMoreListener {
        void onShowMore();
    }

    public BasePaginationableAdapter() {
        getPaginationFooterController().setListener(new OnShowMoreListener() {
            @Override
            public void onShowMore() {
                onShowMoreClick();
            }
        });
    }

    protected abstract BasePaginationFooterController<? extends RecyclerView.ViewHolder> getPaginationFooterController();

    public void setItems(@NonNull ItemList items, @NonNull PaginationState state) {
        blockShowMoreEvent = state != PaginationState.READY;
        getPaginationFooterController().setState(state);
        if (state.isVisible()) {
            items.add(getPaginationFooterController());
        }
        super.setItems(items);
    }

    /**
     * use {@link #setItems(ItemList, PaginationState)} instead
     */
    @Override
    @Deprecated
    public void setItems(@NonNull ItemList items) {
        throw new UnsupportedOperationException("use setItems(ItemList, PaginationState) instead");
    }

    public <T> void setData(@NonNull Collection<T> data,
                            @NonNull BindableItemController<T, ? extends RecyclerView.ViewHolder> itemController,
                            @NonNull PaginationState paginationState) {
        setItems(ItemList.create(data, itemController), paginationState);
    }

    /**
     * use {@link #setData(Collection, BindableItemController, PaginationState)} instead
     */
    @Override
    @Deprecated
    public <T> void setData(@NonNull Collection<T> data,
                            @NonNull BindableItemController<T, ? extends RecyclerView.ViewHolder> itemController) {
        throw new UnsupportedOperationException("use setData(Collection, BindableItemController, PaginationState) instead");
    }

    public void setOnShowMoreListener(OnShowMoreListener onShowMoreListener) {
        this.onShowMoreListener = onShowMoreListener;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        initLayoutManager(layoutManager);
        initPaginationListener(recyclerView, layoutManager);
    }

    protected void initPaginationListener(RecyclerView recyclerView, final RecyclerView.LayoutManager layoutManager) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (onShowMoreListener != null && !blockShowMoreEvent) {
                    int totalItemCount = layoutManager.getItemCount();
                    int firstVisibleItem = findFirstVisibleItem(layoutManager);
                    int lastVisibleItem = findLastVisibleItem(layoutManager);
                    int numVisibleItem = lastVisibleItem - firstVisibleItem;

                    if (totalItemCount - lastVisibleItem < 2 * numVisibleItem) {
                        blockShowMoreEvent = true;
                        onShowMoreListener.onShowMore();
                    }
                }
            }
        });
    }

    protected int findFirstVisibleItem(RecyclerView.LayoutManager layoutManager) {
        int pos = 0;

        if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager lm = (StaggeredGridLayoutManager) layoutManager;

            int[] items = lm.findFirstVisibleItemPositions(new int[lm.getSpanCount()]);
            int position = 0;

            for (int i = 0; i < items.length - 1; i++) {
                position = Math.min(items[i], items[i + 1]);
            }
            pos = position;
        }

        if (layoutManager instanceof LinearLayoutManager) {
            pos = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }

        return pos;
    }

    protected int findLastVisibleItem(RecyclerView.LayoutManager layoutManager) {
        int pos = 0;

        if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager lm = (StaggeredGridLayoutManager) layoutManager;

            int spanCount = lm.getSpanCount();
            int[] items = lm.findLastVisibleItemPositions(new int[spanCount]);
            int position = 0;

            for (int i = 0; i < items.length - 1; i++) {
                position = Math.max(items[i], items[i + 1]);
            }
            pos = position;
        }

        if (layoutManager instanceof LinearLayoutManager) {
            pos = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        }

        return pos;
    }

    protected void initLayoutManager(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager castedLayoutManager = (GridLayoutManager) layoutManager;
            final GridLayoutManager.SpanSizeLookup existingLookup = castedLayoutManager.getSpanSizeLookup();

            castedLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (position == getPaginationFooterPosition() && hasFooter()) {
                        //full width footer
                        return castedLayoutManager.getSpanCount();
                    } else {
                        return existingLookup.getSpanSize(position);
                    }
                }
            });
        }
    }

    private int getPaginationFooterPosition() {
        return getItemCount() - 1;
    }

    private boolean hasFooter() {
        return getPaginationFooterController().getState().isVisible();
    }

    private void onShowMoreClick() {
        setState(PaginationState.READY);
        if (onShowMoreListener != null) {
            onShowMoreListener.onShowMore();
        }
    }

    public void setState(final PaginationState state) {
        blockShowMoreEvent = state != PaginationState.READY;
        ItemList items = getItems();
        int lastIndex = items.size() - 1;
        if (lastIndex >= 0 && hasFooter()) {
            items.remove(lastIndex);
        }
        getPaginationFooterController().setState(state);
        if (state.isVisible()) {
            items.add(getPaginationFooterController());
        }
        setItems(items, true);
    }

    protected abstract static class BasePaginationFooterController<H extends BasePaginationFooterHolder> extends NoDataItemController<H> {
        private PaginationState state = PaginationState.COMPLETE;
        private OnShowMoreListener listener;

        public void setListener(OnShowMoreListener listener) {
            this.listener = listener;
        }

        public void setState(PaginationState state) {
            this.state = state;
        }

        public PaginationState getState() {
            return state;
        }

        @Override
        public void bind(H holder, NoDataItem<H> item) {
            holder.bind(state);
        }

        @Override
        public H createViewHolder(ViewGroup parent) {
            return createViewHolder(parent, listener);
        }

        @Override
        public String getItemHash(NoDataItem<H> item) {
            return String.valueOf(state.hashCode());
        }

        protected abstract H createViewHolder(ViewGroup parent, OnShowMoreListener listener);
    }

    protected static abstract class BasePaginationFooterHolder extends BindableViewHolder<PaginationState> {
        public BasePaginationFooterHolder(ViewGroup parent, @LayoutRes int layoutRes) {
            super(parent, layoutRes);
        }
    }

}

