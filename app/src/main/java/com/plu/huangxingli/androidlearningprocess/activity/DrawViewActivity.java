package com.plu.huangxingli.androidlearningprocess.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.plu.huangxingli.androidlearningprocess.BaseActivity;
import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;

import java.security.spec.PKCS8EncodedKeySpec;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by lily on 16-5-10.
 */
public class DrawViewActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawview);
        DrawView drawView=findView(R.id.drawview);
        drawView.setImagUrl("https://avatars2.githubusercontent.com/u/6166079?v=3&s=460");
        drawView.setSender("i am sender");
        final Button textView=findView(R.id.tv_test);
        textView.post(new Runnable() {
            @Override
            public void run() {
                int top=textView.getTop();
                int paddingTop=textView.getPaddingTop();//注意top和paddingTop的区别，xml里面paddingTop为3，此处getTop为0，getPaddingTop为3
                PluLogUtil.eLog("----top is "+top+" paddingTop is "+paddingTop);
            }
        });

        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action=event.getAction();
                switch (action){
                    case MotionEvent.ACTION_DOWN:
                        PluLogUtil.eLog("----action DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        PluLogUtil.eLog("0000action move");
                       // event.setAction(MotionEvent.ACTION_DOWN);
                        break;
                    case MotionEvent.ACTION_UP:
                        PluLogUtil.eLog("----action up");
                        break;
                }
                return false;
            }
        });

    }



    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action=event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                PluLogUtil.eLog("---___________________________11111111-action DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                PluLogUtil.eLog("______________________________111111110000action move");
                event.setAction(MotionEvent.ACTION_DOWN);//此处导致button的ACTION_MOVE方法不会被调到
                break;
            case MotionEvent.ACTION_UP:
                PluLogUtil.eLog("--____________________________________1111111--action up");
                break;
        }
        return super.dispatchTouchEvent(event);
    }
}
