package com.plu.huangxingli.androidlearningprocess.adapter.commonadapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RatingBar;


/**baseAdapter的通用ViewHolder
 * Created by lily on 15-12-8.
 */
public class ComBaseViewHolder implements  Holderable{

    /**
     * abslistview和recycleview桥接的实现类
     */
    ViewHolderImpl viewHolderImpl;

    public ComBaseViewHolder(Context context, ViewGroup parent, int layoutId,
                             int position)
    {
        viewHolderImpl=new ViewHolderImpl(ComBaseViewHolder.this,context,parent,layoutId,position);

    }

    public static ComBaseViewHolder get(Context context, View convertView,
                                 ViewGroup parent, int layoutId, int position)
    {
        if (convertView == null)
        {
            return new ComBaseViewHolder(context, parent, layoutId, position);
        } else
        {
            ComBaseViewHolder holder = (ComBaseViewHolder) convertView.getTag();
            //holder.mPosition = position;
            return holder;
        }
    }


    public int getLayoutId(){
        return viewHolderImpl.getLayoutId();
       // return mLayoutId;
    }

    public View getInflateView(){
        return viewHolderImpl.getInflatedView();
    }

    public void setInflateView(View view){
        viewHolderImpl.setInflatedView(view);
    }



    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @return
     */
    public <T extends View> T findView(int viewId)
    {
        return viewHolderImpl.findView(viewId);
    }
/**
     * 通过viewId获取控件
     *
     * @param viewId
     * @return
     */
    public <T extends View> T findView(int parentId,int viewId)
    {
        return viewHolderImpl.findView(parentId,viewId);
    }

    public View getConvertView()
    {
        return viewHolderImpl.getConvertView();

    }

    /**
     * 设置TextView的值
     *
     * @param viewId
     * @param text
     * @return
     */
    public ComBaseViewHolder setText(int parentRes,int viewId, String text)
    {
        viewHolderImpl.setText(parentRes,viewId,text);
        return this;

    }

    /**
     * 设置TextView的值
     *
     * @param viewId
     * @param text
     * @return
     */
    public ComBaseViewHolder setText(int viewId, CharSequence text)
    {
         viewHolderImpl.setText(viewId,text);
        return this;

    }

    public ComBaseViewHolder setLayoutParem(int viewId,int width,int height){
        viewHolderImpl.setLayoutParem(viewId,width,height);
        return this;
    }

    public ComBaseViewHolder setCompoundDrawablesWithIntrinsicBounds(int viewId,int arg1,int arg2,int arg3,int arg4){

        return  this;
    }


    public ComBaseViewHolder setImageResource(int viewId, int resId)
    {
        viewHolderImpl.setImageResource(viewId,resId);
        return this;
    }

    public ComBaseViewHolder setImageBitmap(int viewId, Bitmap bitmap)
    {
        viewHolderImpl.setImageBitmap(viewId,bitmap);
        return this;
    }

    public ComBaseViewHolder setImageDrawable(int viewId, Drawable drawable)
    {
        viewHolderImpl.setImageDrawable(viewId,drawable);
        return this;
    }

    public ComBaseViewHolder setBackgroundColor(int viewId, int color)
    {
        viewHolderImpl.setBackgroundColor(viewId,color);
        return this;
    }

    public ComBaseViewHolder setBackgroundRes(int viewId, int backgroundRes)
    {
       viewHolderImpl.setBackgroundRes(viewId,backgroundRes);
        return this;
    }

    public ComBaseViewHolder setTextColor(int viewId, int textColor)
    {
        viewHolderImpl.setTextColor(viewId,textColor);
        return this;
    }

    public ComBaseViewHolder setTextColorRes(int viewId, int textColorRes)
    {
        viewHolderImpl.setTextColorRes(viewId,textColorRes);
        return this;
    }

    @SuppressLint("NewApi")
    public ComBaseViewHolder setAlpha(int viewId, float value)
    {
       viewHolderImpl.setAlpha(viewId,value);

        return this;
    }

    public ComBaseViewHolder setVisible(int parentId,int viewId, boolean visible)
    {
        viewHolderImpl.setVisible(parentId,viewId,visible);
        return this;
    }

    public ComBaseViewHolder setVisible(int viewId, boolean visible) {
        return setVisible(viewId, (visible ? View.VISIBLE : View.GONE));
    }

    public ComBaseViewHolder setVisible(int viewId, int visible) {
        viewHolderImpl.setVisible(viewId,visible);
        return this;
    }

    public ComBaseViewHolder linkify(int viewId)
    {
        viewHolderImpl.linkify(viewId);
        return this;
    }

    public ComBaseViewHolder setTypeface(Typeface typeface, int... viewIds)
    {
        viewHolderImpl.setTypeface(typeface,viewIds);
        return this;
    }

    public ComBaseViewHolder setProgress(int viewId, int progress)
    {
        viewHolderImpl.setProgress(viewId,progress);
        return this;
    }

    public ComBaseViewHolder setProgress(int viewId, int progress, int max)
    {
        ProgressBar view = findView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    public ComBaseViewHolder setMax(int viewId, int max)
    {
        ProgressBar view = findView(viewId);
        view.setMax(max);
        return this;
    }

    public ComBaseViewHolder setRating(int viewId, float rating)
    {
        RatingBar view = findView(viewId);
        view.setRating(rating);
        return this;
    }

    public ComBaseViewHolder setRating(int viewId, float rating, int max)
    {
        RatingBar view = findView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    public ComBaseViewHolder setTag(int viewId, Object tag)
    {
        viewHolderImpl.setTag(viewId,tag);
        return this;
    }

    public ComBaseViewHolder setTag(int viewId, int key, Object tag)
    {
        viewHolderImpl.setTag(viewId,key,tag);
        return this;
    }

    public ComBaseViewHolder setChecked(int viewId, boolean checked)
    {
        viewHolderImpl.setChecked(viewId,checked);
        return this;
    }

    /**
     * 关于事件的
     */
    public ComBaseViewHolder setOnClickListener(int viewId,
                                         View.OnClickListener listener)
    {
        viewHolderImpl.setOnClickListener(viewId,listener);
        return this;
    }

    public ComBaseViewHolder setOnTouchListener(int viewId,
                                         View.OnTouchListener listener)
    {
        viewHolderImpl.setOnTouchListener(viewId,listener);
        return this;
    }

    public ComBaseViewHolder setOnLongClickListener(int viewId,
                                             View.OnLongClickListener listener)
    {
       viewHolderImpl.setOnLongClickListener(viewId,listener);
        return this;
    }


}
