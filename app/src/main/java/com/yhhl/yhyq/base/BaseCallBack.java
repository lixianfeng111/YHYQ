package com.yhhl.yhyq.base;

public interface BaseCallBack<T> {
    void requestError(ServerException e);
    void requestSuccess(T t);
}