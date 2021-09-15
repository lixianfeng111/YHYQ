package com.yhhl.yhyq.fragment;

import android.view.View;
import android.widget.LinearLayout;

import com.yhhl.yhyq.R;
import com.yhhl.yhyq.base.BaseFragment;
import com.yhhl.yhyq.base.BasePresenter;
import com.yhhl.yhyq.util.IntentUtil;
import com.yhhl.yhyq.workbench.addresslist.activity.AddressListActivity;
import com.yhhl.yhyq.workbench.expense.activity.ExpenseActivity;
import com.yhhl.yhyq.workbench.invoice.activity.InvoiceActivity;
import com.yhhl.yhyq.workbench.projectapproval.activity.ProjectApprovalActivity;
import com.yhhl.yhyq.workbench.task.activity.TaskActivity;

public class WorkBenchFragment extends BaseFragment implements View.OnClickListener {

    private LinearLayout expense;//报销
    private LinearLayout task;
    private LinearLayout invoice;
    private LinearLayout project_approval;
    private LinearLayout book;

    @Override
    public void initView() {

        expense = view.findViewById(R.id.expense);
        task = view.findViewById(R.id.task);
        invoice = view.findViewById(R.id.invoice);
        project_approval = view.findViewById(R.id.project_approval);
        book = view.findViewById(R.id.book);
    }

    @Override
    public void initListener() {
        expense.setOnClickListener(this);
        task.setOnClickListener(this);
        invoice.setOnClickListener(this);
        project_approval.setOnClickListener(this);
        book.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayoutId() {
        return R.layout.fragment_workbench;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.expense:
                IntentUtil.getInstance().intent(getContext(), ExpenseActivity.class,null);
                break;
            case R.id.task:
                IntentUtil.getInstance().intent(getContext(), TaskActivity.class,null);
                break;
            case R.id.invoice:
                IntentUtil.getInstance().intent(getContext(), InvoiceActivity.class,null);
                break;
            case R.id.project_approval:
                IntentUtil.getInstance().intent(getContext(), ProjectApprovalActivity.class,null);
                break;
            case R.id.book:
                IntentUtil.getInstance().intent(getContext(), AddressListActivity.class,null);
                break;
        }
    }
}
