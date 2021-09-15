package com.yhhl.yhyq.util;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ImageButton extends LinearLayout {
    private ImageView imageViewbutton;

    private TextView textView;

    public ImageButton(Context context) {
        super(context);
    }

    public ImageButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public ImageButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        imageViewbutton = new ImageView(context, attrs);

        imageViewbutton.setPadding(0, 0, 0, 0);

        textView =new TextView(context, attrs);
        //水平居中
        textView.setGravity(android.view.Gravity.CENTER_HORIZONTAL);

        textView.setPadding(0, 0, 0, 0);

        setClickable(true);

        setFocusable(true);

        setBackgroundResource(android.R.drawable.btn_default);

        setOrientation(LinearLayout.HORIZONTAL);

        addView(imageViewbutton);

        addView(textView);
    }
}
