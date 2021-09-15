package com.yhhl.yhyq.workbench.projectapproval.presenter;

import com.yhhl.yhyq.base.BaseCallBack;
import com.yhhl.yhyq.base.BaseEntity;
import com.yhhl.yhyq.base.BaseModel;
import com.yhhl.yhyq.base.BasePresenter;
import com.yhhl.yhyq.base.ServerException;
import com.yhhl.yhyq.net.GsonUtil;
import com.yhhl.yhyq.net.HttpUrlUtils;
import com.yhhl.yhyq.workbench.projectapproval.ProjectApprovalView;
import com.yhhl.yhyq.workbench.projectapproval.bean.ProjectApprovalBean;
import java.util.HashMap;
import java.util.List;

public class ProjectApprovalPresenter extends BasePresenter<ProjectApprovalView> {

    private final BaseModel baseModel;

    public ProjectApprovalPresenter(ProjectApprovalView iBaseView) {
        super(iBaseView);
        baseModel = new BaseModel();
    }
    public void find_ProjectApproval(String page){
        HashMap<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("per_page",20);

        baseModel.find_project(HttpUrlUtils.PROJECT_APPROVAL, map, new BaseCallBack<List<ProjectApprovalBean>>() {
            @Override
            public void requestError(ServerException e) {
                getiBaseView().onDataFailed(e.getMessage());
                ServerException.getMessageAndCode();
            }

            @Override
            public void requestSuccess(List<ProjectApprovalBean> projectApprovalBeans) {
                getiBaseView().onDataList(projectApprovalBeans);
            }
        });
    }
    public void add_ProjectApproval(String customer_info,String topic_content,String file_url){
        HashMap<String, Object> map = new HashMap<>();
        map.put("customer_info",customer_info);
        map.put("topic_content",topic_content);
        map.put("file_url",file_url);
        String s = GsonUtil.getInstance().mGson.toJson(map);
        getiBaseView().showLoading();
//        baseModel.add_project(HttpUrlUtils.PROJECT_APPROVAL, s, new BaseCallBack<BaseEntity>() {
//            @Override
//            public void requestError(ServerException e) {
//                getiBaseView().hideLoading();
//                getiBaseView().onDataFailed(e.getMessage());
//            }
//
//            @Override
//            public void requestSuccess(BaseEntity baseEntity) {
//                getiBaseView().onAddSuccess();
//            }
//        });
        baseModel.doPost(HttpUrlUtils.PROJECT_APPROVAL, s, new BaseCallBack() {
            @Override
            public void requestError(ServerException e) {
                getiBaseView().hideLoading();
                getiBaseView().onDataFailed(e.getMessage());
            }

            @Override
            public void requestSuccess(Object o) {
                getiBaseView().hideLoading();
                getiBaseView().onAddSuccess();
            }
        },new BaseEntity());
    }
}