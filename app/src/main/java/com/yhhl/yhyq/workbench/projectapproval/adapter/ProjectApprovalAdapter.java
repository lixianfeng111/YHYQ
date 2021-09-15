package com.yhhl.yhyq.workbench.projectapproval.adapter;

import android.content.Context;

import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.base.CommonBaseAdapter;
import com.yhhl.yhyq.R;
import com.yhhl.yhyq.workbench.projectapproval.bean.ProjectApprovalBean;

import java.util.List;

public class ProjectApprovalAdapter extends CommonBaseAdapter<ProjectApprovalBean> {


    public ProjectApprovalAdapter(Context context, List<ProjectApprovalBean> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, ProjectApprovalBean data, int position) {
        holder.setText(R.id.client_infor,"客户信息："+data.getCustomer_info());
        holder.setText(R.id.project_content,"立项内容："+data.getTopic_content());
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.project_approval_item;
    }
}
