package com.plu.huangxingli.androidlearningprocess.activity;

import android.os.Bundle;

import com.plu.huangxingli.androidlearningprocess.BaseActivity;
import com.plu.huangxingli.androidlearningprocess.R;

/**
 * Created by lily on 16-5-10.
 */
public class DrawViewActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawview);
        DrawView drawView=findView(R.id.drawview);
        drawView.setImagUrl("https://avatars2.githubusercontent.com/u/6166079?v=3&s=460");
        drawView.setSender("i am sender");
    }
}
