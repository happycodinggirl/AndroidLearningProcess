package com.plu.huangxingli.androidlearningprocess.dagger2;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by lily on 16-5-25.
 */
public interface GitHubService {
    String ENDPOINT = "https://api.github.com";

    // 获取库, 获取的是数组
    @GET("/users/{user}/repos")
    Observable<ArrayList<ListAdapter.Repo>> getRepoData(@Path("user") String user);
}
