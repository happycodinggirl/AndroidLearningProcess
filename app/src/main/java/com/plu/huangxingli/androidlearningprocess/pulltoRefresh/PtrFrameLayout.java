package com.plu.huangxingli.androidlearningprocess.pulltoRefresh;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;

import okhttp3.internal.ws.RealWebSocket;

/**
 * Created by lily on 16-5-13.
 */
public class PtrFrameLayout extends FrameLayout {

    private View mHeaderView;
    private View mContentView;
    private int nowY;

    private int minTouchDis;

    private int lastY;


    private int maxOffsetDis = 200;
    private int downX;
    private int downY;


    public PtrFrameLayout(Context context) {
        super(context);
        init(context);
    }

    public PtrFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PtrFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        minTouchDis = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public void setHeaderView(View mHeaderView) {
        this.mHeaderView = mHeaderView;
    }

    public void setContentView(View mContentView) {
        this.mContentView = mContentView;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PluLogUtil.eLog("---onTouch");
        int x= (int) event.getX();
        int y= (int) event.getY();

        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                PluLogUtil.eLog("---actionDown");
                downX = (int) event.getX();
                downY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int dis=y-lastY;
                PluLogUtil.log("----dis is " + dis + " y : " + y + ", lastY:" + lastY);
                mContentView.offsetTopAndBottom(dis);
                PluLogUtil.eLog("ddelY > min, nowY: " + nowY + "lastY:" + lastY + " offset : " + (nowY - lastY));
                lastY = y;
                /*MotionEvent motionEvent=MotionEvent.obtain(event);
                motionEvent.setAction(MotionEvent.ACTION_UP);
                mContentView.onTouchEvent(motionEvent);*/

                break;
            case MotionEvent.ACTION_UP:
                PluLogUtil.log("----onTouchEvent ACTION_UP");
                PluLogUtil.eLog("left:" + mContentView.getLeft() + ",top:" + mContentView.getTop() + ",right:" + mContentView.getRight() + ",bottom:" + mContentView.getMeasuredHeight());
                mContentView.layout(mContentView.getLeft(), 0, mContentView.getRight(), mContentView.getMeasuredHeight());
               // mContentView.onTouchEvent(event);
                lastY=0;
                break;
        }

        return true;

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action=ev.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                lastY= (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:


                PluLogUtil.log("---dispatchTouchEvent Action move lastY : "+lastY);
                break;
            case MotionEvent.ACTION_UP:
                lastY=0;
                break;
        }
        boolean dispatchTouchEvent=super.dispatchTouchEvent(ev);
        PluLogUtil.log("---dispatchTouchEvent result is "+dispatchTouchEvent);
        return dispatchTouchEvent;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //PluLogUtil.log("---onInterceptTouchEvent is "+onInterceptTouchEvent);
        int action=ev.getAction();
        PluLogUtil.log("---onInterceptTouchEvent ");
        //PluLogUtil.log("mContentView scrollY is " + mContentView.getScrollY());
        int y= (int) mContentView.getY();
        PluLogUtil.log("---mContentView y is " + y + " top is " + mContentView.getTop());
        Rect rect=new Rect();
       // mContentView.getWindowVisibleDisplayFrame(rect);
        //mContentView.getClipBounds(rect);
      //  mContentView.getGlobalVisibleRect(rect);
        boolean canScroll=mContentView.canScrollVertically(-1);
        PluLogUtil.log("---canScroll is "+canScroll);
        PluLogUtil.log("----rect top is " + rect.top + " rect bottom : " + rect.bottom + " rect left : " + rect.left + " rect right " + rect.right);

        if (action==MotionEvent.ACTION_MOVE){
            int dis= (int) (ev.getY()-lastY);

            if (dis>0&&!canScroll){
                PluLogUtil.log("--onInterceptTouchEvent-return true mCOntentView getY is "+mContentView.getY());
                return true;
            }else{

                PluLogUtil.log("----onInterceptTouchEvent return false "+dis);

                return false;
            }
        }else{
            PluLogUtil.log("---else-onInterceptTouchEvent return false");
            return false;
        }


    }
}
