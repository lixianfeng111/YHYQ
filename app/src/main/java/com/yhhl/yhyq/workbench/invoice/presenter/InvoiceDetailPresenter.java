package com.yhhl.yhyq.workbench.invoice.presenter;

import com.yhhl.yhyq.base.BaseCallBack;
import com.yhhl.yhyq.base.BasePresenter;
import com.yhhl.yhyq.base.ServerException;
import com.yhhl.yhyq.net.GsonUtil;
import com.yhhl.yhyq.util.ToastUtil;
import com.yhhl.yhyq.workbench.invoice.bean.InvoiceDetailBean;
import com.yhhl.yhyq.workbench.invoice.model.InvoiceDetailModel;
import com.yhhl.yhyq.workbench.invoice.view.InvoiceDetailView;

import java.util.HashMap;

public class InvoiceDetailPresenter extends BasePresenter<InvoiceDetailView> {

    private final InvoiceDetailModel invoiceDetailModel;

    public InvoiceDetailPresenter(InvoiceDetailView iBaseView) {
        super(iBaseView);
        invoiceDetailModel = new InvoiceDetailModel();
    }

    public void invoice_detail(String id){
        HashMap<String, Object> map = new HashMap<>();

        map.put("id",id);
        invoiceDetailModel.invoice_detail(map, new BaseCallBack<InvoiceDetailBean>() {
            @Override
            public void requestError(ServerException e) {
                ServerException.getMessageAndCode();
            }

            @Override
            public void requestSuccess(InvoiceDetailBean invoiceDetailBean) {
                getiBaseView().onDataSuccess(invoiceDetailBean);
            }
        });
    }
}