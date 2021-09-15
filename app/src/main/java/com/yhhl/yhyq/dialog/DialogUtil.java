package com.yhhl.yhyq.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.yhhl.yhyq.R;

import java.util.Arrays;
import java.util.List;

public class DialogUtil {

    //网络加载
    public static Dialog showLoading(Context context) {
        ViewGroup view = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.show_loading, null, false);
        final AlertDialog dialog = new AlertDialog
                .Builder(context)
                .setView(view)
                .create();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);//设置背景透明
        dialog.show();
        return dialog;
    }
    public static Dialog invoice(Context context,String s) {
        ViewGroup view = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.dialog_invoice, null, false);
        TextView copy = view.findViewById(R.id.copy);
        TextView close = view.findViewById(R.id.close);
        TextView textView = view.findViewById(R.id.textView);
        textView.setText(s);
        final AlertDialog dialog = new AlertDialog
                .Builder(context)
                .setView(view)
                .create();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);//设置背景透明
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setLayout(800, 1400);
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.hide();
                // 得到剪贴板管理器
                ClipboardManager cmb = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
                cmb.setText(s.trim());
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.hide();
            }
        });
        //取消默认背景
        return dialog;
    }
}