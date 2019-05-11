package me.newtrekwang.lib_base.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <T>
 * @param <VH>
 * @author newtrekWang
 * @className BaseRecyclerViewAdapter
 * @createDate 2018/7/16 9:17
 * @email 408030208@qq.com
 * @desc RecyclerViewAdapter 基类
 */
public abstract class BaseRecyclerViewAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    /**
     * 数据模型
     */
    protected List<T> dataList = new ArrayList<>();

    /**
     * item点击监听器
     */
    protected OnItemClickListener<T> onItemClickListener;

    /**
     * 更新整个列表
     *
     * @param data
     */
    public void setData(List<T> data) {
        dataList.clear();
        dataList.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     * @param data
     */
    public void addData(List<T> data){
        int lastEndPosition = dataList.size()-1;
        dataList.addAll(data);
        notifyItemRangeChanged(lastEndPosition,data.size());
    }

    /**
     * 清除所有数据
     */
    public void clearData(){
        dataList.clear();
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(@NonNull VH holder,final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(dataList.get(position), position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    /**
     * item 点击监听器
     *
     * @param <T>
     */
    public interface OnItemClickListener<T> {
        void onItemClick(T item, int position);
    }

    public List<T> getDataList() {
        return dataList;
    }

    public OnItemClickListener<T> getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


}
