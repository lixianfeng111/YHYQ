package com.yhhl.yhyq.workbench.addresslist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.yhhl.yhyq.R;
import com.yhhl.yhyq.workbench.addresslist.bean.CompanyBean;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by zhangxutong .
 * Date: 16/08/28
 */

public class PopDepartAdapter extends RecyclerView.Adapter<PopDepartAdapter.ViewHolder> {
    private Context mContext;
    private List<CompanyBean> mDatas;
    private LayoutInflater mInflater;
    private String department;
    public PopDepartAdapter(Context mContext, List<CompanyBean> mDatas, String department) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.department=department;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.popu_department_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final CompanyBean companyBean = mDatas.get(position);
        String name = companyBean.getName();
        if (name.equals(department)){
            holder.department.setVisibility(View.GONE);
        }else {
            holder.department.setText(name);
            holder.department.setVisibility(View.VISIBLE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChangeDepartmentListener.dept_name(name,companyBean.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView department;

        public ViewHolder(View itemView) {
            super(itemView);
            department = (TextView) itemView.findViewById(R.id.department);
        }
    }
    private OnChangeDepartmentListener onChangeDepartmentListener;

    public interface OnChangeDepartmentListener{
        void dept_name(String name,int id);
    }
    public void setOnChangeDepartmentListener(OnChangeDepartmentListener onChangeDepartmentListener){
        this.onChangeDepartmentListener=onChangeDepartmentListener;
    }
}
