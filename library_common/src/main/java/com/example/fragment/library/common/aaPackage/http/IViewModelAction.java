package com.example.fragment.library.common.aaPackage.http;

import androidx.lifecycle.MutableLiveData;

public interface IViewModelAction {
    void startLoading();

    void startLoading(String message);

    void dismissLoading();

    void showToast(String message);

    void finish();

    void finishWithResultOk();

    MutableLiveData<BaseActionEvent> getActionLiveData();

}
