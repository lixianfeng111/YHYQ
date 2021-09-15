package com.yhhl.yhyq.util;

import android.widget.Toast;

import com.yhhl.yhyq.base.App;

public class ToastUtil {
    public static void showToast(String content) {
        //判空
        Toast.makeText(App.sContext, content, Toast.LENGTH_SHORT).show();
    }
}
