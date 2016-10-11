package com.plu.huangxingli.androidlearningprocess.adapter.commonadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * baseAdapter的通用类　继承该类实现bindData方法即可
 * Created by lily on 15-12-8.
 */
public abstract class ComBaseAdapter<T> extends BaseAdapter {


    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    private int layoutId;


    int columnNum = 1;

    ViewStubController viewStubController;

    public void setViewStubController(ViewStubController viewStubController) {
        this.viewStubController = viewStubController;
    }


    public ComBaseAdapter(Context context, List<T> datas, int layoutId) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mDatas = datas;
        this.layoutId = layoutId;

        // AsyncTask<> asyncTask;

    }

    public void setColumnNum(int columnNum) {
        this.columnNum = columnNum;
    }

    public int getColumnNum() {
        return columnNum;
    }

    @Override
    public int getCount() {
        try {
            if (mDatas == null) return 0;
            int yushu = mDatas.size() % getColumnNum();
            return mDatas == null ? 0 : yushu == 0 ? mDatas.size() / getColumnNum() : mDatas.size() / getColumnNum() + 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public T getItem(int position) {
        //  return mDatas.subList(position,columnNum);
        return mDatas != null && position < mDatas.size() ? mDatas.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ComBaseViewHolder holder = ComBaseViewHolder.get(mContext, convertView, parent,
                layoutId, position);
        bindData(holder, position);
        if (viewStubController != null) {
            viewStubController.fillInflateView(holder, position);
        }
        return holder.getConvertView();
    }

    /**
     * 填充数据
     *
     * @param data
     * @param isAppend 是否是追加数据
     */
    public void fillData(List<T> data, boolean isAppend) {
        if (data == null) {
            return;
        }
        if (mDatas == null) {
            mDatas = new ArrayList<>();
        }
        if (!isAppend) {
            mDatas.clear();
            mDatas.addAll(data);
        } else {
            for (int i = 0; i < data.size(); i++) {
                T item = data.get(i);
                if (!mDatas.contains(item)) {
                    mDatas.add(item);
                }
            }
        }

        notifyDataSetChanged();
    }


    /**
     * 绑定数据
     *
     * @param holder 　view的holder类
     * @param        　单个数据源
     */
    public abstract void bindData(ComBaseViewHolder holder, int pos);


    public interface ViewStubController {
        //View getView(CommonViewHolder commonViewHolder);
        void fillInflateView(ComBaseViewHolder viewHolder, int pos);


    }
}