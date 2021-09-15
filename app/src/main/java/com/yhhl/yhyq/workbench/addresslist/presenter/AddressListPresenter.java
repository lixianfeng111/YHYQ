package com.yhhl.yhyq.workbench.addresslist.presenter;

import com.yhhl.yhyq.base.BaseCallBack;
import com.yhhl.yhyq.base.BaseModel;
import com.yhhl.yhyq.base.BasePresenter;
import com.yhhl.yhyq.base.ServerException;
import com.yhhl.yhyq.net.HttpUrlUtils;
import com.yhhl.yhyq.workbench.addresslist.bean.AddressListBean;
import com.yhhl.yhyq.workbench.addresslist.bean.CompanyBean;
import com.yhhl.yhyq.workbench.addresslist.view.AddressListView;

import java.util.HashMap;
import java.util.List;

public class AddressListPresenter extends BasePresenter<AddressListView> {

    private final BaseModel baseModel;

    public AddressListPresenter(AddressListView iBaseView) {
        super(iBaseView);
        baseModel = new BaseModel();
    }
    public void getCompany(){
        HashMap<String, Object> map = new HashMap<>();
        baseModel.getCompany(HttpUrlUtils.COMPANYAndGROUPS, map, new BaseCallBack<List<CompanyBean>>() {
            @Override
            public void requestError(ServerException e) {
                ServerException.getMessageAndCode();
                getiBaseView().onFindCompanyFailed();
            }

            @Override
            public void requestSuccess(List<CompanyBean> companyBeans) {
                getiBaseView().onFindCompanySuccess(companyBeans);
            }
        });
    }
    public void getList(String id){
        HashMap<String, Object> map = new HashMap<>();
        if (!id.isEmpty()){
            map.put("dept_id",id);
        }
        baseModel.getList(HttpUrlUtils.ADDRESS_LIST, map, new BaseCallBack<List<AddressListBean>>() {
            @Override
            public void requestError(ServerException e) {
                ServerException.getMessageAndCode();
                getiBaseView().onDataFailed("");
            }

            @Override
            public void requestSuccess(List<AddressListBean> addressListBeans) {
                getiBaseView().onDataList(addressListBeans);
            }
        });
    }
}
