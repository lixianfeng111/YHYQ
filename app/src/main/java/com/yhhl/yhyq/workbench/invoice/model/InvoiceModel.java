package com.yhhl.yhyq.workbench.invoice.model;

import com.yhhl.yhyq.base.BaseCallBack;
import com.yhhl.yhyq.base.BaseObserver;
import com.yhhl.yhyq.base.BaseService;
import com.yhhl.yhyq.net.RetrofitUtil;
import com.yhhl.yhyq.util.ToastUtil;
import com.yhhl.yhyq.workbench.expense.bean.ExpenseBean;
import com.yhhl.yhyq.workbench.invoice.bean.InvoiceBean;

import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class InvoiceModel {
    public void find_invoice(String url, HashMap<String,Object> map, final BaseCallBack<List<InvoiceBean>> callBack){
        BaseService baseService = RetrofitUtil.getInstance().createApi(BaseService.class);
        baseService.find_invoice(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<InvoiceBean>>(callBack){
                    @Override
                    public void onNext(List<InvoiceBean> expenseBeans) {
                        super.onNext(expenseBeans);
                        callBack.requestSuccess(expenseBeans);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
