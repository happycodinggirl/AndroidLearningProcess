package com.plu.huangxingli.androidlearningprocess.dagger2.subcomponent;

import android.os.Bundle;
import android.widget.TextView;

import com.plu.huangxingli.androidlearningprocess.BaseActivity;
import com.plu.huangxingli.androidlearningprocess.R;

/**
 * Created by lily on 16-6-14.
 */
public class TestInjectActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_inject);
        TextView textView=findView(R.id.tv_inject);
        //FragmentComponent fragmentComponent=

    }

}
