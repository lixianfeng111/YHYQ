package com.yhhl.yhyq.base;

import com.yhhl.yhyq.net.GsonUtil;
import com.yhhl.yhyq.util.NetUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class CommonObserver<T> implements Observer<T> {
    public HttpCallBack mHttpCallBack;
    //传泛型
    Class<?> dataType = null;
    private T t;

    public CommonObserver(HttpCallBack httpCallBack) {
        mHttpCallBack = httpCallBack;
    }

    @Override
    public void onSubscribe(Disposable d) {
        //请求网络前
        boolean networkConnected = NetUtils.isconnected(App.sContext);
        if (networkConnected) {
            mHttpCallBack.onRequest();
        }else {
            //取消订阅
            d.dispose();
            mHttpCallBack.onFailed("网络不给力");
        }
    }

    @Override
    public void onNext(T t) {
        String json = GsonUtil.getInstance().mGson.toJson(t);
        T result = null;
        List<T> list = null;
        if (json.startsWith("{")) {
            result = (T) GsonUtil.getInstance().mGson.fromJson(json, dataType);
        } else {
            ListWithElements<T> tListWithElements = new ListWithElements<>((Class<T>) dataType);
            list = GsonUtil.mGson.fromJson(json, tListWithElements);
            result = null;
        }
        if (result != null) {
            mHttpCallBack.onDataSuccess(result);
        } else if (list != null) {
            mHttpCallBack.onListSuccess(list);
        } else {
            mHttpCallBack.onFailed("数据结构异常");
        }
    }

    @Override
    public void onError(Throwable e) {
        //请求异常
        mHttpCallBack.onFailed(e.getMessage());
    }

    @Override
    public void onComplete() {
        // 请求完成
    }

    public <T> void getDataType(T t){
        dataType=t.getClass();
    }

    private class ListWithElements<T> implements ParameterizedType {

        private Class<T> elementsClass;

        public ListWithElements(Class<T> elementsClass) {
            this.elementsClass = elementsClass;
        }

        public Type[] getActualTypeArguments() {
            return new Type[] {elementsClass};
        }

        public Type getRawType() {
            return List.class;
        }

        public Type getOwnerType() {
            return null;
        }
    }
}
