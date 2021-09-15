package com.yhhl.yhyq.base;

import com.yhhl.yhyq.home.bean.BackLogBean;
import com.yhhl.yhyq.login.bean.LoginBean;
import com.yhhl.yhyq.net.HttpUrlUtils;
import com.yhhl.yhyq.workbench.addresslist.bean.AddressListBean;
import com.yhhl.yhyq.workbench.addresslist.bean.CompanyBean;
import com.yhhl.yhyq.workbench.expense.bean.ExpenseBean;
import com.yhhl.yhyq.workbench.invoice.bean.AddInvoiceBean;
import com.yhhl.yhyq.workbench.invoice.bean.InvoiceBean;
import com.yhhl.yhyq.workbench.invoice.bean.InvoiceDetailBean;
import com.yhhl.yhyq.workbench.projectapproval.bean.ProjectApprovalBean;
import com.yhhl.yhyq.workbench.task.bean.TaskBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface BaseService {
    //登录
    @POST
    @FormUrlEncoded
    Observable<LoginBean> login(@Url String url, @FieldMap Map<String,Object> params);

    @GET
    Observable<List<BackLogBean>> message(@Url String url, @QueryMap Map<String,Object> params);

    @GET
    Observable<List<InvoiceBean>> find_invoice(@Url String url, @QueryMap Map<String,Object> params);
    @GET
    Observable<List<ExpenseBean>> find_expense(@Url String url, @QueryMap Map<String,Object> params);

    @GET
    Observable<InvoiceDetailBean> invoice_detail(@Url String url, @QueryMap Map<String,Object> params);

    @GET
    Observable<List<TaskBean>> find_task(@Url String url, @QueryMap Map<String,Object> params);

    @GET
    Observable<List<ProjectApprovalBean>> find_project(@Url String url, @QueryMap Map<String,Object> params);

    @GET
    Observable<List<AddressListBean>> getList(@Url String url, @QueryMap Map<String,Object> params);

    @GET
    Observable<List<CompanyBean>> getCompany(@Url String url, @QueryMap Map<String,Object> params);
//    @POST(HttpUrlUtils.LOGIN)
//    @FormUrlEncoded
//    Observable<LoginBean> login(@FieldMap Map<String,Object> params);
    @POST
    Observable<Void> add_invoice(@Url String url, @Body RequestBody body);

    @POST
    @FormUrlEncoded
    Observable<BaseEntity> doPost(@Url String url, @FieldMap Map<String, Object> params);

    @POST
    @Multipart
    Observable<BaseEntity> upImage(@Url String url, @Part MultipartBody.Part image);

    @DELETE
    Observable<BaseEntity> doDelete(@Url String url, @QueryMap Map<String, Object> params);

    @PUT
    Observable<BaseEntity> doPut(@Url String url, @QueryMap Map<String, Object> params);
    @POST
    @FormUrlEncoded
    Observable<BaseEntity> doPost(@Url String url, @FieldMap Map<String, Object> params, @HeaderMap Map<String, Object> map);
    @GET
    Observable<BaseEntity> doGettwo(@Url String url);
}
