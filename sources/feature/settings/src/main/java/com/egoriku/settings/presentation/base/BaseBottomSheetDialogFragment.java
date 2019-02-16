package com.egoriku.settings.presentation.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.egoriku.ladyhappy.arch.pvm.BaseContract;
import com.egoriku.ladyhappy.arch.pvm.BaseViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

public abstract class BaseBottomSheetDialogFragment<V extends BaseContract.View, P extends BaseContract.Presenter<V>>
        extends BottomSheetDialogFragment implements BaseContract.View {

    protected P presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

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
    public void onAttach(Context context) {
        injectDependencies();
        super.onAttach(context);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachLifecycle(getLifecycle());
        presenter.detachView();
    }

    protected abstract P providePresenter();

    protected abstract void injectDependencies();

    @LayoutRes
    protected abstract int getLayoutId();
}