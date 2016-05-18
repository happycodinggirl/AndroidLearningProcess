package com.plu.huangxingli.androidlearningprocess.pulltoRefresh;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.plu.huangxingli.androidlearningprocess.BaseActivity;
import com.plu.huangxingli.androidlearningprocess.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by lily on 16-5-17.
 */
public class PullToRefreshActivity extends BaseActivity {

    private PtrFrameLayout ptrFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pullrefresh);
        ListView listView=findView(R.id.listview);
        ArrayList<String> itemList=new ArrayList<>();
        ptrFrameLayout = findView(R.id.ptrFrameLayout);
        ptrFrameLayout.setOnRefreshListener(new PtrFrameLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                MyHandler myHandler=new MyHandler(PullToRefreshActivity.this);
                myHandler.sendEmptyMessageDelayed(0,3000);

            }
        });
       // ptrFrameLayout.setContentView(listView);
        for (int i=0;i<50;i++){
            itemList.add("item "+i);
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(PullToRefreshActivity.this,android.R.layout.simple_list_item_1,android.R.id.text1,itemList);
        listView.setAdapter(adapter);
    }

    static class MyHandler extends Handler {
        WeakReference<PullToRefreshActivity> wf;

        public MyHandler(PullToRefreshActivity pullToRefreshActivity){
            wf=new WeakReference<PullToRefreshActivity>(pullToRefreshActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            PullToRefreshActivity pullToRefreshActivity=wf.get();
            if (pullToRefreshActivity==null)return;

            pullToRefreshActivity.handleMsg(msg);

        }
    }

    private void handleMsg(Message message){
        ptrFrameLayout.completePull();
    }
}
