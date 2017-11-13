package com.plu.huangxingli.androidlearningprocess.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.plu.huangxingli.androidlearningprocess.Utils.LogUtil;

/**
 * Created by Administrator on 2017/11/13.
 */

public class SwipeLayout extends FrameLayout {

    ViewDragHelper viewDragHelper;

    ViewDragHelper.Callback callback;
    TextView dragView;
    TextView toggleView;

    int dragRange;


    public SwipeLayout(@NonNull Context context) {
        super(context);
        init(context);
    }

    public SwipeLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public SwipeLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount()==2){
            dragView= (TextView) getChildAt(1);
            toggleView= (TextView) getChildAt(0);
            LogUtil.d("-->dragViewContent is "+dragView.getText());
            toggleView.post(new Runnable() {
                @Override
                public void run() {
                    dragRange=toggleView.getWidth();
                    LogUtil.d("-->dragRange is "+dragRange);


                }
            });
        }
    }

    private void init(Context context){
        callback=new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return child==dragView;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                return left-dragRange;
            }

            @Override
            public void onEdgeTouched(int edgeFlags, int pointerId) {
                super.onEdgeTouched(edgeFlags, pointerId);
            }

        };
        viewDragHelper=ViewDragHelper.create(this,1.0f,callback);

    }

    @Override
    public void computeScroll() {
        if (viewDragHelper.continueSettling(true)){
            invalidate();
        }
    }
}
