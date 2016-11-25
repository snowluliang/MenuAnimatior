# MenuAnimatior
### 这是一个菜单弹出动画
![](https://github.com/snowluliang/repository/blob/master/22.gif)

- 弹出动画
```
 private void startAnimation() {
        for (int i = 1; i < res.length; i++) {
            ObjectAnimator animator =
                    ObjectAnimator.ofFloat(imgList.get(i), "translationY", 0, i * 150);
            animator.setDuration(700);
            animator.setInterpolator(new BounceInterpolator());//落地回弹效果
            animator.setStartDelay(i * 200);
            animator.start();
         )
    }
```
- 收回动画
```
private void back() {
        for (int i = 1; i < res.length; i++) {
            ObjectAnimator animator =
                    ObjectAnimator.ofFloat(imgList.get(i), "translationY", i * 150f, 0f);
            animator.setDuration(800);
            animator.start();
          )
    }
```


![](https://github.com/snowluliang/repository/blob/master/184345.gif)
![](https://github.com/snowluliang/repository/blob/master/185004.gif)

- 新增扇形动画
```
AnimationSet set = new AnimationSet(true);
            Animation animation = null;
            if (mStatus == Status.CLOSE) {
                //如果状态是关闭的话,执行展开动画
                animation = new TranslateAnimation(flagX * cl, 0, flagY * ct, 0);
                child.setClickable(true);
                child.setFocusable(true);
            } else {
                //状态打开的话,执行收回动画
                animation = new TranslateAnimation(0, flagX * cl, 0, flagY * ct);
                child.setClickable(false);
                child.setFocusable(false);
            }

            animation.setFillAfter(true);
            animation.setDuration(duration);
            animation.setStartOffset(i * 100 / count);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    if (mStatus == Status.CLOSE) {
                        child.setVisibility(GONE);
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            //旋转动画
            RotateAnimation rotate = new RotateAnimation(0, 720f,
                    RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
            rotate.setDuration(duration);
            rotate.setFillAfter(true);

            set.addAnimation(rotate);
            set.addAnimation(animation);

            child.startAnimation(set);
```

