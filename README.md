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
