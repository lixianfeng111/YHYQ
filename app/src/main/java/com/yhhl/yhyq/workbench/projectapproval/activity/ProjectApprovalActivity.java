package com.yhhl.yhyq.workbench.projectapproval.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.interfaces.OnItemClickListener;
import com.othershe.baseadapter.interfaces.OnLoadMoreListener;
import com.ps.mrcyclerview.WrapContentLinearLayoutManager;
import com.yhhl.yhyq.R;
import com.yhhl.yhyq.base.BaseActivity;
import com.yhhl.yhyq.base.BasePresenter;
import com.yhhl.yhyq.dialog.DialogUtil;
import com.yhhl.yhyq.util.IntentUtil;
import com.yhhl.yhyq.util.ToastUtil;
import com.yhhl.yhyq.workbench.invoice.activity.AddInvoiceActivity;
import com.yhhl.yhyq.workbench.invoice.bean.InvoiceBean;
import com.yhhl.yhyq.workbench.projectapproval.ProjectApprovalView;
import com.yhhl.yhyq.workbench.projectapproval.adapter.ProjectApprovalAdapter;
import com.yhhl.yhyq.workbench.projectapproval.bean.ProjectApprovalBean;
import com.yhhl.yhyq.workbench.projectapproval.presenter.ProjectApprovalPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.OnClick;

public class ProjectApprovalActivity extends BaseActivity<ProjectApprovalPresenter> implements ProjectApprovalView {

    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipe_layout;
    private RecyclerView recyclerView;
    private ImageView back;
    private TextView title;
    private ProjectApprovalAdapter approvalAdapter;
    private ImageView right_icon;
    private int num=1;

    @Override
    public void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        title = findViewById(R.id.title);
        right_icon = findViewById(R.id.right_icon);
        title.setText("立项登记");
        right_icon.setVisibility(View.VISIBLE);
        swipe_layout.setRefreshing(true);
    }

    @Override
    public void initListener() {
        swipe_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                num=1;
                miBasePresenter.find_ProjectApproval(num+"");
            }
        });
    }

    @Override
    public void initData() {
        miBasePresenter.find_ProjectApproval(num+"");
    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_project_approval;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public ProjectApprovalPresenter initPresenter() {
        return new ProjectApprovalPresenter(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onDataSuccess(ProjectApprovalBean data) {

    }

    @Override
    public void onDataFailed(String msg) {
        swipe_layout.setRefreshing(false);
    }

    @Override
    public void onDataList(List<ProjectApprovalBean> list) {
        swipe_layout.setRefreshing(false);
        if (approvalAdapter!=null){
            refreshOrLoad(list);
        }else {
            approvalAdapter = new ProjectApprovalAdapter(this, list, true);
            approvalAdapter.setLoadingView(R.layout.load_loading_layout);
            approvalAdapter.setLoadFailedView(R.layout.load_failed_layout);
            approvalAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(boolean isReload) {
                    loadMore();
                }
            });
            approvalAdapter.setOnItemClickListener(new OnItemClickListener<ProjectApprovalBean>() {
                @Override
                public void onItemClick(ViewHolder viewHolder, ProjectApprovalBean data, int position) {
                    Bundle bundle = new Bundle();
                    bundle.putString("client_infor",data.getCustomer_info());
                    bundle.putString("project_content",data.getTopic_content());
                    IntentUtil.getInstance().intent(ProjectApprovalActivity.this, ProjectDetailActivity.class,bundle);
                }
            });
            WrapContentLinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(approvalAdapter);
        }
    }

    private void loadMore() {
        num++;
        miBasePresenter.find_ProjectApproval(num+"");
    }

    @OnClick({R.id.back, R.id.right_icon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.right_icon:
                IntentUtil.getInstance().intent(this, AddProjectActivity.class,null);
                break;
        }
    }
    private void refreshOrLoad(List<ProjectApprovalBean> list){
        if (num==1){
            approvalAdapter.setNewData(list);
        }else {
            if (list.size()==0){
                approvalAdapter.setLoadEndView(R.layout.load_end_layout);
                approvalAdapter.loadEnd();
            }else {
                approvalAdapter.setLoadMoreData(list);
            }
        }
    }

    @Override
    public void onAddSuccess() {

    }
}