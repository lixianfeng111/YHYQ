package com.yhhl.yhyq.workbench.expense.view;

import com.yhhl.yhyq.base.IBaseView;
import com.yhhl.yhyq.workbench.expense.bean.ExpenseBean;

public interface ExpenseView extends IBaseView<ExpenseBean> {
    void onAddExpenseSuccess();
}
