package com.snow.objectanimatior;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class TopBarView extends RelativeLayout {

    private Button leftBtn, rightBtn;
    private TextView tittle;
    //左边button 文字颜色,背景颜色,字体
    private int leftTextColor;
    private Drawable leftBackground;
    private String leftText;

    //右边button 文字颜色,背景颜色,字体
    private int rightTextColor;
    private Drawable rightBackground;
    private String rightText;

    //中间标题的字体,颜色,字体大小
    private int tittleTextColor;
    private String tittleText;
    private float tittleTextSize;


    private LayoutParams leftParams, rightParams, tittleParams;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public TopBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        leftTextColor = array.getColor(R.styleable.TopBar_leftBtnTextColor, 0);
        leftBackground = array.getDrawable(R.styleable.TopBar_leftBtnBackground);
        leftText = array.getString(R.styleable.TopBar_leftBtnText);

        rightTextColor = array.getColor(R.styleable.TopBar_rightBtnTextColor, 0);
        rightBackground = array.getDrawable(R.styleable.TopBar_rightBtnBackground);
        rightText = array.getString(R.styleable.TopBar_rightBtnText);

        tittleTextColor = array.getColor(R.styleable.TopBar_tittleTextColor, 0);
        tittleTextSize = array.getDimension(R.styleable.TopBar_tittleTextSize, 0);
        tittleText = array.getString(R.styleable.TopBar_tittle);

        array.recycle();//回收

        leftBtn = new Button(context);
        rightBtn = new Button(context);
        tittle = new TextView(context);

        leftBtn.setText(leftText);
        leftBtn.setTextColor(leftTextColor);
        leftBtn.setBackground(leftBackground);

        rightBtn.setBackground(rightBackground);
        rightBtn.setText(rightText);
        rightBtn.setTextColor(rightTextColor);

        tittle.setText(tittleText);
        tittle.setTextColor(tittleTextColor);
        tittle.setTextSize(tittleTextSize);

        setBackgroundColor(0xFFF59563);

        leftParams = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        addView(leftBtn, leftParams);

        rightParams = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        addView(rightBtn, rightParams);

        tittleParams = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        tittleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        addView(tittle, tittleParams);


    }
}
