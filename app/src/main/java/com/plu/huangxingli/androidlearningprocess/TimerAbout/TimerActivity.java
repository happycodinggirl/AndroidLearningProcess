package com.plu.huangxingli.androidlearningprocess.TimerAbout;

import android.app.Activity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

import com.plu.huangxingli.androidlearningprocess.BaseActivity;

/**
 * Created by lily on 16-1-5.
 */
public class TimerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        double powValue = Math.pow(4, 2);
        Log.v("MT","---powValue is "+powValue);
        CountDownTimer countDownTimer = new CountDownTimer(3000, 3000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.v("PLU", "-----onTick");
            }

            @Override
            public void onFinish() {
                Log.v("PLU", "-----onFinish");
            }
        };
        countDownTimer.start();
    }
}
