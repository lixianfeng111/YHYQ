package com.yhhl.yhyq.home;

import android.content.Context;

import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.base.CommonBaseAdapter;
import com.yhhl.yhyq.R;
import com.yhhl.yhyq.home.bean.BackLogBean;
import com.yhhl.yhyq.net.LogUtil;
import com.yhhl.yhyq.util.ToastUtil;

import java.util.List;

public class BackLogAdapter extends CommonBaseAdapter<BackLogBean> {

    public BackLogAdapter(Context context, List<BackLogBean> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, BackLogBean data, int position) {
        int priority = data.getPriority();
        if (priority==1){
            holder.setText(R.id.priority,"低级");
        }else if (priority==2){
            holder.setText(R.id.priority,"中级");
        }else {
            holder.setText(R.id.priority,"高级");
        }
        holder.setText(R.id.subject,data.getSubject());
        holder.setText(R.id.content,data.getContent());
        holder.setText(R.id.time,data.getCreate_time());
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.layout_backlog_item;
    }
}
