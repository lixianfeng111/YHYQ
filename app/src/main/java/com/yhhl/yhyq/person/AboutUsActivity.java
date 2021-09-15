package com.yhhl.yhyq.person;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yhhl.yhyq.R;
import com.yhhl.yhyq.base.AppManager;
import com.yhhl.yhyq.base.BaseActivity;
import com.yhhl.yhyq.base.BasePresenter;

public class AboutUsActivity extends BaseActivity {

    private ImageView back;
    private TextView title;
    private TextView version;

    @Override
    public void initView() {
        title = findViewById(R.id.title);
        back = findViewById(R.id.back);
        version = findViewById(R.id.version);
        title.setText("关于我们");
    }

    @Override
    public void initListener() {

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initData() {
        String versionName = AppManager.getAppManager().getVersionName();
        version.setText("V."+versionName);
    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_about_us;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}