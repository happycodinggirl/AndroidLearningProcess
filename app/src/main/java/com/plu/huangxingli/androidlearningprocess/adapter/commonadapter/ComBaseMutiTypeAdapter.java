package com.plu.huangxingli.androidlearningprocess.adapter.commonadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**具有多种类型view的baseAdapter继承此类
 * Created by lily on 15-12-8.
 */
public abstract  class ComBaseMutiTypeAdapter<T> extends ComBaseAdapter<T> {

    MutiTypeInterface<T> mMultiItemTypeSupport;

    public ComBaseMutiTypeAdapter(Context context, List<T> datas, MutiTypeInterface mutiTypeInterface) {
        super(context, datas, -1);
        mMultiItemTypeSupport =mutiTypeInterface;
    }



    @Override
    public int getViewTypeCount() {
        if (mMultiItemTypeSupport !=null){
            return mMultiItemTypeSupport.getViewTypeCount();
        }
        return super.getViewTypeCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (mMultiItemTypeSupport !=null){
            return mMultiItemTypeSupport.getItemViewType(position, mDatas.get(position));
        }
        return super.getItemViewType(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (mMultiItemTypeSupport == null)
            return super.getView(position, convertView, parent);

        int layoutId = mMultiItemTypeSupport.getLayoutId(position,
                getItem(position));
        ComBaseViewHolder viewHolder = ComBaseViewHolder.get(mContext, convertView, parent,
                layoutId, position);
        bindData(viewHolder,position);
        return viewHolder.getConvertView();
    }


}
