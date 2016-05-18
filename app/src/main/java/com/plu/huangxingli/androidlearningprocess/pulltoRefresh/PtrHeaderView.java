package com.plu.huangxingli.androidlearningprocess.pulltoRefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;

import java.lang.reflect.GenericArrayType;

/**
 * Created by lenovo on 2016/5/17.
 */
public class PtrHeaderView extends LinearLayout implements PtrHeaderHandler {



    private TextView mPullStateTv,mPullTimeTv;
    private EggView eggView;

    private int maxOffset=300;


    public PtrHeaderView(Context context) {
        super(context);
        init(context);
    }

    public PtrHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PtrHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        setGravity(Gravity.CENTER);
        setOrientation(HORIZONTAL);
        LayoutInflater inflater=LayoutInflater.from(context);
        eggView = new EggView(context);
        eggView.setLayoutParams(new MarginLayoutParams(240, 240));
        addView(eggView);
        View headView=inflater.inflate(R.layout.head_text_layout,this,true);
        mPullStateTv= (TextView) headView.findViewById(R.id.tv_state);
        mPullTimeTv= (TextView) headView.findViewById(R.id.tv_time);
      //  addView(headView);

    }

    public void setCurrentState(int statue){
        if (eggView!=null){
            eggView.setCurrentState(statue);
            if (statue==EggView.STATE_ON){
                mPullStateTv.setText("刷新中");
            }
            eggView.invalidate();
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mPullTimeTv.setText("最后更新时间");
    }

    @Override
    public void onOffsetChange(int offset) {
        PluLogUtil.log("---onOffsetChange " + offset);
        if (offset>maxOffset/2){
            mPullStateTv.setText("松开刷新");
        }else{
            mPullStateTv.setText("下拉刷新");
        }
        eggView.movePosition(offset);

    }

    @Override
    public void onAnimAway(int offset) {
       // eggView.setCurrentState(EggView.STATE_MOVING);
        eggView.movePosition(offset);
    }
}
