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
package ru.surfstudio.easyadapter.recycler.pagination;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.Collection;

import ru.surfstudio.easyadapter.recycler.EasyAdapter;
import ru.surfstudio.easyadapter.recycler.ItemList;
import ru.surfstudio.easyadapter.recycler.controller.BindableItemController;
import ru.surfstudio.easyadapter.recycler.controller.NoDataItemController;
import ru.surfstudio.easyadapter.recycler.holder.BindableViewHolder;
import ru.surfstudio.easyadapter.recycler.item.NoDataItem;

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
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        initLayoutManager(layoutManager);
        initPaginationListener(recyclerView, layoutManager);
    }

    private void initPaginationListener(RecyclerView recyclerView, final LinearLayoutManager layoutManager) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (onShowMoreListener != null && !blockShowMoreEvent) {
                    int totalItemCount = layoutManager.getItemCount();
                    int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
                    int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                    int numVisibleItem = lastVisibleItem - firstVisibleItem;

                    if (totalItemCount - lastVisibleItem < 2 * numVisibleItem) {
                        blockShowMoreEvent = true;
                        onShowMoreListener.onShowMore();
                    }
                }
            }
        });
    }

    private void initLayoutManager(LinearLayoutManager layoutManager) {
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

    private void setState(final PaginationState state) {
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
        public long getItemHash(NoDataItem<H> item) {
            return state.hashCode();
        }

        protected abstract H createViewHolder(ViewGroup parent, OnShowMoreListener listener);
    }

    protected static abstract class BasePaginationFooterHolder extends BindableViewHolder<PaginationState> {

        public BasePaginationFooterHolder(ViewGroup parent, @LayoutRes int layoutRes) {
            super(parent, layoutRes);
        }

    }

}

