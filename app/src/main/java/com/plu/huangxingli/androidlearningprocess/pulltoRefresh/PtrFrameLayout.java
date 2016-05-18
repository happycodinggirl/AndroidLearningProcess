package com.plu.huangxingli.androidlearningprocess.pulltoRefresh;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;

import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;


/**
 * Created by lily on 16-5-13.
 */
public class PtrFrameLayout extends FrameLayout {

    private View mHeaderView;
    private View mContentView;

    private PtrHeaderHandler mPtrHandler;


    private int nowY;

    private int minTouchDis;

    private int lastY;


    private int maxOffsetDis = 300;
    private int downX;
    private int downY;
    private int mCurrentTop;

    private OnRefreshListener mOnRefreshListener;


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

    public void setOnRefreshListener(OnRefreshListener mOnRefreshListener) {
        this.mOnRefreshListener = mOnRefreshListener;
    }

    public void setHeaderView(View mHeaderView) {
        this.mHeaderView = mHeaderView;
        if (mHeaderView instanceof PtrHeaderView){
            mPtrHandler= (PtrHeaderHandler) mHeaderView;
        }
    }



    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount=getChildCount();
        PluLogUtil.log("---childCount is "+childCount);
        if (childCount>2){
            throw new RuntimeException("容器内只能有2个子View");
        }
        if (childCount>0){
            for(int i=0;i<childCount;i++){
                View view=getChildAt(i);
                if (view instanceof PtrHeaderHandler){
                    mPtrHandler= (PtrHeaderHandler) view;
                    mHeaderView=view;
                }else{
                    mContentView=view;
                    if (mContentView!=null){
                        mContentView.bringToFront();
                    }
                }
            }
        }
    }

    public void setContentView(View mContentView) {
        this.mContentView = mContentView;
        mContentView.bringToFront();
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
                PluLogUtil.log("y-downY is " + dis);
                if (y-downY>maxOffsetDis){
                    return true;
                }
                mContentView.offsetTopAndBottom(dis);
                mCurrentTop = mContentView.getTop();
                PluLogUtil.eLog("----ACTION_MOVE top is "+mCurrentTop+" dis is "+dis);

               // PluLogUtil.log("--onTouchEvent--dis is " + dis + " y : " + y + ", downY:" + downY);
                //PluLogUtil.eLog("ddelY > min, nowY: " + nowY + "lastY:" + lastY + " offset : " + (nowY - lastY));
                lastY = y;
                if (mPtrHandler!=null) {
                    mPtrHandler.onOffsetChange(y-downY);
                }

                /*MotionEvent motionEvent=MotionEvent.obtain(event);
                motionEvent.setAction(MotionEvent.ACTION_UP);
                mContentView.onTouchEvent(motionEvent);*/

                break;
            case MotionEvent.ACTION_UP:
                PluLogUtil.log("----onTouchEvent ACTION_UP");
                PluLogUtil.eLog("left:" + mContentView.getLeft() + ",top:" + mContentView.getTop() + ",right:" + mContentView.getRight() + ",bottom:" + mContentView.getMeasuredHeight());
                ((PtrHeaderView)mHeaderView).setCurrentState(EggView.STATE_ON);
                if (mOnRefreshListener!=null){
                    mOnRefreshListener.onRefresh();
                }
                //mContentView.layout(mContentView.getLeft(), 0, mContentView.getRight(), mContentView.getMeasuredHeight());
               // mContentView.onTouchEvent(event);
                lastY=0;
                break;
        }

        return true;

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        PluLogUtil.eLog("----onLayout");
        mContentView.layout(getLeft(),mCurrentTop,getRight(),mCurrentTop+mContentView.getMeasuredHeight());
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action=ev.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                lastY= (int) ev.getY();
                downX = (int) ev.getX();
                downY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:


                PluLogUtil.log("---dispatchTouchEvent Action move lastY : "+lastY);
                break;
            case MotionEvent.ACTION_UP:
                lastY=0;
                break;
        }
        boolean dispatchTouchEvent=super.dispatchTouchEvent(ev);
        PluLogUtil.log("---dispatchTouchEvent result is " + dispatchTouchEvent);
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
       // Rect rect=new Rect();
       // mContentView.getWindowVisibleDisplayFrame(rect);
        //mContentView.getClipBounds(rect);
      //  mContentView.getGlobalVisibleRect(rect);
        boolean canScroll=mContentView.canScrollVertically(-1);
        PluLogUtil.log("---canScroll is "+canScroll);
       // PluLogUtil.log("----rect top is " + rect.top + " rect bottom : " + rect.bottom + " rect left : " + rect.left + " rect right " + rect.right);

        if (action==MotionEvent.ACTION_MOVE){
            int dis= (int) (ev.getY()-lastY);
            PluLogUtil.log("--onIntercept-dis is "+dis+" ev.getY "+ev.getY()+" lastY : "+lastY);

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

    public void completePull() {
        ((PtrHeaderView)mHeaderView).setCurrentState(EggView.STATE_MOVING);
        animBack();
        invalidate();
    }

    private void animBack(){
        PluLogUtil.eLog("---mCurrentTop is " + mCurrentTop);
        ValueAnimator valueAnimator=ValueAnimator.ofInt(mCurrentTop,0);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animValue = (int) animation.getAnimatedValue();
                PluLogUtil.eLog("--animValue is " + animValue);
                mContentView.setTop(animValue);
            }
        });

        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.setDuration(3000);
        valueAnimator.start();
    }

    static interface OnRefreshListener{
        void onRefresh();
    }
}
