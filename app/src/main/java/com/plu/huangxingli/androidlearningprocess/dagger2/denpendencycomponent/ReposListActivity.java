package com.plu.huangxingli.androidlearningprocess.dagger2.denpendencycomponent;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.plu.huangxingli.androidlearningprocess.BaseActivity;
import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;
import com.plu.huangxingli.androidlearningprocess.app.App;


import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lily on 16-5-25.
 */
public class ReposListActivity extends BaseActivity {

    @Bind(R.id.repos_rv_list)
    RecyclerView mRvList;


    //@Inject Student mStudent;

    @Inject
    Child mChild;

    @Inject
    GitHubService mGitHubService;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos_list);
        ButterKnife.bind(this);

       //改activity中依赖注入的类时GitHubService 此处必须要注入
        SmallComponent smallComponent= DaggerSmallComponent.builder().smallMoudle(new SmallMoudle()).build();//貌似不可有多个component注入同一个Activity,若需要多个component,依靠依赖的方式实现
        DaggerDemoComponent.builder().smallComponent(smallComponent).mainMoudle(new MainMoudle(App.getInstance())).apiModule(new ApiModule(App.getInstance())).build().inject(this);
        //DaggerSmallComponent.builder().smallMoudle(new SmallMoudle()).build().inject(this);
        PluLogUtil.eLog("----mChild age is " + mChild.getAge());
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvList.setLayoutManager(manager);

        ListAdapter adapter = new ListAdapter();
        mRvList.setAdapter(adapter);
        loadData(adapter);
       //PluLogUtil.eLog("-----Inject student name is "+mStudent.getName());
    }

    // 加载数据
   private void loadData(final ListAdapter adapter) {
        mGitHubService.getRepoData("SpikeKing")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ArrayList<ListAdapter.Repo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ArrayList<ListAdapter.Repo> repos) {
                            adapter.setRepos(repos);
                    }
                });
    }
}
