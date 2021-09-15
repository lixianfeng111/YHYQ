package com.yhhl.yhyq.workbench.invoice.presenter;

import com.yhhl.yhyq.base.BaseCallBack;
import com.yhhl.yhyq.base.BaseModel;
import com.yhhl.yhyq.base.BasePresenter;
import com.yhhl.yhyq.base.ServerException;
import com.yhhl.yhyq.net.GsonUtil;
import com.yhhl.yhyq.net.HttpUrlUtils;
import com.yhhl.yhyq.workbench.invoice.bean.AddInvoiceBean;
import com.yhhl.yhyq.workbench.invoice.view.AddInvoiceView;

import java.util.HashMap;

public class AddInvoicePresenter extends BasePresenter<AddInvoiceView> {

    private final BaseModel baseModel;

    public AddInvoicePresenter(AddInvoiceView iBaseView) {
        super(iBaseView);
        baseModel = new BaseModel();
    }
    public void add_invoice(String company_name,String ITIN,String address,String phone,String opening_bank,
                            String bank_account,String invoice_content,String tax_rate,String invoice_value){
        HashMap<String, Object> map = new HashMap<>();
        map.put("company_name",company_name);
        map.put("taxpayer_number",ITIN);
        map.put("address",address);
        map.put("phone",phone);
        map.put("open_bank",opening_bank);
        map.put("bank_account",bank_account);
        map.put("content",invoice_content);
        map.put("amount",invoice_value);
        map.put("tax_rate",tax_rate);
        String s = GsonUtil.getInstance().mGson.toJson(map);
        getiBaseView().showLoading();
        baseModel.doPost(HttpUrlUtils.INVOICE, s, new BaseCallBack() {
            @Override
            public void requestError(ServerException e) {
                getiBaseView().hideLoading();
//                ServerException.getMessageAndCode();
            }

            @Override
            public void requestSuccess(Object o) {
                getiBaseView().hideLoading();
                getiBaseView().onDataSuccess(o);
            }
        },new AddInvoiceBean());

    }
}