package com.plu.huangxingli.androidlearningprocess.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;



public class StartActivityForResultTest extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_activity_for_result_test);
        Button button= (Button) findViewById(R.id.btn_jump_ac);
        //1.待启动Activity的LauchMode若设置为了singleTask,则startActivityResult时来源activity的onActivityResult在进入目标页面就回调
        //回来了。（不管是在activity里面还是fragment里面调用startActivtyForResult）
        //2.在fragment里面调用startActivityForResult会响应到寄宿Activity和自身的onActivityResult里面去,但要注意的是寄宿activityResult里面的requestCode不是传进去的requestCode,
        // 自身fragment的onActivityResult里面的requestCode是当初传进去的那个值
        //在fragment里面调用getActivity.startActivityForResult只会调到寄宿的activity的onActivityResult里面去。且requestCode是传进去的requestCode
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StartActivityForResultTest.this, CustomViewActivity.class);
                startActivityForResult(intent,10);
            }
        });
    }

   @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PluLogUtil.log("---Activity onActivity Result request code "+requestCode);
    }
}
