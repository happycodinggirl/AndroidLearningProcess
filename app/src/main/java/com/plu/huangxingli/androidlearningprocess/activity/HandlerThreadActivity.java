package com.plu.huangxingli.androidlearningprocess.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.plu.huangxingli.androidlearningprocess.BaseActivity;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;
import com.plu.huangxingli.androidlearningprocess.bean.Student;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lily on 16-1-18.
 */
public class HandlerThreadActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ExecutorService executorService= Executors.newCachedThreadPool();
        HandlerThread handlerThread=new HandlerThread("test");
        executorService.execute(new MyHandlerThread("TEST"));
        MyHandler myHandler=new MyHandler();
        Student student=new Student("lily");
        Message message1=Message.obtain();
        message1.obj=student;
        message1.what=0;
        myHandler.sendMessageDelayed(message1, 4000);
        student.setName("lucy");

        Message message2=Message.obtain();
        message2.what=1;
        message2.obj=student;
        myHandler.sendMessageDelayed(message2,1000);

     /*   Handler handler=new MyHandler(handlerThread.getLooper());
        Message message=handler.obtainMessage();
        message.sendToTarget();*/


    }

    class MyHandlerThread extends HandlerThread{

        public MyHandlerThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            super.run();

            PluLogUtil.log(HandlerThreadActivity.class,"0---myHandleThread run thread name is "+Thread.currentThread().getName());
        }
    }

    class MyHandler extends Handler{


        public MyHandler() {
            super();

        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            PluLogUtil.log(HandlerThreadActivity.class,"---handleMsg");
            switch (msg.what){
                case 0:
                    Student student= (Student) msg.obj;
                    PluLogUtil.eLog("---000000 name is "+student.getName());
                    break;
                case 1:
                    Student student1= (Student) msg.obj;
                    PluLogUtil.eLog("-----1111111 name is "+student1.getName());
                    break;
            }

        }
    }

    class MyThread extends Thread{
        @Override
        public void run() {
            super.run();
            PluLogUtil.log(HandlerThreadActivity.class,"----run -----");
        }
    }
}
