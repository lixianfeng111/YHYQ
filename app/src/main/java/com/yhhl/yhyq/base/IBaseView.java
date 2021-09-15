package com.yhhl.yhyq.base;

import java.util.List;

public interface IBaseView<T> {
    void showLoading();

    void hideLoading();

    void onDataSuccess(T data);

    void onDataFailed(String msg);

    void onDataList(List<T> list);

}
