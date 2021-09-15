package com.yhhl.yhyq.home.model;

import com.yhhl.yhyq.base.BaseCallBack;
import com.yhhl.yhyq.base.BaseObserver;
import com.yhhl.yhyq.base.BaseService;
import com.yhhl.yhyq.home.bean.BackLogBean;
import com.yhhl.yhyq.login.bean.LoginBean;
import com.yhhl.yhyq.net.RetrofitUtil;
import com.yhhl.yhyq.util.ToastUtil;

import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeModel {
    public void back_log(String url, HashMap<String,Object> map, final BaseCallBack<List<BackLogBean>> callBack){
        BaseService baseService = RetrofitUtil.getInstance().createApi(BaseService.class);
        baseService.message(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<BackLogBean>>(callBack){
                    @Override
                    public void onNext(List<BackLogBean> backLogBean) {
                        super.onNext(backLogBean);
                        callBack.requestSuccess(backLogBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    public void inform(String url, HashMap<String,Object> map, final BaseCallBack<List<BackLogBean>> callBack){
        BaseService baseService = RetrofitUtil.getInstance().createApi(BaseService.class);
//        BaseObserver<List<BackLogBean>> listBaseObserver = new BaseObserver<>(callBack);
        baseService.message(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<BackLogBean>>(callBack){
                    @Override
                    public void onNext(List<BackLogBean> backLogBean) {
                        super.onNext(backLogBean);
                        callBack.requestSuccess(backLogBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
