package com.example.fragment.library.common.aaPackage.http;

interface RequestCallback<T> {
    void onSuccess(T t);
}


interface RequestMultiplyCallback<T> extends RequestCallback<T> {

    void onFail(BaseException e);

}