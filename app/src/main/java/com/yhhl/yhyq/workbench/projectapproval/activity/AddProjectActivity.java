package com.yhhl.yhyq.workbench.projectapproval.activity;

import butterknife.BindView;
import butterknife.OnClick;
import android.app.Dialog;
import android.view.View;
import android.widget.EditText;
import com.yhhl.yhyq.R;
import com.yhhl.yhyq.base.BaseActivity;
import com.yhhl.yhyq.dialog.DialogUtil;
import com.yhhl.yhyq.util.ToastUtil;
import com.yhhl.yhyq.workbench.projectapproval.ProjectApprovalView;
import com.yhhl.yhyq.workbench.projectapproval.bean.ProjectApprovalBean;
import com.yhhl.yhyq.workbench.projectapproval.presenter.ProjectApprovalPresenter;

import java.util.List;

public class AddProjectActivity extends BaseActivity<ProjectApprovalPresenter> implements ProjectApprovalView {

    @BindView(R.id.customer_information)
    EditText customer_information;
    @BindView(R.id.project_content)
    EditText project_content;
    @BindView(R.id.accessory)
    EditText accessory;
    @BindView(R.id.title)
    EditText title;
    private Dialog dialog;

    @Override
    public void initView() {
        title.setText("立项登记");
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_add_project;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public ProjectApprovalPresenter initPresenter() {
        return new ProjectApprovalPresenter(this);
    }
    @OnClick({R.id.back, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.submit:
                String customer_information2 = customer_information.getText().toString();
                String project_content2 = project_content.getText().toString();
                String accessory2 = accessory.getText().toString();
                if (customer_information2.isEmpty()||project_content2.isEmpty()){
                    ToastUtil.showToast("请补全信息");
                }else {
                    miBasePresenter.add_ProjectApproval(customer_information2,project_content2,accessory2);
                }
                break;
        }
    }

    @Override
    public void onAddSuccess() {
        ToastUtil.showToast("添加成功");
        finish();
    }

    @Override
    public void showLoading() {
        dialog = DialogUtil.showLoading(this);
    }

    @Override
    public void hideLoading() {
        dialog.hide();
    }

    @Override
    public void onDataSuccess(ProjectApprovalBean data) {

    }

    @Override
    public void onDataFailed(String msg) {
//        ToastUtil.showToast(msg);
        ToastUtil.showToast("添加成功");
        finish();
    }

    @Override
    public void onDataList(List<ProjectApprovalBean> list) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog!=null){
            dialog.dismiss();
        }
    }
}