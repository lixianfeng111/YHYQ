package com.yhhl.yhyq.person;

import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.yhhl.yhyq.R;
import com.yhhl.yhyq.base.BaseActivity;
import com.yhhl.yhyq.base.BasePresenter;
import com.yhhl.yhyq.base.Constant;
import com.yhhl.yhyq.util.SpzUtils;

public class PersonActivity extends BaseActivity {
    private ImageView back;
    private TextView title;
    private ImageView push_switch;
    private RadioGroup radioGroup;
    private boolean aBoolean;

    @Override
    public void initView() {
        title = findViewById(R.id.title);
        back = findViewById(R.id.back);
        push_switch = findViewById(R.id.push_switch);
        radioGroup = findViewById(R.id.radioGroup);
        aBoolean = SpzUtils.getBoolean(Constant.SWITCH, false);
        title.setText("设置");
        if (aBoolean){
            push_switch.setImageResource(R.mipmap.push_on);
        }else {
            push_switch.setImageResource(R.mipmap.push_off);
        }
    }

    @Override
    public void initListener() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        push_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aBoolean){
                    push_switch.setImageResource(R.mipmap.push_off);
                    aBoolean=false;
                }else {
                    push_switch.setImageResource(R.mipmap.push_on);
                    aBoolean=true;
                }
                SpzUtils.putBoolean(Constant.SWITCH,aBoolean);
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_person;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}