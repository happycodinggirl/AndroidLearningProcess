package com.plu.huangxingli.androidlearningprocess;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;


public  class BaseActivity extends AppCompatActivity {

    protected boolean intoBaseOnCreate=false;
    public void toActivity(Context context,Class<?> cls){
        Intent intent = new Intent(context,cls);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (intoBaseOnCreate) {
            setContentView(getLayoutRes());
            ButterKnife.bind(this);
            initView();
            init();
        }


    }

    public <T extends View> T findView(int resId){

        return (T)findViewById(resId);
    }

    protected void init(){}
    protected void initView(){}

    protected  int getLayoutRes() {
        return 0;
    }





}
