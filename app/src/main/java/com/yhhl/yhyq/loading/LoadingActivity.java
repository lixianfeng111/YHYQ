
package com.yhhl.yhyq.loading;

import com.yhhl.yhyq.MainActivity;
import com.yhhl.yhyq.R;
import com.yhhl.yhyq.base.BaseActivity;
import com.yhhl.yhyq.base.BasePresenter;
import com.yhhl.yhyq.login.LoginActivity;
import com.yhhl.yhyq.statusBar.StatusManager;
import com.yhhl.yhyq.util.IntentUtil;
import com.yhhl.yhyq.util.SpzUtils;

public class LoadingActivity extends BaseActivity {

    private boolean is_login;

    @Override
    public void initView() {
        StatusManager.getInstance().immersionStatusBar(this,false);
        is_login = SpzUtils.getBoolean("is_login", false);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    if (is_login){
                        IntentUtil.getInstance().intent(LoadingActivity.this, MainActivity.class,null);
                        finish();
                    }else {
//                        IntentUtil.getInstance().intent(LoadingActivity.this, MainActivity.class,null);
                        IntentUtil.getInstance().intent(LoadingActivity.this, LoginActivity.class,null);
                        finish();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_loading;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}