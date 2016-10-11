package com.plu.huangxingli.androidlearningprocess.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.plu.huangxingli.androidlearningprocess.BaseActivity;
import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.adapter.commonadapter.ComRecycleAdapter;
import com.plu.huangxingli.androidlearningprocess.adapter.commonadapter.ComRecycleViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CustomViewListActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recycleview_customview)
    RecyclerView recycleviewCustomview;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    List<String> itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_list);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CustomViewListActivity.this);
        recycleviewCustomview.setLayoutManager(linearLayoutManager);
        itemList=new ArrayList<>();
        itemList.add("简单自定义View");
        itemList.add("cos函数绘制");
        CustormViewListAdapter custormViewListAdapter=new CustormViewListAdapter(getApplicationContext());
        custormViewListAdapter.setData(itemList);
        custormViewListAdapter.setOnItemClickListener(new ComRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                switch (pos){
                    case 0:
                        toActivity(CustomViewListActivity.this,CustomViewActivity.class);

                        break;
                    case 1:
                        Toast.makeText(CustomViewListActivity.this, "位置 "+pos, Toast.LENGTH_SHORT).show();

                        break;

                }
            }
        });
        recycleviewCustomview.setAdapter(custormViewListAdapter);


    }

    class CustormViewListAdapter extends ComRecycleAdapter<String> {

        public CustormViewListAdapter(Context context) {
            super(context);
        }



        @Override
        public int getLayoutRes(int type) {
            return android.R.layout.simple_list_item_1;
        }

        @Override
        public void bindData(ComRecycleViewHolder recycleViewHolder, int pos) {
            recycleViewHolder.getConvertView().setTag(pos);
            TextView textview = recycleViewHolder.findView(android.R.id.text1);
            String item=getItem(pos);
            textview.setText(item);
        }
    }

}
