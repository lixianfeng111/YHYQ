package com.ps.mrcyclerview;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.ps.mrcyclerview.delegate.NestedItemDelegate;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by PengSong on 18/11/21.
 */

public abstract class NestedItemViewConvert<D extends NestedItemDelegate> implements ItemViewConvert<D> {


    @Override
    public void convert(@NonNull BViewHolder holder, D mData, int position, @NonNull List<Object> payloads) {
        itemConvert(holder,mData,position,payloads);
        int layoutRes = getSubContentView();
        if (layoutRes > 0){
            LinearLayout subContentView = holder.findView(layoutRes);

            List<SubData> subDataList = mData.getSubItemDatas();
            for (int i = 0; i < subDataList.size();i++){
                subContentView.addView(subConvert(holder.getContext(),subDataList.get(i),i));
            }
        }
    }

    public abstract void itemConvert(@NonNull BViewHolder holder, D mData, int position, @NonNull List<Object> payloads);

    /**
     * 获取子View的主Content
     * @return
     */
    public abstract int getSubContentView();

    protected abstract View subConvert(Context context,SubData data, int position);
}
