package com.yhhl.yhyq.workbench.expense.activity;

import android.app.Dialog;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.yhhl.yhyq.R;
import com.yhhl.yhyq.base.BaseActivity;
import com.yhhl.yhyq.base.BasePresenter;
import com.yhhl.yhyq.dialog.DialogUtil;
import com.yhhl.yhyq.util.ToastUtil;
import com.yhhl.yhyq.workbench.expense.bean.ExpenseBean;
import com.yhhl.yhyq.workbench.expense.presenter.ExpensePresenter;
import com.yhhl.yhyq.workbench.expense.view.ExpenseView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class NewAddWorkActivity extends BaseActivity<ExpensePresenter> implements ExpenseView {

    @BindView(R.id.select_organization)
    TextView select_organization;
    @BindView(R.id.select_department)
    TextView select_department;
    @BindView(R.id.expense_user)
    EditText expense_user;
    @BindView(R.id.expense_money)
    EditText expense_money;
    @BindView(R.id.remark)
    EditText remark;
    private ImageView back;
    private Dialog dialog;

    @Override
    public void initView() {
        back = findViewById(R.id.back);
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

    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_new_add_work;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public ExpensePresenter initPresenter() {
        return new ExpensePresenter(this);
    }

    @OnClick({R.id.back, R.id.select_organization, R.id.select_department, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.select_organization:
                select_organization();
                break;
            case R.id.select_department:
                select_department();
                break;
            case R.id.submit:
                String organization = select_organization.getText().toString();
                String department = select_department.getText().toString();
                String name = expense_user.getText().toString();
                String money = expense_money.getText().toString();
                String remark2 = remark.getText().toString();
                if (organization.isEmpty()||department.isEmpty()||name.isEmpty()||money.isEmpty()||remark2.isEmpty()){
                    ToastUtil.showToast("请补全信息");
                }else {
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("company_name",organization);
                    map.put("dept_name",department);
                    map.put("bill_user_name",name);
                    map.put("bill_value",money);
                    map.put("remark",remark2);
                    miBasePresenter.add_expense(map);
                }
                break;
        }
    }

    private void select_department() {
        View view = View.inflate(this,R.layout.select_department_pop,null);
        TextView yan = view.findViewById(R.id.yan);
        TextView xiaoshpou = view.findViewById(R.id.xiaoshpou);
        TextView kefu = view.findViewById(R.id.kefu);
        final PopupWindow popupWindow=new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);
        popupWindow.setFocusable(true);
        setBackgroundAlpha(0.5f);
        popupWindow.setOutsideTouchable(true);
//        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.showAtLocation(view, Gravity.BOTTOM,0,0);
        //点击外部消失并回复透明度
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(1.0f);
            }
        });
        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                setBackgroundAlpha(1.0f);
            }
        });
        yan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = yan.getText().toString();
                select_department.setText(s);
                select_department.setTextColor(getResources().getColor(R.color.color_50));
                popupWindow.dismiss();
                setBackgroundAlpha(1.0f);
            }
        });
        xiaoshpou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = xiaoshpou.getText().toString();
                select_department.setText(s);
                select_department.setTextColor(getResources().getColor(R.color.color_50));
                popupWindow.dismiss();
                setBackgroundAlpha(1.0f);
            }
        });
        kefu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = kefu.getText().toString();
                select_department.setText(s);
                select_department.setTextColor(getResources().getColor(R.color.color_50));
                popupWindow.dismiss();
                setBackgroundAlpha(1.0f);
            }
        });
    }

    private void select_organization() {
        View view = View.inflate(this,R.layout.select_organization_pop,null);
        TextView yhhl = view.findViewById(R.id.yhhl);
        TextView fuhong = view.findViewById(R.id.fuhong);
        TextView yhhl2 = view.findViewById(R.id.yhhl2);
        final PopupWindow popupWindow=new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);
        popupWindow.setFocusable(true);
        setBackgroundAlpha(0.5f);
        popupWindow.setOutsideTouchable(true);
//        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.showAtLocation(view, Gravity.BOTTOM,0,0);
        //点击外部消失并回复透明度
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(1.0f);
            }
        });
        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                setBackgroundAlpha(1.0f);
            }
        });
        yhhl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = yhhl.getText().toString();
                select_organization.setText(s);
                select_organization.setTextColor(getResources().getColor(R.color.color_50));
                popupWindow.dismiss();
                setBackgroundAlpha(1.0f);
            }
        });
        fuhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = fuhong.getText().toString();
                select_organization.setText(s);
                select_organization.setTextColor(getResources().getColor(R.color.color_50));
                popupWindow.dismiss();
                setBackgroundAlpha(1.0f);
            }
        });
        yhhl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = yhhl2.getText().toString();
                select_organization.setText(s);
                select_organization.setTextColor(getResources().getColor(R.color.color_50));
                popupWindow.dismiss();
                setBackgroundAlpha(1.0f);
            }
        });
    }

    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }

    @Override
    public void showLoading() {
        dialog = DialogUtil.showLoading(this);
    }

    @Override
    public void hideLoading() {
        dialog.dismiss();
    }

    @Override
    public void onDataSuccess(ExpenseBean data) {

    }

    @Override
    public void onDataFailed(String msg) {
        ToastUtil.showToast("添加成功");
        finish();
    }

    @Override
    public void onDataList(List<ExpenseBean> list) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog!=null){
            dialog.dismiss();
        }
    }

    @Override
    public void onAddExpenseSuccess() {
        ToastUtil.showToast("添加成功");
        finish();
    }
}