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
package ru.surfstudio.android.easyadapter.controller;

import androidx.recyclerview.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.Random;

import ru.surfstudio.android.easyadapter.EasyAdapter;
import ru.surfstudio.android.easyadapter.ItemList;
import ru.surfstudio.android.easyadapter.item.BaseItem;

/**
 * Base Controller for item of RecyclerView. It used with {@link EasyAdapter} and {@link ItemList}
 * It responsible for interaction with item.
 *
 * @param <H> type of ViewHolder
 * @param <I> type of Item
 */
public abstract class BaseItemController<H extends RecyclerView.ViewHolder, I extends BaseItem> {

    public static final long NO_ID = RecyclerView.NO_ID;
    private int uniqueControllerInstanceId;

    public BaseItemController() {
        uniqueControllerInstanceId = new Random().nextInt();
    }

    public abstract void bind(H holder, I item);

    public abstract H createViewHolder(ViewGroup parent);

    public int viewType() {
        return getClass().getCanonicalName().hashCode() + uniqueControllerInstanceId;
    }

    /**
     * must return unique value
     * method is used for automatically call notify... methods, see {@link EasyAdapter}
     *
     * @return item id
     */
    public String getItemId(I item) {
        return String.valueOf(NO_ID);
    }

    /**
     * method is used for automatically call notify... methods, see {@link EasyAdapter}
     *
     * @return hash of item's data
     */
    public abstract String getItemHash(I item);
}
