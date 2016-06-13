



package com.plu.huangxingli.androidlearningprocess.dagger2.subcomponent;

import dagger.Subcomponent;


/**
 * Created by lily on 16-5-25.
 */


@Subcomponent(modules = {SmallMoudle.class})
public interface SmallComponent {

    //public Child provideChild1(); //注意此处这个方法一定不就可以少,因为该compenent被领外一个component依赖,在Activity中需要到SmallMoudle里面的Child,所以此处要主动提供


        public void inject(ReposListActivity_Sub reposListActivity);
   //     public void inject(TestDagger reposListActivity);

}




