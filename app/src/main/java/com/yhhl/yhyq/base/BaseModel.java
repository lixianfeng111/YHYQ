package com.yhhl.yhyq.base;

import com.yhhl.yhyq.net.RetrofitUtil;
import com.yhhl.yhyq.util.ToastUtil;
import com.yhhl.yhyq.workbench.addresslist.bean.AddressListBean;
import com.yhhl.yhyq.workbench.addresslist.bean.CompanyBean;
import com.yhhl.yhyq.workbench.projectapproval.bean.ProjectApprovalBean;
import com.yhhl.yhyq.workbench.task.bean.TaskBean;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class BaseModel {

    private BaseObserver listBaseObserver;

    //查询任务列表
    public void find_task(String url, HashMap<String,Object> map, final BaseCallBack<List<TaskBean>> callBack){
        BaseService baseService = RetrofitUtil.getInstance().createApi(BaseService.class);
        baseService.find_task(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<TaskBean>>(callBack){
                    @Override
                    public void onNext(List<TaskBean> expenseBeans) {
                        super.onNext(expenseBeans);
                        callBack.requestSuccess(expenseBeans);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
    //查询立项列表
    public void find_project(String url, HashMap<String,Object> map, final BaseCallBack<List<ProjectApprovalBean>> callBack){
        BaseService baseService = RetrofitUtil.getInstance().createApi(BaseService.class);
        baseService.find_project(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<ProjectApprovalBean>>(callBack){
                    @Override
                    public void onNext(List<ProjectApprovalBean> approvalBeanList) {
                        super.onNext(approvalBeanList);
                        callBack.requestSuccess(approvalBeanList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
    //查询公司和部门
    public void getCompany(String url, HashMap<String,Object> map, final BaseCallBack<List<CompanyBean>> callBack){
        BaseService baseService = RetrofitUtil.getInstance().createApi(BaseService.class);
        baseService.getCompany(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<CompanyBean>>(callBack){
                    @Override
                    public void onNext(List<CompanyBean> companyBeans) {
                        super.onNext(companyBeans);
                        callBack.requestSuccess(companyBeans);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    //查询通讯录
    public void getList(String url, HashMap<String,Object> map, final BaseCallBack<List<AddressListBean>> callBack){
        BaseService baseService = RetrofitUtil.getInstance().createApi(BaseService.class);
        baseService.getList(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<AddressListBean>>(callBack){
                    @Override
                    public void onNext(List<AddressListBean> addressListBeans) {
                        super.onNext(addressListBeans);
                        callBack.requestSuccess(addressListBeans);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

//    public void add_project(String url, String s, final BaseCallBack<BaseEntity> callBack){
//        BaseService baseService = RetrofitUtil.getInstance().createApi(BaseService.class);
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),s);
//        baseService.add_invoice(url,requestBody)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new BaseObserver<BaseEntity>(callBack){
//                    @Override
//                    public void onNext(BaseEntity expenseBeans) {
//                        super.onNext(expenseBeans);
//                        callBack.requestSuccess(expenseBeans);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        super.onError(e);
////                        ToastUtil.showToast(e.getMessage());
//                    }
//                });
//
//    }

    public <T> void doPost(String url, String s, final BaseCallBack callBack, T t){
        BaseService baseService = RetrofitUtil.getInstance().createApi(BaseService.class);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),s);
        getBaseObserver(callBack);
        baseService.add_invoice(url,requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listBaseObserver);
        listBaseObserver.onNext(t);
    }


    private BaseObserver getBaseObserver(BaseCallBack callBack){
        if (listBaseObserver==null){
            listBaseObserver = new BaseObserver<>(callBack);
            return listBaseObserver;
        }else {
            return listBaseObserver;
        }
    }


    public <T> void doGet(String url, HashMap<String,Object> map, String s, final BaseCallBack callBack, T t){
        BaseService baseService = RetrofitUtil.getInstance().createApi(BaseService.class);
        getBaseObserver(callBack);
        switch (s){
            case "task":
                baseService.find_task(url,map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(listBaseObserver);
                break;
            case "project":
                baseService.find_project(url,map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(listBaseObserver);
                break;
        }
        listBaseObserver.onNext(t);
    }
}
