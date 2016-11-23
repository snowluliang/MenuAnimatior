package com.snow.objectanimatior;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Path;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;



public class Action extends Activity implements View.OnClickListener {
    private int[] res = {R.id.iv_g, R.id.iv_a,
            R.id.iv_b,
            R.id.iv_c,
            R.id.iv_d,
            R.id.iv_e,
            R.id.iv_f};

    private List<ImageView> imgList = new ArrayList<>();

    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action);
        initView();
    }

    private void initView() {
        for (int id : res) {
            ImageView view = (ImageView) findViewById(id);
            view.setOnClickListener(this);
            imgList.add(view);
            System.out.println("id:->" + id);
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_g:
                if (flag) {
                    startAnimation();
                    flag = false;
                } else {
                    back();
                    flag = true;
                }
                break;
            case R.id.iv_a:

                break;
            case R.id.iv_b:

                break;
            case R.id.iv_c:

                break;
            case R.id.iv_d:

                break;
            case R.id.iv_e:

                break;
            case R.id.iv_f:

                break;

        }

    }

    private void back() {
        for (int i = 1; i < res.length; i++) {
            ObjectAnimator animator =
                    ObjectAnimator.ofFloat(imgList.get(i), "translationY", i * 150f, 0f);
            animator.setDuration(800);
            //animator.setInterpolator(new OvershootInterpolator());
            animator.start();
           /* ObjectAnimator animator2 =
                    ObjectAnimator.ofFloat(imgList.get(i), "translationX", i * 100f, 0f);
            animator2.setDuration(800);
            //animator.setInterpolator(new OvershootInterpolator());
            animator2.start();*/

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void startAnimation() {
        for (int i = 1; i < res.length; i++) {
            ObjectAnimator animator =
                    ObjectAnimator.ofFloat(imgList.get(i), "translationY", 0, i * 150);
            animator.setDuration(700);
            //animator.setInterpolator(new OvershootInterpolator());//动画偏出一点再回来.
            //animator.setInterpolator(new AnticipateInterpolator());//说不出来动画
            //animator.setInterpolator(new AccelerateInterpolator());//加速动画
           //animator.setInterpolator(new DecelerateInterpolator());//减速动画
            //animator.setInterpolator(new FastOutLinearInInterpolator());//开始的时候加速
            animator.setInterpolator(new BounceInterpolator());//落地回弹效果
            animator.setStartDelay(i * 200);
            animator.start();

            /*ObjectAnimator animator2 =
                    ObjectAnimator.ofFloat(imgList.get(i), "translationX", 0, i * 100);
            animator2.setDuration(700);
            animator2.setInterpolator(new AccelerateInterpolator());
            animator2.setStartDelay(i * 200);
            animator2.start();*/

        }
    }
}
