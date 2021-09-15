package com.yhhl.yhyq.workbench.invoice.adapter;

import android.content.Context;

import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.base.CommonBaseAdapter;
import com.yhhl.yhyq.R;
import com.yhhl.yhyq.workbench.invoice.bean.InvoiceBean;

import java.util.List;

public class InvoiceAdapter extends CommonBaseAdapter<InvoiceBean> {

    public InvoiceAdapter(Context context, List<InvoiceBean> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, InvoiceBean data, int position) {
        holder.setText(R.id.company_name,"公司名称："+data.getCompany_name());
        holder.setText(R.id.ITIN,"纳税人识别号："+data.getTaxpayer_number());
        holder.setText(R.id.invoice_value,"发票金额："+data.getAmount());
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.invoice_item;
    }
}
