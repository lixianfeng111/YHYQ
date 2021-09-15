package com.yhhl.yhyq.home;

import android.content.Context;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.base.CommonBaseAdapter;
import com.yhhl.yhyq.R;
import com.yhhl.yhyq.home.bean.BackLogBean;
import com.yhhl.yhyq.util.TimeUtil;

import java.util.List;

public class InformAdapter extends CommonBaseAdapter<BackLogBean> {

    public InformAdapter(Context context, List<BackLogBean> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, BackLogBean data, int position) {
        int is_read = data.getIs_read();
        if (is_read==0){
            holder.setText(R.id.readStatues,"未读");
        }else {
            holder.setText(R.id.readStatues,"已读");
        }
        holder.setText(R.id.inform,data.getSubject());
        holder.setText(R.id.content,data.getContent());
        holder.setText(R.id.time, TimeUtil.removeHMS(data.getCreate_time()));
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.layout_inform_item;
    }
}