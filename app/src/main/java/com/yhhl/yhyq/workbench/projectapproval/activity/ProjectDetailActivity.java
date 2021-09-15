package com.yhhl.yhyq.workbench.projectapproval.activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.OnClick;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yhhl.yhyq.R;
import com.yhhl.yhyq.base.BaseActivity;
import com.yhhl.yhyq.base.BasePresenter;
import com.yhhl.yhyq.util.IntentUtil;

public class ProjectDetailActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.client_infor)
    TextView client_infor;
    @BindView(R.id.project_content)
    TextView project_content;

    @Override
    public void initView() {
        title.setText("立项详情");
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getExtras();
        String infor = bundle.getString("client_infor");
        String content = bundle.getString("project_content");
        client_infor.setText(infor);
        project_content.setText(content);
    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_project_detail;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @OnClick({R.id.back, R.id.back_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.back_btn:
                finish();
                break;
        }
    }

}