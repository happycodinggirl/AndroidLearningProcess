package com.plu.huangxingli.androidlearningprocess.RxJava.operators;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.plu.huangxingli.androidlearningprocess.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.push.lib.util.LogUtil;
import rx.Notification;
import rx.Observable;
import rx.Subscriber;

public class AssistOperators extends AppCompatActivity {


    @Bind(R.id.btn1)
    Button btn1;
    @Bind(R.id.btn2)
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assist_operators);
        ButterKnife.bind(this);



    }

    @OnClick({R.id.btn1,R.id.btn2})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn1:
                Observable.range(1, 3)
                        .materialize()
                        .subscribe(new Subscriber<Notification<Integer>>() {
                @Override
                public void onCompleted() {
                    Log.v("MT","---meterializeObservable onComplete");

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(Notification<Integer> integerNotification) {
                    Log.v("MT"," meterializeObservable  notification getValue is "+integerNotification.getValue()+" getKind is "+integerNotification.getKind());
                }
            });

                break;
            case R.id.btn2:
                deMeterializeObserver().subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        Log.v("MT","---meterializeObservable onComplete");

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integerNotification) {
                        Log.v("MT"," deMeterializeObserver  notification  is "+integerNotification);
                    }
                });
                break;
        }
    }

    private Observable<Notification<Integer>> meterializeObserver() {

        return Observable.just(1, 2, 3).materialize();
    }

    private Observable<Integer> deMeterializeObserver() {
        return meterializeObserver().dematerialize();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_assist_operators, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so longpic
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
