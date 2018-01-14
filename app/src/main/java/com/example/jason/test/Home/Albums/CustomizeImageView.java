package com.example.jason.test.Home.Albums;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by Jason on 2018/1/10.
 */

public class CustomizeImageView extends android.support.v7.widget.AppCompatImageView {
    public CustomizeImageView(Context context) {
        super(context);
    }

    public CustomizeImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomizeImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
        int childWidthSize = getMeasuredWidth();
        // 高和寬一樣
        heightMeasureSpec = widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
