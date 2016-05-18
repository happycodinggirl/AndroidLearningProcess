package com.plu.huangxingli.androidlearningprocess.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.LinearLayout;

import com.plu.huangxingli.androidlearningprocess.BaseActivity;
import com.plu.huangxingli.androidlearningprocess.R;

/**
 * Created by lily on 16-5-18.
 */
public class IntrestingActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interestingac);
        LinearLayout linearLayout= (LinearLayout) findViewById(R.id.linearlayout);
        /*linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(getResources().getDrawable(R.drawable.bg_divider));*/

    }
}
