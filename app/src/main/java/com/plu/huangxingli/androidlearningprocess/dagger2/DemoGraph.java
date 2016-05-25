package com.plu.huangxingli.androidlearningprocess.dagger2;

import com.plu.huangxingli.androidlearningprocess.MainActivity;

/**
 * Created by lily on 16-5-25.
 */
public interface DemoGraph {


    void inject(TestDagger testDagger); // 注入列表Activity
    void inject(ReposListActivity reposListActivity); // 注入列表Activity
}
