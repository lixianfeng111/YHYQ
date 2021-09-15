package com.yhhl.yhyq.workbench.invoice.activity;

import android.app.Dialog;
import android.view.View;
import android.widget.TextView;

import com.yhhl.yhyq.R;
import com.yhhl.yhyq.base.BaseActivity;
import com.yhhl.yhyq.base.BasePresenter;
import com.yhhl.yhyq.dialog.DialogUtil;
import com.yhhl.yhyq.util.ToastUtil;
import com.yhhl.yhyq.workbench.invoice.presenter.AddInvoicePresenter;
import com.yhhl.yhyq.workbench.invoice.view.AddInvoiceView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddInvoiceActivity extends BaseActivity<AddInvoicePresenter> implements AddInvoiceView {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.company_name)
    TextView company_name;
    @BindView(R.id.ITIN)
    TextView ITIN;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.opening_bank)
    TextView opening_bank;
    @BindView(R.id.bank_account)
    TextView bank_account;
    @BindView(R.id.invoice_content)
    TextView invoice_content;
    @BindView(R.id.tax_rate)
    TextView tax_rate;
    @BindView(R.id.invoice_value)
    TextView invoice_value;
    private Dialog dialog;

    @Override
    public void initView() {
        title.setText("新增开票");
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_add_invoice;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public AddInvoicePresenter initPresenter() {
        return new AddInvoicePresenter(this);
    }

    @OnClick({R.id.back, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.submit:
                String company_name2 = company_name.getText().toString();
                String ITIN2 = ITIN.getText().toString();
                String address2 = address.getText().toString();
                String phone2 = phone.getText().toString();
                String opening_bank2 = opening_bank.getText().toString();
                String bank_account2 = bank_account.getText().toString();
                String invoice_content2 = invoice_content.getText().toString();
                String tax_rate2 = tax_rate.getText().toString();
                String invoice_value2 = invoice_value.getText().toString();
                if (company_name2.isEmpty()||ITIN2.isEmpty()||address2.isEmpty()||phone2.isEmpty()||opening_bank2.isEmpty()
                        ||bank_account2.isEmpty()||invoice_content2.isEmpty()||tax_rate2.isEmpty()||invoice_value2.isEmpty()){
                    ToastUtil.showToast("请补全开票信息");
                }else {
                    miBasePresenter.add_invoice(company_name2,ITIN2,address2,phone2,opening_bank2,
                            bank_account2,invoice_content2,tax_rate2,invoice_value2);
                }
                break;
        }
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
    public void onDataSuccess(Object data) {
        ToastUtil.showToast("添加成功");
        finish();
    }

    @Override
    public void onDataFailed(String msg) {

    }

    @Override
    public void onDataList(List list) {

    }
}