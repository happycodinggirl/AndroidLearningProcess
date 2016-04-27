package com.plu.huangxingli.androidlearningprocess.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;

public class SendedMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sended_message);
        Message message=Message.obtain();
        message.what=0;
        MyHandler myHandler=new MyHandler();
        Message message1=Message.obtain();
        message1.what=1;

        myHandler.sendMessage(message);
        myHandler.sendMessage(message1);
    }

    static class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            PluLogUtil.log("-----handleMessage");
        }
    }
}
