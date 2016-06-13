
package com.plu.huangxingli.androidlearningprocess.dagger2.subcomponent;

import com.plu.huangxingli.androidlearningprocess.app.App;

import javax.inject.Singleton;

import dagger.Component;


/**
 * Created by lily on 16-5-25.
 */

@Singleton
@Component(modules={MainMoudle.class,ApiModule.class})
public interface DemoComponent  {

    public SmallComponent provideSmalllComponent();

    void inject(TestDagger testDagger); // 注入列表Activity



/*final class Initial{
        public static DemoComponent  init(App app){
           return DaggerDemoComponent.builder().mainMoudle(new MainMoudle(app)).apiModule(new ApiModule(app)).build();
        }
    }
*/

}
