package com.plu.huangxingli.androidlearningprocess.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class LinkedBlockingDequeTest extends AppCompatActivity {

    private LinkedBlockingDeque blockingQueue;

    private int addNum;
    private boolean addThreadAlive=true;
    private boolean removeThreadAlive=true;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        blockingQueue = new LinkedBlockingDeque();
        new Thread(new AddRunnable()).start();
        new Thread(new AddRunnable()).start();
        new Thread(new RemoveRunnble()).start();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                blockingQueue.addFirst(100000);
            }
        }, 6000);
       // new Thread(new RemoveRunnble()).start();
    }

    class AddRunnable implements java.lang.Runnable{


        @Override
        public void run() {
            while(addThreadAlive) {
                synchronized (blockingQueue) {
                    blockingQueue.add(addNum);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    PluLogUtil.log("---&******size is " + blockingQueue.size()+"__addNum is "+addNum);
                    addNum++;
                }
            }
        }
    }

    class RemoveRunnble implements Runnable{

        @Override
        public void run() {
            while (removeThreadAlive){
                try {
                    int takeNum= (int) blockingQueue.take();
                    Thread.sleep(2000);
                    PluLogUtil.log("-_________________________________--take num is "+takeNum+"  size is "+blockingQueue.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeThreadAlive=false;
        addThreadAlive=false;
        timer.cancel();
        timer=null;
    }
}
