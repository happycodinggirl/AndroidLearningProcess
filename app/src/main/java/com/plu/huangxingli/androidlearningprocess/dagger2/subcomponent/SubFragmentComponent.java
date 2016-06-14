package com.plu.huangxingli.androidlearningprocess.dagger2.subcomponent;

import dagger.Subcomponent;

/**
 * Created by lily on 16-6-14.
 */

@Subcomponent
public interface SubFragmentComponent {
    void inject(TestScopeFragment testScopeFragment);
}
