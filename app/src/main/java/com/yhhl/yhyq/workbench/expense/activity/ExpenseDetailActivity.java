package com.yhhl.yhyq.workbench.expense.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.yhhl.yhyq.R;
import com.yhhl.yhyq.base.BaseActivity;
import com.yhhl.yhyq.base.BasePresenter;
import com.yhhl.yhyq.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class ExpenseDetailActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.organization)
    TextView organization;
    @BindView(R.id.department)
    TextView department;
    @BindView(R.id.expense_user)
    TextView expense_user;
    @BindView(R.id.expense_money)
    TextView expense_money;
    @BindView(R.id.note)
    TextView note;
    @Override
    public void initView() {
        title.setText("报销详情");
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getExtras();
        String organization2 = bundle.getString("organization");
        String department2 = bundle.getString("department");
        String expense_user2 = bundle.getString("expense_user");
        String expense_money2 = bundle.getString("expense_money");
        String note2 = bundle.getString("note");

        organization.setText(organization2);
        department.setText(department2);
        expense_money.setText(expense_money2);
        expense_user.setText(expense_user2);
        note.setText(note2);
    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_expense_detail;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @OnClick({R.id.back, R.id.back_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.back_btn:
                finish();
                break;
        }
    }
}