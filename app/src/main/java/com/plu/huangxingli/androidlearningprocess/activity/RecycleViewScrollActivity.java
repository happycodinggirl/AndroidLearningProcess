package com.plu.huangxingli.androidlearningprocess.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.plu.huangxingli.androidlearningprocess.BaseActivity;
import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.Utils.LogUtil;
import com.plu.huangxingli.androidlearningprocess.adapter.commonadapter.ComRecycleAdapter;
import com.plu.huangxingli.androidlearningprocess.adapter.commonadapter.ComRecycleViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by huangxl on 2016/10/10.
 */
public class RecycleViewScrollActivity extends BaseActivity {

    @Bind(R.id.recycleview)
    RecyclerView recycleview;
    @Bind(R.id.btn)
    Button btn;
    private ScrollAdapter scrollAdapter;
    private List<String> itemList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview_scroll);
        ButterKnife.bind(this);
        itemList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            itemList.add("item " + i);
        }
        scrollAdapter = new ScrollAdapter(RecycleViewScrollActivity.this);
        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(RecycleViewScrollActivity.this);
        recycleview.setLayoutManager(linearLayoutManager);
       // linearLayoutManager.setStackFromEnd(true);
        //linearLayoutManager.setReverseLayout(true);  //recycleView实现倒序排列
      //  linearLayoutManager.setStackFromEnd(true);  //recycleView实现倒序排列
        scrollAdapter.setData(itemList);
        recycleview.setAdapter(scrollAdapter);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  List<String> batchList=new ArrayList<String>();
                for (int i=100;i<150;i++){
                    batchList.add(" item "+i);
                }
                scrollAdapter.batchAdd(batchList);*/
              //  scrollAdapter.addItem("item new_________");
             //   scrollAdapter.notifyDataSetChanged();
             /*   recycleview.smoothScrollToPosition( 50);
                recycleview.scrollToPosition(50);*/
              //  recycleview.scroll
                LogUtil.d("-122221-2222--scrollTo Pos 50");

                /*int firstPos=linearLayoutManager.findFirstVisibleItemPosition();
                View firstItemView=linearLayoutManager.findViewByPosition(firstPos);
                int height=firstItemView.getHeight();
                LogUtil.d("----firstItemView height is "+height);
                int bottom=linearLayoutManager.getDecoratedBottom(firstItemView);
                LogUtil.d("---firstItem bottom is "+bottom);
*/

                linearLayoutManager.scrollToPositionWithOffset(50,0);  //recyleview滚动到指定position的item.且该item位于最顶部,如果直接用scrollToPosition则不一定能使其在最顶部

            }
        });


    }

    class ScrollAdapter extends ComRecycleAdapter<String> {

        public ScrollAdapter(Context context) {
            super(context);
        }

        @Override
        public int getLayoutRes(int type) {
            return R.layout.title_item;
        }

        @Override
        public void bindData(ComRecycleViewHolder recycleViewHolder, int pos) {
            TextView textView = recycleViewHolder.findView(R.id.textview_title);
            textView.setText(getItem(pos));
        }
    }
}
