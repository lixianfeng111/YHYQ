package com.yhhl.yhyq.workbench.addresslist.adapter;

import android.content.Context;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.base.CommonBaseAdapter;
import com.yhhl.yhyq.R;

import java.util.List;

public class AddressListAdapter extends CommonBaseAdapter {

    public AddressListAdapter(Context context, List datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, Object data, int position) {

    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.address_list_parent_item;
    }
}