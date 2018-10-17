package com.egoriku.landingfragment.common.parallax;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.OnScrollListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

import java.util.ArrayList;
import java.util.List;

public class ParallaxScrollListener extends OnScrollListener implements OnGlobalLayoutListener, LifecycleObserver {

    private final List<IParallaxScrollListener> scrollListeners = new ArrayList();
    private RecyclerView recyclerView;
    private int recyclerViewHeight;

    public void initWith(RecyclerView pRecyclerView, Lifecycle lifecycle) {
        recyclerView = pRecyclerView;
        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(this);
        recyclerView.addOnScrollListener(this);
        lifecycle.addObserver(this);
    }

    public void addListener(IParallaxScrollListener IParallaxScrollListener) {
        scrollListeners.add(IParallaxScrollListener);
    }

    public int getRecyclerViewHeight() {
        return recyclerViewHeight;
    }

    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        for (IParallaxScrollListener scrollListener : scrollListeners) {
            scrollListener.onScroll();
        }
    }

    @Override
    public void onGlobalLayout() {
        int height = 0;

        if (recyclerView != null) {
            height = recyclerView.getHeight();
        }

        if (height > 0) {
            recyclerViewHeight = height;
            recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

            for (IParallaxScrollListener scrollListener : scrollListeners) {
                scrollListener.onAttach();
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected void detach() {
        for (IParallaxScrollListener scrollListener : scrollListeners) {
            scrollListener.onDetach();
        }
        recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        recyclerView.removeOnScrollListener(this);
        scrollListeners.clear();
        recyclerView = null;
    }
}
