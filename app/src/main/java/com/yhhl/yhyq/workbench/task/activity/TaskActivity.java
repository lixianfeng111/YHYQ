package com.yhhl.yhyq.workbench.task.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.interfaces.OnItemClickListener;
import com.othershe.baseadapter.interfaces.OnLoadMoreListener;
import com.ps.mrcyclerview.WrapContentLinearLayoutManager;
import com.yhhl.yhyq.R;
import com.yhhl.yhyq.base.AppManager;
import com.yhhl.yhyq.base.BaseActivity;
import com.yhhl.yhyq.base.BasePresenter;
import com.yhhl.yhyq.dialog.DialogUtil;
import com.yhhl.yhyq.util.IntentUtil;
import com.yhhl.yhyq.workbench.task.adapter.TaskAdapter;
import com.yhhl.yhyq.workbench.task.bean.TaskBean;
import com.yhhl.yhyq.workbench.task.presenter.TaskPresenter;
import com.yhhl.yhyq.workbench.task.view.TaskView;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;

public class TaskActivity extends BaseActivity<TaskPresenter> implements TaskView {

    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipe_layout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private TextView title;
    private ImageView back;
    private Dialog dialog;
    private int n=1;

    @Override
    public void initView() {
        title = findViewById(R.id.title);
        back = findViewById(R.id.back);
        title.setText("任务");
        swipe_layout.setRefreshing(true);
    }

    @Override
    public void initListener() {

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        swipe_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                n=1;
                miBasePresenter.task(n+"");
            }
        });
    }

    @Override
    public void initData() {
        miBasePresenter.task(n+"");
    }

    private void loadMore() {
        n++;
        miBasePresenter.task(n+"");
    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_task;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public TaskPresenter initPresenter() {
        return new TaskPresenter(this);
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void onDataSuccess(TaskBean data) {

    }

    @Override
    public void onDataFailed(String msg) {
        swipe_layout.setRefreshing(false);
    }

    @Override
    public void onDataList(List<TaskBean> list) {
        swipe_layout.setRefreshing(false);
        if (taskAdapter!=null){
            refreshOrLoad(list);
        }else {
            taskAdapter = new TaskAdapter(this, list, true);
            taskAdapter.setLoadingView(R.layout.load_loading_layout);
            taskAdapter.setLoadFailedView(R.layout.load_failed_layout);

            taskAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(boolean isReload) {
                    loadMore();
                }
            });
            taskAdapter.setOnItemClickListener(new OnItemClickListener<TaskBean>() {
                @Override
                public void onItemClick(ViewHolder viewHolder, TaskBean data, int position) {
                    Bundle bundle = new Bundle();
                    bundle.putString("task_coding",data.getTask_code());
                    bundle.putString("task_content",data.getContent());
                    bundle.putString("task_progress",data.getSchedule()+"%");
                    bundle.putString("plan_day",data.getUnit_num()+"");
                    bundle.putString("start_time",data.getStart_time());
                    bundle.putString("end_time",data.getEnd_time());
                    bundle.putString("feedback",data.getFeed_back());
                    IntentUtil.getInstance().intent(TaskActivity.this,TaskDetailActivity.class,bundle);
                }
            });
            WrapContentLinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(taskAdapter);
        }
    }

    private void refreshOrLoad(List<TaskBean> list){
        if (n==1){
            taskAdapter.setNewData(list);
        }else {
            if (list.size()==0){
                taskAdapter.setLoadEndView(R.layout.load_end_layout);
                taskAdapter.loadEnd();
            }else {
                taskAdapter.setLoadMoreData(list);
            }
        }
    }
}