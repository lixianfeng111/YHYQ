package com.yhhl.yhyq.home.presenter;

import com.yhhl.yhyq.base.BaseCallBack;
import com.yhhl.yhyq.base.BasePresenter;
import com.yhhl.yhyq.base.ServerException;
import com.yhhl.yhyq.home.bean.BackLogBean;
import com.yhhl.yhyq.home.model.HomeModel;
import com.yhhl.yhyq.home.view.BackLogView;
import com.yhhl.yhyq.net.HttpUrlUtils;

import java.util.HashMap;
import java.util.List;

public class HomePresenter extends BasePresenter<BackLogView> {

    private final HomeModel homeModel;
    public HomePresenter(BackLogView iBaseView) {
        super(iBaseView);
        homeModel = new HomeModel();
    }

    public void back_log(String search,String is_read,String page,String message_flag){
        final HashMap<String,Object> map=new HashMap<>();
        if (!search.isEmpty()){
            map.put("q",search);
        }
        map.put("is_read",is_read);
        map.put("page",page);
        map.put("send_or_receive","receive");
        map.put("message_flag",message_flag);
//        map.put("per_page","10");

        homeModel.back_log(HttpUrlUtils.MESSAGES, map, new BaseCallBack<List<BackLogBean>>() {
            @Override
            public void requestError(ServerException e) {
                getiBaseView().onDataFailed(e.getMessage());
                ServerException.getMessageAndCode();
            }

            @Override
            public void requestSuccess(List<BackLogBean> backLogBean) {
                getiBaseView().onDataList(backLogBean);
            }
        });
    }

    public void back_log2(String search,String is_read,String page,String message_flag){
        final HashMap<String,Object> map=new HashMap<>();
        if (!search.isEmpty()){
            map.put("q",search);
        }
        map.put("is_read",is_read);
        map.put("page",page);
        map.put("send_or_receive","receive");
        map.put("message_flag",message_flag);
//        map.put("per_page","10");

        homeModel.back_log(HttpUrlUtils.MESSAGES, map, new BaseCallBack<List<BackLogBean>>() {
            @Override
            public void requestError(ServerException e) {
                getiBaseView().onDataFailed(e.getMessage());
                ServerException.getMessageAndCode();
            }

            @Override
            public void requestSuccess(List<BackLogBean> backLogBean) {
                getiBaseView().onDataList(backLogBean);
            }
        });
    }
}
