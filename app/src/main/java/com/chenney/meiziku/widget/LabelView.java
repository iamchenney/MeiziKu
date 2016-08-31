package com.chenney.meiziku.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chenney.meiziku.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/8/30.
 */
public class LabelView extends LinearLayout {

    @BindView(R.id.title)
    TextView titleView;
    @BindView(R.id.text)
    TextView textView;

    private String title;
    private String text;
    private int textColor;
    private int titleColor;
    private int textSize;
    private int titleSize;

    public LabelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.basic_label_text, this);
        ButterKnife.bind(this,view);

        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.LabelView);
        title = array.getString(R.styleable.LabelView_title);
        titleSize = array.getDimensionPixelSize(R.styleable.LabelView_title_size,12);
        titleColor = array.getInt(R.styleable.LabelView_title_color, Color.BLUE);

        text = array.getString(R.styleable.LabelView_text);
        textSize = array.getDimensionPixelSize(R.styleable.LabelView_text_size,12);
        textColor = array.getInt(R.styleable.LabelView_text_color,Color.BLACK);


        array.recycle();
    }

    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();

        setTitle(title);
        setTitleColor(titleColor);
        setTitleSize(titleSize);

        setText(text);
        setTextSize(textSize);
        setTextColor(textColor);
    }

    public void setTitle(String title){
        titleView.setText(title);
    }

    public void setText(String text){
        textView.setText(text);
    }

    public void setTitleSize(int titleSize){
        titleView.setTextSize(titleSize);
    }

    public void setTitleColor(@ColorInt int titleColor){
        titleView.setTextColor(titleColor);
    }

    public void setTextSize(int textSize){
        textView.setTextSize(textSize);
    }

    public void setTextColor(@ColorInt int textColor){
        textView.setTextColor(textColor);
    }
}
