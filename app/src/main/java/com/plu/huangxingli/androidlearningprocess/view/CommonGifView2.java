package com.plu.huangxingli.androidlearningprocess.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;
import com.plu.huangxingli.androidlearningprocess.Utils.UiTools;

/**
 * Created by lily on 16-3-23.普通礼物
 */
public class CommonGifView2 extends RelativeLayout {

    ImageView mImgGift;
    TextView mTvGifCount;


    int mGiftCount;
    int giftWidth;
    private View giftOuterView;

    boolean isRunning;

    private int showedCount = 1;
    private int gifCountWidth;
    private int giftImageWidth;
    private LayoutParams originLayoutParams;
    private float originY;
    private int innerLayoutWidth;
    private RelativeLayout innerLayout;


    public CommonGifView2(Context context) {
        super(context);
        init(context);
    }

    public CommonGifView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CommonGifView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public int getGiftCount() {
        return mGiftCount;
    }



    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        giftOuterView = inflater.inflate(R.layout.common_gift_layout, this, true);
        mImgGift = (ImageView) giftOuterView.findViewById(R.id.imageGift);
        innerLayout = (RelativeLayout) giftOuterView.findViewById(R.id.layout_gift);
        innerLayoutWidth = UiTools.getMeasureWidth(innerLayout);




        mTvGifCount = (StrokeTextView) giftOuterView.findViewById(R.id.tv_gift_count);
        giftOuterView.post(new Runnable() {
            @Override
            public void run() {
                giftWidth = giftOuterView.getWidth();
                originY = giftOuterView.getY();
                originLayoutParams = (LayoutParams) getLayoutParams();
            }
        });


        mTvGifCount.post(new Runnable() {
            @Override
            public void run() {
                gifCountWidth = mTvGifCount.getWidth();
            }
        });

        mImgGift.post(new Runnable() {
            @Override
            public void run() {
                giftImageWidth = mImgGift.getWidth();
            }
        });





    }



    /*
    显示并展示礼物动画
     */
    public void startAnimate(int giftCount) {
        this.mGiftCount = giftCount;
        isRunning=true;

        final TranslateAnimation translateAnimation = new TranslateAnimation(-giftWidth, 0, 0, 0);

        translateAnimation.setDuration(1500);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                giftOuterView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                imageGifAnimate();


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        setVisibility(VISIBLE);
        innerLayout.startAnimation(translateAnimation);

    }



    /**
     * 具体礼物动画
     */

    private void imageGifAnimate() {

        TranslateAnimation gifCountTranslation = new TranslateAnimation( -giftImageWidth, innerLayoutWidth,0, 0);
        gifCountTranslation.setDuration(1000);
        gifCountTranslation.setFillAfter(true);
        mImgGift.startAnimation(gifCountTranslation);
        gifCountTranslation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mImgGift.setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                handGif();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                    mImgGift.setVisibility(INVISIBLE);
                    mImgGift.clearAnimation();
            }
        });
    }

    /*
    礼物递增动画显示
     */

    private void handGif() {

        final ValueAnimator valueAnimator = ValueAnimator.ofObject(new FloatEvaluator(), 1f, 2f, 1);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
               // Log.v("PLU", "  ONAnimationUpdate value is " + value);
                //mTvGifCount.setTextSize(value);
                mTvGifCount.setText("X" + showedCount);
                mTvGifCount.setScaleX(value);
                mTvGifCount.setScaleY(value);
            }
        });

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                AnimatorSet animatorSet = new AnimatorSet();
             //   TranslateAnimation translateAnimation=new TranslateAnimation(0,0,0,-300);
                ObjectAnimator transalteAnimator = ObjectAnimator.ofFloat(giftOuterView, "y", giftOuterView.getY(), giftOuterView.getY() - 300);
                ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(giftOuterView, "alpha", 1.f, 0f);
                animatorSet.play(transalteAnimator).with(alphaAnimator);
                animatorSet.setDuration(2000);
                animatorSet.start();
                animatorSet.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        release();
                        reset();
                        isRunning=false;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });

                PluLogUtil.log("-----onAnimationEnd");
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                showedCount++;
            }
        });

        valueAnimator.setRepeatCount(getGiftCount());
        valueAnimator.setDuration(500);
        valueAnimator.start();

    }

    public boolean isRunning(){
        return isRunning;
    }

    public void reset(){
        showedCount=1;
        mTvGifCount.setText("x1");
        mImgGift.setVisibility(INVISIBLE);
        giftOuterView.setVisibility(INVISIBLE);
        giftOuterView.setAlpha(1);
        giftOuterView.setY(originY);
        giftOuterView.setLayoutParams(originLayoutParams);
    }

    /**
     * 动画资源释放
     */
    public void release(){
        mImgGift.clearAnimation();
        mTvGifCount.clearAnimation();
        giftOuterView.clearAnimation();
    }
}
