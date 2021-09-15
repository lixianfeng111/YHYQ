package com.yhhl.yhyq.workbench.invoice.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.interfaces.OnItemClickListener;
import com.othershe.baseadapter.interfaces.OnLoadMoreListener;
import com.ps.mrcyclerview.WrapContentLinearLayoutManager;
import com.yhhl.yhyq.R;
import com.yhhl.yhyq.base.BaseActivity;
import com.yhhl.yhyq.dialog.DialogUtil;
import com.yhhl.yhyq.util.IntentUtil;
import com.yhhl.yhyq.workbench.expense.bean.ExpenseBean;
import com.yhhl.yhyq.workbench.invoice.adapter.InvoiceAdapter;
import com.yhhl.yhyq.workbench.invoice.bean.InvoiceBean;
import com.yhhl.yhyq.workbench.invoice.presenter.InvoicePresenter;
import com.yhhl.yhyq.workbench.invoice.view.InvoiceView;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.OnClick;

public class InvoiceActivity extends BaseActivity<InvoicePresenter> implements InvoiceView {

    @BindView(R.id.right_icon)
    ImageView right_icon;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipe_layout;
    private RecyclerView recyclerView;
    private TextView title;
    private InvoiceAdapter invoiceAdapter;
    private Dialog dialog;
    private int n=1;
    @Override
    public void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        title = findViewById(R.id.title);
        title.setText("开票申请");
        right_icon.setVisibility(View.VISIBLE);
        swipe_layout.setRefreshing(true);
    }

    @Override
    public void initListener() {
        swipe_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                n=1;
                miBasePresenter.find_expense(n+"");
            }
        });
    }

    @Override
    public void initData() {
        miBasePresenter.find_expense(n+"");
    }

    private void loadMore() {
        n++;
        miBasePresenter.find_expense(n+"");
    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_invoice;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public InvoicePresenter initPresenter() {
        return new InvoicePresenter(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onDataSuccess(InvoiceBean data) {

    }

    @Override
    public void onDataFailed(String msg) {
        swipe_layout.setRefreshing(false);
    }

    @Override
    public void onDataList(List<InvoiceBean> list) {
        swipe_layout.setRefreshing(false);
        if (invoiceAdapter!=null){
            refreshOrLoad(list);
        }else {
            invoiceAdapter = new InvoiceAdapter(this, list, true);
            invoiceAdapter.setLoadingView(R.layout.load_loading_layout);
            invoiceAdapter.setLoadFailedView(R.layout.load_failed_layout);

            invoiceAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(boolean isReload) {
                    loadMore();
                }
            });
            invoiceAdapter.setOnItemClickListener(new OnItemClickListener<InvoiceBean>() {
                @Override
                public void onItemClick(ViewHolder viewHolder, InvoiceBean data, int position) {
                    Bundle bundle = new Bundle();
                    bundle.putString("company_name",data.getCompany_name());
                    bundle.putString("ITIN",data.getTaxpayer_number());
                    bundle.putString("address",data.getAddress());
                    bundle.putString("phone",data.getPhone());
                    bundle.putString("opening_bank",data.getOpen_bank());
                    bundle.putString("bank_account",data.getBank_account());
                    bundle.putString("tax_rate",data.getTax_rate());
                    bundle.putString("invoice_value",data.getAmount());
                    bundle.putString("invoice_content",data.getContent());
                    IntentUtil.getInstance().intent(InvoiceActivity.this,InvoiceDetailsActivity.class,bundle);
                }
            });
            WrapContentLinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(invoiceAdapter);
        }
    }

    @OnClick({R.id.back, R.id.right_icon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.right_icon:
                IntentUtil.getInstance().intent(this,AddInvoiceActivity.class,null);
                break;
        }
    }
    private void refreshOrLoad(List<InvoiceBean> list){
        if (n==1){
            invoiceAdapter.setNewData(list);
        }else {
            if (list.size()==0){
                invoiceAdapter.setLoadEndView(R.layout.load_end_layout);
                invoiceAdapter.loadEnd();
            }else {
                invoiceAdapter.setLoadMoreData(list);
            }
        }
    }
}