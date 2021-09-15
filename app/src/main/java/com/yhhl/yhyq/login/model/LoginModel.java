package com.yhhl.yhyq.login.model;

import com.yhhl.yhyq.base.BaseCallBack;
import com.yhhl.yhyq.base.BaseObserver;
import com.yhhl.yhyq.base.BaseService;
import com.yhhl.yhyq.login.bean.LoginBean;
import com.yhhl.yhyq.net.HttpUrlUtils;
import com.yhhl.yhyq.net.RetrofitUtil;
import com.yhhl.yhyq.util.ToastUtil;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginModel {
    public void login(String url,HashMap<String,Object> map, final BaseCallBack<LoginBean> callBack){
        BaseService baseService = RetrofitUtil.getInstance().createApi(BaseService.class);
        baseService.login(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<LoginBean>(callBack){
                    @Override
                    public void onNext(LoginBean loginBean) {
                        super.onNext(loginBean);
                        callBack.requestSuccess(loginBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}