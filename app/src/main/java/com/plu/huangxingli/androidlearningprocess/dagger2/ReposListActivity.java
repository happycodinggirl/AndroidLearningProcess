package com.plu.huangxingli.androidlearningprocess.dagger2;

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


  //  @Inject Student mStudent;

    @Inject
    GitHubService mGitHubService;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos_list);
        ButterKnife.bind(this);

       App.getInstance().component().inject(this);//改activity中依赖注入的类时GitHubService 此处必须要注入

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvList.setLayoutManager(manager);

        ListAdapter adapter = new ListAdapter();
        mRvList.setAdapter(adapter);
        loadData(adapter);
       // PluLogUtil.eLog("-----Inject student name is "+mStudent.getName());
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
