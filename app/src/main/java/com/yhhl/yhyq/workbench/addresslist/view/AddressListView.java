package com.yhhl.yhyq.workbench.addresslist.view;

import com.yhhl.yhyq.base.IBaseView;
import com.yhhl.yhyq.workbench.addresslist.bean.AddressListBean;
import com.yhhl.yhyq.workbench.addresslist.bean.CompanyBean;

import java.util.List;

public interface AddressListView extends IBaseView<AddressListBean> {
    void onFindCompanySuccess(List<CompanyBean> list);
    void onFindCompanyFailed();
}
