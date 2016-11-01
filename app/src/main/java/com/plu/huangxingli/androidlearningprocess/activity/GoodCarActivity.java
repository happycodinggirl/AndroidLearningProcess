package com.plu.huangxingli.androidlearningprocess.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.plu.huangxingli.androidlearningprocess.BaseActivity;
import com.plu.huangxingli.androidlearningprocess.R;


/**
 * Created by huangxl on 2016/11/1.
 */
public class GoodCarActivity extends BaseActivity {

    private ImageView imageGoodCar;

    int screenWidth;
    int screenHeight;

    float[] pos=new float[2];
    float[] arc=new float[2];
    private RelativeLayout relativeLayout;

    int[] parentPos=new int[2];
    private ImageView goods;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.good_car);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativelayout);

        screenWidth=getResources().getDisplayMetrics().widthPixels;
        screenHeight=getResources().getDisplayMetrics().heightPixels;
        imageGoodCar = (ImageView) findViewById(R.id.image_good);
        Button button= (Button) findViewById(R.id.btn);
        goods = new ImageView(getApplicationContext());
        goods.setImageDrawable(imageGoodCar.getDrawable());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 100);
        relativeLayout.addView(goods, params);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flyToFloor();
            }
        });


    }


    private void flyToFloor(){
        Path path=new Path();
        final int[] currentPos=new int[2];
                imageGoodCar.getLocationInWindow(currentPos);
        relativeLayout.getLocationInWindow(parentPos);
        path.moveTo(currentPos[0]-parentPos[0]+imageGoodCar.getWidth()/2,currentPos[1]-parentPos[1]+imageGoodCar.getHeight()/2);
        path.quadTo(screenWidth/2+100,screenHeight/2-200,screenWidth/2,screenHeight);
        final PathMeasure pathMeasure=new PathMeasure(path,false);
        ValueAnimator valueAnimator=ValueAnimator.ofFloat(0,pathMeasure.getLength());

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                pathMeasure.getPosTan((Float) valueAnimator.getAnimatedValue(),pos,arc);
                goods.setTranslationX(pos[0]);
                goods.setTranslationY(pos[1]);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                relativeLayout.removeView(goods);
            }
        });
        valueAnimator.setDuration(3000);
        valueAnimator.start();

    }



}
