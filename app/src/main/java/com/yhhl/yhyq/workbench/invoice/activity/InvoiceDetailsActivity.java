package com.yhhl.yhyq.workbench.invoice.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.yhhl.yhyq.R;
import com.yhhl.yhyq.base.BaseActivity;
import com.yhhl.yhyq.dialog.DialogUtil;
import com.yhhl.yhyq.util.ToastUtil;
import com.yhhl.yhyq.workbench.invoice.bean.InvoiceDetailBean;
import com.yhhl.yhyq.workbench.invoice.presenter.InvoiceDetailPresenter;
import com.yhhl.yhyq.workbench.invoice.view.InvoiceDetailView;
import java.util.List;
import butterknife.BindView;

public class InvoiceDetailsActivity extends BaseActivity<InvoiceDetailPresenter> implements InvoiceDetailView {

    private ImageView back;
    private Button back_btn;
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
        back = findViewById(R.id.back);
        back_btn = findViewById(R.id.back_btn);
        title.setText("开票详情");
    }

    @Override
    public void initListener() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            String company_name2 = bundle.getString("company_name");
            String ITIN2 = bundle.getString("ITIN");
            String address2 = bundle.getString("address");
            String phone2 = bundle.getString("phone");
            String opening_bank2 = bundle.getString("opening_bank");
            String bank_account2 = bundle.getString("bank_account");
            String invoice_content2 = bundle.getString("invoice_content");
            String tax_rate2 = bundle.getString("tax_rate");
            String invoice_value2 = bundle.getString("invoice_value");
            company_name.setText(company_name2);
            ITIN.setText(ITIN2);
            address.setText(address2);
            phone.setText(phone2);
            opening_bank.setText(opening_bank2);
            bank_account.setText(bank_account2);
            invoice_content.setText(invoice_content2);
            tax_rate.setText(tax_rate2);
            invoice_value.setText(invoice_value2);

        }

//        miBasePresenter.invoice_detail("28");
    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_invoice_details;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public InvoiceDetailPresenter initPresenter() {
        return new InvoiceDetailPresenter(this);
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
    public void onDataSuccess(InvoiceDetailBean data) {
        ToastUtil.showToast(data.getCompany_name());
    }

    @Override
    public void onDataFailed(String msg) {

    }

    @Override
    public void onDataList(List<InvoiceDetailBean> list) {

    }
}