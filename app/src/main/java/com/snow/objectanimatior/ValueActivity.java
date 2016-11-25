package com.snow.objectanimatior;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;



public class ValueActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.valueanimation);

    }

    public void change(View view) {
        final Button button = (Button) findViewById(R.id.change);
        ValueAnimator animator = ValueAnimator.ofInt(0, 100);
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Object value = valueAnimator.getAnimatedValue();
                button.setText(""+value);
            }
        });
        animator.start();

    }


}
