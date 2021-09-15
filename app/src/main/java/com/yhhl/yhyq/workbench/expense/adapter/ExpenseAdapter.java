package com.yhhl.yhyq.workbench.expense.adapter;

import android.content.Context;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.base.CommonBaseAdapter;
import com.yhhl.yhyq.R;
import com.yhhl.yhyq.workbench.expense.bean.ExpenseBean;
import java.util.List;

public class ExpenseAdapter extends CommonBaseAdapter<ExpenseBean> {

    public ExpenseAdapter(Context context, List<ExpenseBean> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, ExpenseBean data, int position) {
        holder.setText(R.id.organization,data.getCompany_name());
        holder.setText(R.id.expense_user,data.getBill_user_name());
        holder.setText(R.id.expense_money,data.getBill_value()+"");
        holder.setText(R.id.note,data.getRemark());
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.expense_item;
    }
}
