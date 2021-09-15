package com.yhhl.yhyq.workbench.invoice.model;

import com.yhhl.yhyq.base.BaseCallBack;
import com.yhhl.yhyq.base.BaseObserver;
import com.yhhl.yhyq.base.BaseService;
import com.yhhl.yhyq.net.HttpUrlUtils;
import com.yhhl.yhyq.net.LogUtil;
import com.yhhl.yhyq.net.RetrofitUtil;
import com.yhhl.yhyq.util.ToastUtil;
import com.yhhl.yhyq.workbench.invoice.bean.InvoiceBean;
import com.yhhl.yhyq.workbench.invoice.bean.InvoiceDetailBean;

import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class InvoiceDetailModel {
    public void invoice_detail(HashMap<String,Object> map, BaseCallBack<InvoiceDetailBean> callBack){
        BaseService baseService = RetrofitUtil.getInstance().createApi(BaseService.class);
        baseService.invoice_detail(HttpUrlUtils.INVOICE,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<InvoiceDetailBean>(callBack){
                    @Override
                    public void onNext(InvoiceDetailBean invoiceDetailBeans) {
                        super.onNext(invoiceDetailBeans);
                        callBack.requestSuccess(invoiceDetailBeans);

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
