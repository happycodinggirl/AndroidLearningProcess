package com.plu.huangxingli.androidlearningprocess.dagger2.denpendencycomponent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.plu.huangxingli.androidlearningprocess.BaseActivity;
import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.dagger2.denpendencycomponent.ReposListActivity;

/**
 * Created by lily on 16-5-25.
 */
public class TestDagger extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testdagger);



      //  App.getInstance().component().inject(this); //注意此处因为每使用到任何依赖注入的东西，所以此处可以不注入



    }

    public void gotoReposList(View view) {
        startActivity(new Intent(this, ReposListActivity.class));
    }


}
