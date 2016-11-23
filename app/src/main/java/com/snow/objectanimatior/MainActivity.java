package com.snow.objectanimatior;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       img = (ImageView) findViewById(R.id.imageView);
    }

    public void move(View view) {

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


    }

    public void action(View view) {
        Intent intent = new Intent(this, Action.class);
        startActivity(intent);

    }

    public void value(View view) {
        Intent intent = new Intent(this, ValueActivity.class);
        startActivity(intent);

    }

    public void click(View view) {
        Toast.makeText(this, "被点击了", Toast.LENGTH_SHORT).show();
    }
}
