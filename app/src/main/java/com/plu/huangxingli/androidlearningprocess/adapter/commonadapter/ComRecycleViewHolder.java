package com.plu.huangxingli.androidlearningprocess.adapter.commonadapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**recycleview adapter的通用holder
 * Created by lily on 15-12-8.
 */
public class ComRecycleViewHolder extends RecyclerView.ViewHolder {

    /**
     * 使用该类统一recycleview和listview填充的行为实现
     */
    ViewHolderImpl viewHolderImpl;
    int layoutRes;


    public ComRecycleViewHolder(View view, int resLayout) {
        super(view);
        this.layoutRes=resLayout;
        viewHolderImpl=new ViewHolderImpl(view,resLayout);
    }

    public int getLayoutId() {
        return viewHolderImpl.getLayoutId();
    }

    public void setText(int resId,String content){
        viewHolderImpl.setText(resId,content);
    }


    /**
     * 获取ConvertView
     */
    public View getConvertView() {
        return viewHolderImpl.getConvertView();
    }

    /**
     * 通过viewId获取控件
     *
     * @param viewId
     */
    public <T extends View> T findView(int viewId) {
        return viewHolderImpl.findView(viewId);
    }




}
