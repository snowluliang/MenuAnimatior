package com.snow.objectanimatior;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img;
    private Button mMove, mAction, mValue, mOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        img = (ImageView) findViewById(R.id.imageView);
        img.setOnClickListener(this);

        mAction = (Button) findViewById(R.id.btn_action);
        mMove = (Button) findViewById(R.id.btn_move);
        mValue = (Button) findViewById(R.id.btn_value);
        mOpen = (Button) findViewById(R.id.btn_open);

        mAction.setOnClickListener(this);
        mMove.setOnClickListener(this);
        mValue.setOnClickListener(this);
        mOpen.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.imageView:
                Toast.makeText(this, "被点击了", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_move:
                ObjectAnimator rotation = ObjectAnimator.ofFloat(img, "rotation", 0, 360F);
                rotation.setDuration(2000);
                rotation.start();
                final ObjectAnimator translationY = ObjectAnimator.ofFloat(img, "translationY", 0, 150f);
                translationY.setDuration(2000);
                translationY.setInterpolator(new BounceInterpolator());
                rotation.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        translationY.start();
                        super.onAnimationEnd(animation);
                    }
                });
                break;
            case R.id.btn_action:
                intent.setClass(this, Action.class);
                startActivity(intent);
                break;
            case R.id.btn_value:
                intent.setClass(this, ValueActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_open:
                intent.setClass(this, FanActivity.class);
                startActivity(intent);
                break;

        }
    }
}
