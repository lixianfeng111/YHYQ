package com.yhhl.yhyq.base;


import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 *
 * @param <VH>
 * @param <E>
 *     BaseRecyclerAdapter
 */
public abstract class BaseRecyclerAdapter<VH extends RecyclerView.ViewHolder,E> extends RecyclerView.Adapter<VH> {
    private List<E> listData = new ArrayList<>();
    @Override
    public int getItemCount() {
        return listData.size();
    }
    //添加集合
    public void addList(List<E> list){
        if (list!=null&&list.size()>0){
            listData.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void onRefresh(List<E> list){
        if (list!=null&&list.size()>0){
            listData.clear();
            listData.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void clearList(){
        listData.clear();
        notifyDataSetChanged();
    }
    public E getItem(int positio){
        return listData.get(positio);
    }
    //添加单个数据
    public void append(E o){
        listData.add(o);
        notifyItemInserted(listData.size()-2);
    }
    public void appendHead(E o){
        listData.add(0,o);
        notifyItemInserted(0);
    }
    //删除单个数据的方法
    public void remove(E o){
        listData.remove(o);
        notifyDataSetChanged();
    }
    //删除集合的方法
    public void removeList(List list){
        if(list!=null||list.size()>0){
            if(list!=null||list.size()>0){
                listData.removeAll(list);
            }
        }
    }
    //修改
    public void changeItem(E change,int position){
        if(change!=null){
            for (int i = 0; i <listData.size() ; i++) {
                listData.set(position,change);
            }
        }
        notifyDataSetChanged();
    }
}
