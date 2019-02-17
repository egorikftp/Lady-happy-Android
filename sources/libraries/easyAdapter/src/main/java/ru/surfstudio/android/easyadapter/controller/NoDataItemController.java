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
import ru.surfstudio.android.easyadapter.EasyAdapter;
import ru.surfstudio.android.easyadapter.ItemList;
import ru.surfstudio.android.easyadapter.item.NoDataItem;

/**
 * Controller for item of RecyclerView without data {@link NoDataItem}
 * It used with {@link EasyAdapter} and {@link ItemList}
 * <p>
 * Подразумевается, что будет существовать только один элемент в списке с конкретной реализацией NoDataItemController, если их больше одного, то может возникнуть креш с ошибкой:
 * java.lang.IllegalStateException: Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.
 * Для того, чтобы от неё избавиться, необходимо переопределить метод getItemId(), чтобы он возвращал различные ID для разных item. Например: item.hashCode()
 *
 * @param <H> type of ViewHolder
 */
public abstract class NoDataItemController<H extends RecyclerView.ViewHolder>
        extends BaseItemController<H, NoDataItem<H>> {

    @Override
    public String getItemId(NoDataItem<H> item) {
        return String.valueOf(getClass().getCanonicalName().hashCode());
    }

    @Override
    public void bind(H holder, NoDataItem<H> item) {
        //empty
    }

    @Override
    public String getItemHash(NoDataItem<H> item) {
        return "0";
    }
}
