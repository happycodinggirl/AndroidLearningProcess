package com.plu.huangxingli.androidlearningprocess.adapter.commonadapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;


/**viewHolder真正实现类
 * Created by lily on 15-12-8.
 */
public class ViewHolderImpl {
    private SparseArray<View> viewArray;
    //private int mPosition;
    private View mConvertView;
    private Context mContext;
    private int mLayoutId;

    public ViewStub viewStub;

    private View inflatedView;

    public ViewHolderImpl(Holderable holderable,Context context, ViewGroup parent, int layoutId,
                            int position)
    {

        mContext = context;
        mLayoutId = layoutId;
        //	this.mPosition = position;
        this.viewArray = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        mConvertView.setTag(holderable);
    }
    public ViewHolderImpl(Holderable holderable,Context context, ViewGroup parent, int layoutId,
                            boolean hasViewStub)
    {

        mContext = context;
        mLayoutId = layoutId;
        //	this.mPosition = position;
        this.viewArray = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        mConvertView.setTag(holderable);
    }

    public ViewHolderImpl(View mConvertView,int layoutId) {
        this.mConvertView = mConvertView;
        this.mLayoutId=layoutId;
        this.viewArray = new SparseArray<View>();
    }


    public View getInflatedView() {
        return inflatedView;
    }

    public void setInflatedView(View inflatedView) {
        this.inflatedView = inflatedView;
    }




    public <T extends View> T findView(int resId){
        View view=viewArray.get(resId);
        if (view==null){
            view=mConvertView.findViewById(resId);
           // Log.v("PLU", "0====FIND VIEW BY ID ");
            viewArray.put(resId,view);
            return (T) view;
        }
        return (T) view;
    }
    public <T extends View> T findView(int parentId,int resId){
        View parentView=findView(parentId);
       return findView(parentView,resId);
    }

    public void setText(int parentId,int resId,String content){
        View parent=findView(parentId);
        TextView textView=findView(parent,resId);
        //Log.v("TAG","-----TEXTVIEW IS "+textView);
        textView.setText(content);
    }

    public <T extends View> T findView(View parent,int resId){
        View view=viewArray.get(resId);
        if (view==null){
            view=parent.findViewById(resId);
            //Log.v("PLU","0====FIND VIEW BY ID ");
            viewArray.put(resId,view);
            return (T) view;
        }
        return (T) view;
    }


    public void setCompoundDrawablesWithIntrinsicBounds(int viewId,int arg1,int arg2,int arg3,int arg4){
        TextView textView=findView(viewId);
        textView.setCompoundDrawablesWithIntrinsicBounds(arg1, arg2, arg3, arg4);
    }

    public void setText(int resId,CharSequence content){
        TextView textView=findView(resId);
        //Log.v("TAG","-----TEXTVIEW IS "+textView);
        textView.setText(content);
    }

    public void setLayoutParem(int viewId,int width,int height){
        View view=findView(viewId);
        ViewGroup.LayoutParams layoutParams=view.getLayoutParams();
        layoutParams.height=height;
        layoutParams.width=width;
        view.setLayoutParams(layoutParams);

    }

    public void setImageResource(int resId,int resource){
        ImageView imageView=findView(resId);
        imageView.setImageResource(resource);
    }
 public void setNetImageResource(int resId,int resource){
        ImageView imageView=findView(resId);
        imageView.setImageResource(resource);
    }

    public View getConvertView() {
        return mConvertView;
    }


    public int getLayoutId(){
        return mLayoutId;
    }

    public int getArraySize(){
        return viewArray.size();
    }




    

    public void  setImageBitmap(int viewId, Bitmap bitmap)
    {
        ImageView view = findView(viewId);
        view.setImageBitmap(bitmap);
        
    }

    public void  setImageDrawable(int viewId, Drawable drawable)
    {
        ImageView view = findView(viewId);
        view.setImageDrawable(drawable);

    }

    public void  setBackgroundColor(int viewId, int color)
    {
        View view = findView(viewId);
        view.setBackgroundColor(color);

    }

    public void  setBackgroundRes(int viewId, int backgroundRes)
    {
        View view = findView(viewId);
        view.setBackgroundResource(backgroundRes);

    }

    public void  setTextColor(int viewId, int textColor)
    {
        TextView view = findView(viewId);
        view.setTextColor(textColor);

    }

    public void  setTextColorRes(int viewId, int textColorRes)
    {
        /*TextView view = findView(viewId);
        view.setTextColor(mContext.getResources().getColor(textColorRes));*/

    }

    @SuppressLint("NewApi")
    public void  setAlpha(int viewId, float value)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
        {
            findView(viewId).setAlpha(value);
        } else
        {
            // Pre-honeycomb hack to set Alpha value
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            findView(viewId).startAnimation(alpha);
        }

    }

    public void  setVisible(int viewId, int visible) {
        View view = findView(viewId);
        view.setVisibility(visible);
    }

    public void  setVisible(int parentId,int viewId, boolean visible)
    {
        View parent=findView(parentId);
        View view = findView(parent,viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);

    }



    public void  linkify(int viewId)
    {
        TextView view = findView(viewId);
        Linkify.addLinks(view, Linkify.ALL);

    }

    public void  setTypeface(Typeface typeface, int... viewIds)
    {
        for (int viewId : viewIds)
        {
            TextView view = findView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }

    }

    public void  setProgress(int viewId, int progress)
    {
        ProgressBar view = findView(viewId);
        view.setProgress(progress);

    }

    public void  setProgress(int viewId, int progress, int max)
    {
        ProgressBar view = findView(viewId);
        view.setMax(max);
        view.setProgress(progress);

    }

    public void  setMax(int viewId, int max)
    {
        ProgressBar view = findView(viewId);
        view.setMax(max);

    }

    public void  setRating(int viewId, float rating)
    {
        RatingBar view = findView(viewId);
        view.setRating(rating);

    }

    public void  setRating(int viewId, float rating, int max)
    {
        RatingBar view = findView(viewId);
        view.setMax(max);
        view.setRating(rating);

    }

    public void  setTag(int viewId, Object tag)
    {
        View view = findView(viewId);
        view.setTag(tag);

    }

    public void  setTag(int viewId, int key, Object tag)
    {
        View view = findView(viewId);
        view.setTag(key, tag);

    }

    public void  setChecked(int viewId, boolean checked)
    {
        Checkable view = (Checkable) findView(viewId);
        view.setChecked(checked);

    }

    /**
     * 关于事件的
     */
    public void  setOnClickListener(int viewId,
                                               View.OnClickListener listener)
    {
        View view = findView(viewId);
        view.setOnClickListener(listener);

    }

    public void  setOnTouchListener(int viewId,
                                               View.OnTouchListener listener)
    {
        View view = findView(viewId);
        view.setOnTouchListener(listener);

    }

    public void  setOnLongClickListener(int viewId,
                                                   View.OnLongClickListener listener)
    {
        View view = findView(viewId);
        view.setOnLongClickListener(listener);

    }
}
