package com.plu.huangxingli.androidlearningprocess.pulltoRefresh;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.plu.huangxingli.androidlearningprocess.BaseActivity;
import com.plu.huangxingli.androidlearningprocess.R;

import java.util.ArrayList;

/**
 * Created by lily on 16-5-17.
 */
public class PullToRefreshActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pullrefresh);
        ListView listView=findView(R.id.listview);
        ArrayList<String> itemList=new ArrayList<>();
        PtrFrameLayout ptrFrameLayout=findView(R.id.ptrFrameLayout);
        ptrFrameLayout.setContentView(listView);
        for (int i=0;i<50;i++){
            itemList.add("item "+i);
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(PullToRefreshActivity.this,android.R.layout.simple_list_item_1,android.R.id.text1,itemList);
        listView.setAdapter(adapter);
    }
}
