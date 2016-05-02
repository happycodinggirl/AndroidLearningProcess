package com.plu.huangxingli.androidlearningprocess.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;

public class MessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView textView = (TextView) findViewById(R.id.tv_msg);
        /*ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(textView, "translationX", 1080, 0);
        objectAnimator.setDuration(5000);
        objectAnimator.start();*/
        MyHandler myHandler = new MyHandler();
        MyBundle myBundle = new MyBundle();
        myBundle.setName("lily");
        Message message = Message.obtain();
        message.what=0;
        message.obj = myBundle;
        myHandler.sendMessage(message);
        MyBundle myBundle1=new MyBundle();
        myBundle1.setName("hah");
        message.obj=myBundle1;
       // message.what=1;
        myHandler.sendMessage(message);


    }

    static class MyBundle {
        String name;

        public void setName(String name) {
            this.name = name;
        }
    }

    static class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            PluLogUtil.log("what is "+msg.what);
            switch (msg.what){
                case 0:
                    PluLogUtil.log("0----what is 0");
                    break;
            }
        }
    }

}
