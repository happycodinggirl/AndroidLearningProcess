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
        LayoutParams layoutParams=new LayoutParams(100,100);
        addView(eggView,layoutParams);
        View headView=inflater.inflate(R.layout.head_text_layout,this,true);
        mPullStateTv= (TextView) headView.findViewById(R.id.tv_state);
        mPullTimeTv= (TextView) headView.findViewById(R.id.tv_time);

    }

    @Override
    public void onOffsetChange(int offset) {
        PluLogUtil.log("---onOffsetChange "+offset);
        eggView.movePosition(offset);

    }
}
