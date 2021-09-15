package com.yhhl.yhyq.workbench.task.adapter;

import android.content.Context;

import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.base.CommonBaseAdapter;
import com.yhhl.yhyq.R;
import com.yhhl.yhyq.workbench.task.bean.TaskBean;

import java.util.List;

public class TaskAdapter extends CommonBaseAdapter<TaskBean> {


    public TaskAdapter(Context context, List<TaskBean> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, TaskBean data, int position) {
        holder.setText(R.id.task_coding,"任务编码："+data.getTask_code());
        holder.setText(R.id.task_content,"任务内容："+data.getContent());
        holder.setText(R.id.task_progress,"任务进度："+data.getSchedule()+"%");
        holder.setText(R.id.plan_day,"计划人天："+data.getUnit_num());
        holder.setText(R.id.start_time,"开始时间："+data.getStart_time());
        holder.setText(R.id.end_time,"结束时间："+data.getEnd_time());
        holder.setText(R.id.feedback,"任务反馈："+data.getFeed_back());
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.task_item;
    }
}
