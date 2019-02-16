package com.egoriku.ladyhappy.arch.fragment;

import android.os.Bundle;
import android.view.View;

import com.egoriku.ladyhappy.arch.pvm.BaseContract;
import com.egoriku.ladyhappy.arch.pvm.BaseViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public abstract class BaseFragment<V extends BaseContract.View, P extends BaseContract.Presenter<V>>
        extends Fragment implements BaseContract.View {

    protected P presenter;

    @SuppressWarnings("unchecked")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BaseViewModel<V, P> viewModel = ViewModelProviders.of(this).get(BaseViewModel.class);

        boolean isPresenterCreated = false;

        if (viewModel.getPresenter() == null) {
            viewModel.setPresenter(providePresenter());
            isPresenterCreated = true;
        }

        presenter = viewModel.getPresenter();
        presenter.attachLifecycle(getLifecycle());
        presenter.attachView((V) this);

        if (isPresenterCreated) presenter.onPresenterCreated();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachLifecycle(getLifecycle());
        presenter.detachView();
    }

    protected abstract P providePresenter();
}