
package com.plu.huangxingli.androidlearningprocess.dagger2.subcomponent;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by lily on 16-5-25.
 */

@UserScope
@Module
public class ApiModule {

    public ApiModule() {
    }

    @Provides
    @Singleton
    public GitHubService provideGithubService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GitHubService.ENDPOINT)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 添加Rx适配器
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .build();
        return retrofit.create(GitHubService.class);
    }
}

