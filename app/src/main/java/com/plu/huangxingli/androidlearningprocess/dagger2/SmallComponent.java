



package com.plu.huangxingli.androidlearningprocess.dagger2;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Provides;




/**
 * Created by lily on 16-5-25.
 */


@Component(modules = {SmallMoudle.class})
public interface SmallComponent {

    public Child provideChild1();



   //     public void inject(TestDagger reposListActivity);

}




