package com.yhhl.yhyq.workbench.invoice.presenter;

import com.yhhl.yhyq.base.BaseCallBack;
import com.yhhl.yhyq.base.BasePresenter;
import com.yhhl.yhyq.base.ServerException;
import com.yhhl.yhyq.net.HttpUrlUtils;
import com.yhhl.yhyq.util.ToastUtil;
import com.yhhl.yhyq.workbench.invoice.bean.InvoiceBean;
import com.yhhl.yhyq.workbench.invoice.model.InvoiceModel;
import com.yhhl.yhyq.workbench.invoice.view.InvoiceView;

import java.util.HashMap;
import java.util.List;

public class InvoicePresenter extends BasePresenter<InvoiceView> {

    private final InvoiceModel invoiceModel;

    public InvoicePresenter(InvoiceView iBaseView) {
        super(iBaseView);
        invoiceModel = new InvoiceModel();
    }
    public void find_expense(String page){
        final HashMap<String,Object> map=new HashMap<>();
        map.put("page",page);
        map.put("per_page","20");
        invoiceModel.find_invoice(HttpUrlUtils.INVOICE, map, new BaseCallBack<List<InvoiceBean>>() {
            @Override
            public void requestError(ServerException e) {
                getiBaseView().onDataFailed("");
                ServerException.getMessageAndCode();
            }

            @Override
            public void requestSuccess(List<InvoiceBean> expenseBeans) {
                getiBaseView().hideLoading();
                if (expenseBeans!=null){
                    getiBaseView().onDataList(expenseBeans);
                }else {
                    ToastUtil.showToast("暂未发现发票");
                }
            }
        });
    }
}