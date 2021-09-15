package com.yhhl.yhyq.workbench.task.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yhhl.yhyq.R;
import com.yhhl.yhyq.base.BaseActivity;
import com.yhhl.yhyq.base.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class TaskDetailActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.task_coding)
    TextView task_coding;
    @BindView(R.id.task_content)
    TextView task_content;
    @BindView(R.id.task_progress)
    TextView task_progress;
    @BindView(R.id.plan_day)
    TextView plan_day;
    @BindView(R.id.start_time)
    TextView start_time;
    @BindView(R.id.end_time)
    TextView end_time;
    @BindView(R.id.feedback)
    TextView feedback;
    @Override
    public void initView() {
        title.setText("任务详情");
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getExtras();

        if (bundle!=null){
            task_coding.setText(bundle.getString("task_coding"));
            task_content.setText(bundle.getString("task_content"));
            task_progress.setText(bundle.getString("task_progress"));
            plan_day.setText(bundle.getString("plan_day"));
            start_time.setText(bundle.getString("start_time"));
            end_time.setText(bundle.getString("end_time"));
            feedback.setText(bundle.getString("feedback"));
        }
    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_task_detail;
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