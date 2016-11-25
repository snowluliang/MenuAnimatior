package com.snow.objectanimatior;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.BaseInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import static android.view.animation.Animation.RELATIVE_TO_SELF;


public class ArcMenu extends ViewGroup implements View.OnClickListener {

    /**
     * 成员变量的定义
     */
    private int mRadius;//半径

    public static final int POS_LEFT_TOP = 0;
    public static final int POS_LEFT_BOTTOM = 1;
    public static final int POS_RIGHT_TOP = 2;
    public static final int POS_RIGHT_BOTTOM = 3;
    /**
     * 自定义属性中的四个位置,默认为右下
     */
    private Position mPosition = Position.RIGHT_BOTTOM;

    public enum Position {
        LEFT_TOP, LEFT_BOTTOM, RIGHT_TOP, RIGHT_BOTTOM
    }

    /**
     * 菜单的状态,默认为关闭
     */
    private Status mStatus = Status.CLOSE;

    public enum Status {
        OPEN, CLOSE
    }

    /**
     * 接口回调的方法
     */
    private MenuItemClick mItemClick;

    public void setmItemClick(MenuItemClick mItemClick) {
        this.mItemClick = mItemClick;
    }

    public interface MenuItemClick {
        void OnClick(View view, int pos);
    }


    public ArcMenu(Context context) {
        this(context, null);
    }

    public ArcMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArcMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mRadius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100,
                getResources().getDisplayMetrics());

        TypedArray array = context.getTheme().
                obtainStyledAttributes(attrs, R.styleable.ArcMenu, defStyleAttr, 0);


        int pos = array.getInt(R.styleable.ArcMenu_position, POS_RIGHT_BOTTOM);
        switch (pos) {
            case POS_LEFT_BOTTOM:
                mPosition = Position.LEFT_BOTTOM;
                break;
            case POS_LEFT_TOP:
                mPosition = Position.LEFT_TOP;
                break;
            case POS_RIGHT_BOTTOM:
                mPosition = Position.RIGHT_BOTTOM;
                break;
            case POS_RIGHT_TOP:
                mPosition = Position.RIGHT_TOP;
                break;
        }

        mRadius = (int) array.getDimension(R.styleable.ArcMenu_radius,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100,
                        getResources().getDisplayMetrics()));
        array.recycle();
    }

    /**
     * 测量子View控件,设置监听事件
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
            //getChildAt(i).setOnClickListener(this);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        if (changed) {
            layoutCBtn();
            /**
             * 测量子View控件 的位置,角度
             */
            int count = getChildCount();
            for (int i = 0; i < count - 1; i++) {
                View child = getChildAt(i + 1);
                child.setVisibility(GONE);//先设置隐藏
                int cl = (int) (mRadius * Math.sin(Math.PI / 2 / (count - 2) * i));
                int ct = (int) (mRadius * Math.cos(Math.PI / 2 / (count - 2) * i));

                int cWidth = child.getMeasuredWidth();
                int cHeight = child.getMeasuredHeight();
                /**
                 * 如果主菜单的位置是在左上,右下角
                 */
                if (mPosition == Position.LEFT_BOTTOM || mPosition == Position.RIGHT_BOTTOM) {
                    ct = getMeasuredHeight() - cHeight - ct;

                }
                /**
                 * 如果是右上角,右下角
                 */
                if (mPosition == Position.RIGHT_TOP || mPosition == Position.RIGHT_BOTTOM) {
                    cl = getMeasuredWidth() - cWidth - cl;

                }
                child.layout(cl, ct, cl + cWidth, ct + cHeight);

            }
        }

    }

    private void layoutCBtn() {
        View mCBtn = getChildAt(0);
        mCBtn.setOnClickListener(this);
        int l = 0;
        int t = 0;

        int width = mCBtn.getMeasuredWidth();
        int height = mCBtn.getMeasuredHeight();

        switch (mPosition) {
            case LEFT_TOP:
                l = 0;
                t = 0;
                break;
            case LEFT_BOTTOM:
                l = 0;
                t = getMeasuredHeight() - height;
                break;
            case RIGHT_TOP:
                l = getMeasuredWidth() - width;
                t = 0;
                break;
            case RIGHT_BOTTOM:
                l = getMeasuredWidth() - width;
                t = getMeasuredHeight() - height;
                break;

        }
        mCBtn.layout(l, t, l + width, t + width);
        Log.i("width:--", width + "");
        Log.i("height:--", height + "");
    }

    @Override
    public void onClick(View view) {
        //主菜单的旋转动画
        rotation(view, 0f, 360f, 500);
        toogleItem(500);
    }

    public void toogleItem(int duration) {
        int count = getChildCount();
        for (int i = 0; i < count - 1; i++) {
            final View child = getChildAt(i + 1);
            child.setVisibility(VISIBLE);
            int cl = (int) (mRadius * Math.sin(Math.PI / 2 / (count - 2) * i));
            int ct = (int) (mRadius * Math.cos(Math.PI / 2 / (count - 2) * i));

            int flagX = 1;
            int flagY = 1;
            /**
             * 如果主菜单的位置是在左上,左下角
             */
            if (mPosition == Position.LEFT_TOP || mPosition == Position.LEFT_BOTTOM) {
                flagX = -1;
            }
            /**
             * 如果是左上角,右上角
             */
            if (mPosition == Position.LEFT_TOP || mPosition == Position.RIGHT_TOP) {
                flagY = -1;
            }
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

            final int pos = i + 1;
            child.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mItemClick != null)
                        mItemClick.OnClick(child, pos);

                    setChildAnimation(pos - 1);
                    changeStatus();
                }
            });

        }
        changeStatus();

    }

    private void setChildAnimation(int pos) {
        for (int i = 0; i < getChildCount() - 1; i++) {
            View childView = getChildAt(i + 1);

            if (pos == i) {
                childView.startAnimation(animationToBig());

            } else {
                childView.startAnimation(animationToSmall());

            }
            childView.setClickable(false);
            childView.setFocusable(false);

        }


    }

    private Animation animationToSmall() {
        AnimationSet set = new AnimationSet(true);
        ScaleAnimation animation = new ScaleAnimation( 1.0f, 0.0f,1.0f,0.0f,  Animation.
                RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        //透明动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
        set.addAnimation(animation);
        set.addAnimation(alphaAnimation);
        set.setFillAfter(true);
        set.setDuration(500);
        return set;
    }

    private Animation animationToBig() {
        AnimationSet set = new AnimationSet(true);
        ScaleAnimation animation = new ScaleAnimation(1.0f, 3.0f,1.0f, 3.0f, Animation.
                RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        //透明动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
        set.addAnimation(animation);
        set.addAnimation(alphaAnimation);
        set.setFillAfter(true);
        set.setDuration(500);
        return set;
    }

    private void changeStatus() {
        mStatus = mStatus == Status.CLOSE ? Status.OPEN : Status.CLOSE;
    }

    public boolean isOpen() {
        return mStatus == Status.OPEN;
    }

    private void rotation(View view, float start, float end, int duration) {
        RotateAnimation anim = new RotateAnimation(start, end,
                RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(duration);
        anim.setInterpolator(new AccelerateInterpolator());//加速动画
        anim.setFillAfter(true);
        view.startAnimation(anim);

    }

}
