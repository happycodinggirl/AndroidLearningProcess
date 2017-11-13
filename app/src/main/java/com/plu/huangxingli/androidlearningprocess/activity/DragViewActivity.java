package com.plu.huangxingli.androidlearningprocess.activity;

import android.os.Bundle;

import com.plu.huangxingli.androidlearningprocess.BaseActivity;
import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.view.SwipeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/11/13.
 */

public class DragViewActivity extends BaseActivity {

    @Bind(R.id.swipeLayout)
    SwipeLayout swipeLayout;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_swipe;
    }



    @Override
    protected void initView() {
        super.initView();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        intoBaseOnCreate=true;
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

        ButterKnife.bind(this);
    }
}
