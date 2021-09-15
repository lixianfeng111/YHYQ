package com.yhhl.yhyq.workbench.expense.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.interfaces.OnItemClickListener;
import com.othershe.baseadapter.interfaces.OnLoadMoreListener;
import com.ps.mrcyclerview.WrapContentLinearLayoutManager;
import com.yhhl.yhyq.R;
import com.yhhl.yhyq.base.BaseActivity;
import com.yhhl.yhyq.dialog.DialogUtil;
import com.yhhl.yhyq.util.IntentUtil;
import com.yhhl.yhyq.workbench.expense.adapter.ExpenseAdapter;
import com.yhhl.yhyq.workbench.expense.bean.ExpenseBean;
import com.yhhl.yhyq.workbench.expense.presenter.ExpensePresenter;
import com.yhhl.yhyq.workbench.expense.view.ExpenseView;
import com.yhhl.yhyq.workbench.task.bean.TaskBean;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;

public class ExpenseActivity extends BaseActivity<ExpensePresenter> implements ExpenseView {

    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipe_layout;
    private ImageView right_icon;
    private ExpenseAdapter expenseAdapter;
    private RecyclerView recyclerView;
    private ImageView back;
    private Dialog dialog;
    private int n=1;
    @SuppressLint("ResourceAsColor")
    @Override
    public void initView() {
        right_icon = findViewById(R.id.right_icon);
        recyclerView = findViewById(R.id.recyclerView);
        back = findViewById(R.id.back);
        swipe_layout.setRefreshing(true);
    }

    @Override
    public void initListener() {
        right_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtil.getInstance().intent(ExpenseActivity.this,NewAddWorkActivity.class,null);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
        right_icon.setVisibility(View.VISIBLE);
        miBasePresenter.find_expense("1");

    }

    private void loadMore() {
        n++;
        miBasePresenter.find_expense(n+"");
    }

    @Override
    public int initLayoutId() {
        return R.layout.fragment_expense;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public ExpensePresenter initPresenter() {
        return new ExpensePresenter(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onDataSuccess(ExpenseBean data) {

    }

    @Override
    public void onDataFailed(String msg) {
        swipe_layout.setRefreshing(false);
    }

    @Override
    public void onDataList(List<ExpenseBean> list) {
        swipe_layout.setRefreshing(false);
        if (expenseAdapter!=null){
            refreshOrLoad(list);
        }else {
            expenseAdapter = new ExpenseAdapter(ExpenseActivity.this,list,true);
            expenseAdapter.setLoadingView(R.layout.load_loading_layout);
            expenseAdapter.setLoadFailedView(R.layout.load_failed_layout);

            expenseAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(boolean isReload) {
                    loadMore();
                }
            });
            expenseAdapter.setOnItemClickListener(new OnItemClickListener<ExpenseBean>() {
                @Override
                public void onItemClick(ViewHolder viewHolder, ExpenseBean data, int position) {
                    Bundle bundle = new Bundle();
                    bundle.putString("organization",data.getCompany_name());
                    bundle.putString("department",data.getDept_name());
                    bundle.putString("expense_user",data.getBill_user_name());
                    bundle.putString("expense_money",data.getBill_value()+"");
                    bundle.putString("note",data.getRemark());
                    IntentUtil.getInstance().intent(ExpenseActivity.this, ExpenseDetailActivity.class,bundle);
                }
            });
            WrapContentLinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(ExpenseActivity.this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(expenseAdapter);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onAddExpenseSuccess() {

    }

    private void refreshOrLoad(List<ExpenseBean> list){
        if (n==1){
            expenseAdapter.setNewData(list);
        }else {
            if (list.size()==0){
                expenseAdapter.setLoadEndView(R.layout.load_end_layout);
                expenseAdapter.loadEnd();
            }else {
                expenseAdapter.setLoadMoreData(list);
            }
        }
    }
}
