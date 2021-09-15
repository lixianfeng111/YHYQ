package com.yhhl.yhyq.home;

import android.view.View;

import com.yhhl.yhyq.R;
import com.yhhl.yhyq.base.BaseActivity;
import com.yhhl.yhyq.base.BasePresenter;
import com.yhhl.yhyq.base.BaseTitleBar;
import com.yhhl.yhyq.titlebar.SimpleTitleBar;
import com.yhhl.yhyq.util.ToastUtil;

public class Activity2 extends BaseActivity {

    private SimpleTitleBar simpleTitleBar;
    @Override
    public void initView() {

    }

    @Override
    public String getTitleContent() {
        return "测试";
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

}
