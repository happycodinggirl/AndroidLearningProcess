package com.plu.huangxingli.androidlearningprocess.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by lily on 16-5-10.
 */
public class DrawView extends View {

    private String imagUrl;
    private String sender;
    private String senderContent;
    private Bitmap bitmap;
    private Paint mPaint;


    public String getImagUrl() {
        return imagUrl;
    }

    public String getSender() {
        return sender;
    }

    public String getSenderContent() {
        return senderContent;
    }

    public void setImagUrl(final String imagUrl) {
        this.imagUrl = imagUrl;
        Subscription subscription=Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                subscriber.onNext(imagUrl);
                subscriber.onCompleted();
            }
        }).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                URL url = null;
                try {
                    url = new URL(imagUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    bitmap = BitmapFactory.decodeStream(input);
                    // invalidate();
                    connection.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    PluLogUtil.log("0---MURL EXCEPTION IS "+e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                    PluLogUtil.log("----ioException is "+e.getMessage());

                }
                PluLogUtil.log("-----doOnSubscribe thread is "+Thread.currentThread().getName());
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                invalidate();
            }

            @Override
            public void onNext(String s) {
                PluLogUtil.log("----onNext");
                invalidate();
            }
        });



    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setSenderContent(String senderContent) {
        this.senderContent = senderContent;
    }

    public DrawView(Context context) {
        super(context);
        init();
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        mPaint = new Paint();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        PluLogUtil.log("---ONDRAW");
        if (!TextUtils.isEmpty(getImagUrl())) {
            PluLogUtil.log("-----getImageUrl is " + getImagUrl());
            Matrix matrix=new Matrix();
            RectF rectF=new RectF(getLeft(),getTop(),30,30);
            matrix.mapRect(rectF);
            //matrix.
            canvas.drawBitmap(bitmap,matrix,mPaint);
            //canvas.drawBitmap(bitmap, getLeft(), getTop(), mPaint);
        }
        if (!TextUtils.isEmpty(getSender())) {
            PluLogUtil.log("----toDrawText getSender is "+getSender());
            canvas.drawText(getSender(), 550, 500, mPaint);
        }

    }
}
