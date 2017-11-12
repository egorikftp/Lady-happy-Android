package ext.adapter;

import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class GhostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items = new ArrayList<>();
    private SparseArray<Class<? extends RecyclerView.ViewHolder>> viewTypes = new SparseArray<>();
    private LayoutInflater layoutInflater;

    private void readAnnotations(AnnotatedElement element) {
        if (element.isAnnotationPresent(BindItem.class)) {
            BindItem bindItem = element.getAnnotation(BindItem.class);
            putViewType(bindItem.layout(), bindItem.holder());
        } else {
            throw new IllegalStateException("items should be annotated with BindItem");
        }
    }

    private int readLayout(AnnotatedElement element) {
        if (element.isAnnotationPresent(BindItem.class)) {
            BindItem bindItem = element.getAnnotation(BindItem.class);
            return bindItem.layout();
        } else {
            throw new IllegalStateException("items should be annotated with BindItem");
        }
    }

    private void bind(RecyclerView.ViewHolder holder, Object o) {
        Class c = o.getClass();
        for (Method method : c.getMethods()) {
            if (method.isAnnotationPresent(Binder.class)) {
                try {
                    method.invoke(o, holder);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public GhostAdapter() {
    }

    public void putViewType(@LayoutRes int layout, Class<? extends RecyclerView.ViewHolder> holder) {
        this.viewTypes.put(layout, holder);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        if (viewTypes.size() == 0) {
            throw new IndexOutOfBoundsException(
                    "No ViewType is specified." +
                            "call putViewType before using adapter");
        }
        View view = layoutInflater.inflate(viewType, parent, false);
        try {
            return viewTypes.get(viewType).getConstructor(View.class).newInstance(view);
        } catch (InstantiationException | NullPointerException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        bind(holder, items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return readLayout(items.get(position).getClass());
    }

    /**
     * @param items GhostAdapter uses these items and bind recycler to them
     */
    public <T> void setItems(@NonNull List<T> items) {
        this.items.clear();
        this.items.addAll(items);
        for (T item : items) {
            readAnnotations(item.getClass());
        }
        notifyDataSetChanged();
    }

    public void removeAll() {
        items.clear();
        notifyDataSetChanged();
    }

    /**
     * @param items
     * @param <T>
     */
    public <T> void addItems(@NonNull List<T> items) {
        int start = this.items.size() - 1;
        this.items.addAll(items);
        for (T item : items) {
            readAnnotations(item.getClass());
        }
        if (start >= 0) {
            notifyItemRangeInserted(start, items.size());
        } else {
            notifyDataSetChanged();
        }
    }

    public <T> void addItems(@IntRange(from = 0) int position, @NonNull List<T> items) {
        if (position > this.items.size()) {
            throw new IndexOutOfBoundsException();
        }
        this.items.addAll(position, items);
        for (T item : items) {
            readAnnotations(item.getClass());
        }
        notifyItemRangeInserted(position, items.size());
    }

    /**
     * @param item
     * @param <T>
     */
    public <T> void addItem(@NonNull T item) {
        items.add(item);
        readAnnotations(item.getClass());
        notifyItemInserted(items.size() - 1);
    }

    /**
     * @param item
     * @param <T>  extends CoreItem
     */
    public <T> void removeItem(@NonNull T item) {
        int index = items.indexOf(item);
        if (index >= 0) {
            items.remove(index);
            notifyItemRemoved(index);
        }
    }

    /**
     * remove items
     *
     * @param start
     * @param end
     */
    public void removeItemRange(@IntRange(from = 0) int start, @IntRange(from = 0) int end) {

        if (start < items.size() && end <= items.size()) {

            int count = end - start;
            for (int j = 0; j < count; j++) {
                items.remove(start);

            }
            notifyDataSetChanged();
        }
    }

    /**
     * adding an item to a position
     *
     * @param position insert position
     * @param item     input item
     * @throws IndexOutOfBoundsException position > list size
     */
    public <T> void addItem(@IntRange(from = 0) int position, @NonNull T item) {
        if (position > items.size()) {
            throw new IndexOutOfBoundsException();
        }
        items.add(position, item);
        readAnnotations(item.getClass());

        notifyItemInserted(position);
    }

    /**
     * gettin all items
     */
    public <T> List<Object> getItems() {

        if (items.size() > 0)
            return items;
        return null;
    }

    /**
     * getting an item from a position
     *
     * @param position insert position
     * @throws IndexOutOfBoundsException position > list size
     */
    public <T> Object getItem(@IntRange(from = 0) int position) {
        if (position > items.size()) {
            throw new IndexOutOfBoundsException();
        }
        return items.get(position);
    }

    /**
     * getting items from a specified position to the end of list
     *
     * @param position start position
     * @throws IndexOutOfBoundsException position > list size
     */
    public <T> Object getItems(@IntRange(from = 0) int position) {
        if (position > items.size()) {
            throw new IndexOutOfBoundsException();
        }
        List<Object> temp = new ArrayList<>();
        temp.addAll(items);
        return temp;
    }

    /**
     * getting items from a specified position to the end of list
     *
     * @param beginPosition start position
     * @param endPosition   stop position
     * @throws IndexOutOfBoundsException position > list size Or position < 0 Or start postion be grater than end position
     */
    public <T> Object getItems(@IntRange(from = 0) int beginPosition, @IntRange(from = 0) int endPosition) {
        if (endPosition > items.size() || beginPosition < 0 || beginPosition > endPosition) {
            throw new IndexOutOfBoundsException();
        }
        List temp = new ArrayList();
        for (int i = beginPosition; i < endPosition + 1; i++)
            //noinspection unchecked
            temp.add(items.get(i));
        return temp;
    }

    /**
     * check if list has any item or not
     *
     * @return False  if any item exist
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * @return the iterator of list
     */
    @NonNull
    public Iterator iterator() {
        return items.iterator();
    }

    /**
     * @return an array of list
     */
    @NonNull
    public Object[] toArray() {
        return items.toArray();
    }

    public boolean retainAll(@NonNull Collection collection) {
        return items.retainAll(collection);
    }

    /**
     * check if object exists in list or not
     *
     * @param o object for search the list
     * @return False  if o item does not exist
     */
    public boolean contains(@NonNull Object o) {
        return items.contains(o);
    }

    /**
     * check an collection of objects are exist in list
     *
     * @param collection
     */
    public boolean containsAll(@NonNull Collection collection) {
        return items.containsAll(collection);
    }

    /**
     * getting items from a specified position to the end of list
     *
     * @param o object for search the list
     * @return index of o object if it exist on list
     * -1 will be returned if no  situation
     */
    public int indexOf(@NonNull Object o) {
        return items.indexOf(o);
    }
}
