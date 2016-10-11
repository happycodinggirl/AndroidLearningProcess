package com.plu.huangxingli.androidlearningprocess.adapter.commonadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.plu.huangxingli.androidlearningprocess.Utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * recycleview的adapter的通用类
 * Created by lily on 15-12-8.
 */
public abstract class ComRecycleAdapter<T> extends RecyclerView.Adapter<ComRecycleViewHolder> {
    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;

    OnItemClickListener onItemClickListener;

    public ComRecycleAdapter(Context context) {
        this.mContext = context;
        mDatas = new ArrayList();
        mInflater = LayoutInflater.from(context);
    }

    public void setData(List<T> dataList) {
        if (mDatas == null) {
            mDatas = new ArrayList();
        }
        mDatas.clear();
        mDatas.addAll(dataList);



       // notifyDataSetChanged();
    }




    public void addItem(T t){
        if (mDatas!=null){
            mDatas.add(t);
            notifyItemInserted(mDatas.size()-1);
        }
    }





    public T getItem(int pos){
        return mDatas.get(pos);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public abstract int getLayoutRes(int type);

    @Override
    public ComRecycleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
       ComRecycleViewHolder holder = new ComRecycleViewHolder(mInflater.inflate(
                getLayoutRes(i), viewGroup, false), getLayoutRes(i));

       // ComRecycleViewHolder holder = new ComRecycleViewHolder(View.inflate(viewGroup.getContext(),getLayoutRes(i),null), getLayoutRes(i));
        fillInflateView(holder, i);
        return holder;
    }

    @Override
    public void onBindViewHolder(ComRecycleViewHolder recycleViewHolder, int i) {
        recycleViewHolder.getConvertView().setTag(i);
        bindData(recycleViewHolder, i);
    }

    @Override
    public int getItemCount() {
        int count = mDatas == null ? 0 : mDatas.size();
        return count;
    }

    protected   void fillInflateView(ComRecycleViewHolder recycleViewHolder , final int pos){
        final View convertView=recycleViewHolder.getConvertView();
               convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onItemClickListener!=null){
                            onItemClickListener.onItemClick((Integer) convertView.getTag());
                        }
                    }
                });
    }

    public abstract void bindData(ComRecycleViewHolder recycleViewHolder, int pos);

    public interface OnItemClickListener{
        void onItemClick(int pos);
    }

}
