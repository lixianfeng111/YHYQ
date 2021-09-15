package com.yhhl.yhyq.login.presenter;

import com.yhhl.yhyq.base.BaseCallBack;
import com.yhhl.yhyq.base.BasePresenter;
import com.yhhl.yhyq.base.ServerException;
import com.yhhl.yhyq.login.model.LoginModel;
import com.yhhl.yhyq.login.bean.LoginBean;
import com.yhhl.yhyq.login.view.LoginView;
import com.yhhl.yhyq.net.HttpUrlUtils;
import com.yhhl.yhyq.util.ToastUtil;

import java.util.HashMap;

public class LoginPresenter extends BasePresenter<LoginView> {

    private final LoginModel loginModel;

    public LoginPresenter(LoginView iBaseView) {
        super(iBaseView);
        loginModel = new LoginModel();
    }
    public void login(String username,String password){
        final HashMap<String,Object> map=new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        map.put("grant_type","password");
        map.put("client_id","app_1");
        map.put("client_secret","app1_sec");
        getiBaseView().showLoading();
        loginModel.login(HttpUrlUtils.LOGIN,map, new BaseCallBack<LoginBean>() {
            @Override
            public void requestError(ServerException e) {
                getiBaseView().hideLoading();
                ServerException.getMessageAndCode();
            }

            @Override
            public void requestSuccess(LoginBean loginBean) {
                getiBaseView().hideLoading();
                getiBaseView().onDataSuccess(loginBean);
            }
        });

    }
}