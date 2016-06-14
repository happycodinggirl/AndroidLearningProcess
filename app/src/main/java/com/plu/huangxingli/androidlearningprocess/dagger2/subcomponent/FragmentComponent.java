package com.plu.huangxingli.androidlearningprocess.dagger2.subcomponent;

import dagger.Component;
import rx.Subscription;

/**
 * Created by lily on 16-6-13.
 */
@Component(modules = {FragmentMould.class})
public interface FragmentComponent {

    //SubFragmentComponent provideSubFragmentComponent();
    void inject(TestScopeFragment testScopeFragment);


}
