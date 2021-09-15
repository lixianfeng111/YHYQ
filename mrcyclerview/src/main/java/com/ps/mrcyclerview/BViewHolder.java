package com.ps.mrcyclerview;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by PengSong on 18/6/1.
 */

public class BViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;
    private View mConvertView;
    private Context mContext;

    public BViewHolder(Context context,View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
        mContext = context;
        mConvertView = itemView;
    }



    /**
     * 添加波纹效果
     */
    public void addRippleEffectOnClick(){
        getConvertView().setBackgroundResource(R.drawable.recycler_bg);

    }

    public View getConvertView() {
        return mConvertView;
    }

    public void setOnItemClick(View.OnClickListener onItemClick){
        if (onItemClick != null){
            getConvertView().setOnClickListener(onItemClick);
        }
    }

    public void setClickListener(@IdRes int viewId, View.OnClickListener listener){
        findView(viewId).setOnClickListener(listener);
    }

    /**
     * 通过view Id 获取控件
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T findView(@IdRes int viewId){
        View view = mViews.get(viewId);
        if (view == null){
            view = mConvertView.findViewById(viewId);
            if (view == null){
                throw new IllegalArgumentException("在当前的viewHolder中，找不到viewId : " + viewId + " 所对应的控件！！！");
            }
            mViews.put(viewId,view);
        }
        return (T) view;
    }


    public Context getContext() {
        return mContext;
    }

    /**
     * 给textView 设置内容
     * @param viewId
     * @param content
     * @return
     */
    public BViewHolder setText(@IdRes int viewId,String content){
        TextView tv = findView(viewId);
        tv.setText(content);
        return this;
    }

    public TextView getTextView(@IdRes int viewid){
        TextView textView = findView(viewid);
        return textView;
    }

    /**
     * 获取imageView
     * @param viewId
     * @return
     */
    public ImageView getImageView(@IdRes int viewId){
        ImageView imageView = findView(viewId);
        return imageView;
    }


    public RecyclerView getRecyclerView(@IdRes int viewId){
        return findView(viewId);
    }
}
