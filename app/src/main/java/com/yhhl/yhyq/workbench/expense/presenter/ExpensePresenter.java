package com.yhhl.yhyq.workbench.expense.presenter;

import com.yhhl.yhyq.base.BaseCallBack;
import com.yhhl.yhyq.base.BaseEntity;
import com.yhhl.yhyq.base.BaseModel;
import com.yhhl.yhyq.base.BasePresenter;
import com.yhhl.yhyq.base.ServerException;
import com.yhhl.yhyq.net.GsonUtil;
import com.yhhl.yhyq.net.HttpUrlUtils;
import com.yhhl.yhyq.util.ToastUtil;
import com.yhhl.yhyq.workbench.expense.ExpenseModel;
import com.yhhl.yhyq.workbench.expense.bean.ExpenseBean;
import com.yhhl.yhyq.workbench.expense.view.ExpenseView;
import java.util.HashMap;
import java.util.List;

public class ExpensePresenter extends BasePresenter<ExpenseView> {

    private final ExpenseModel expenseModel;
    private final BaseModel baseModel;

    public ExpensePresenter(ExpenseView iBaseView) {
        super(iBaseView);
        expenseModel = new ExpenseModel();
        baseModel = new BaseModel();
    }
    public void find_expense(String page){
        final HashMap<String,Object> map=new HashMap<>();
        map.put("page",page);
        map.put("per_page","20");

        expenseModel.find_expense(HttpUrlUtils.EXPENSE, map, new BaseCallBack<List<ExpenseBean>>() {
            @Override
            public void requestError(ServerException e) {
                getiBaseView().onDataFailed(e.getMessage());
                ServerException.getMessageAndCode();
            }

            @Override
            public void requestSuccess(List<ExpenseBean> expenseBeans) {
                if (expenseBeans!=null){
                    getiBaseView().onDataList(expenseBeans);
                }else {
                    ToastUtil.showToast("暂未发现发票");
                }
            }
        });
    }

    public void add_expense(HashMap<String, Object> map){
        String s = GsonUtil.getInstance().mGson.toJson(map);
        getiBaseView().showLoading();
        baseModel.doPost(HttpUrlUtils.EXPENSE, s, new BaseCallBack() {
            @Override
            public void requestError(ServerException e) {
                getiBaseView().hideLoading();
                getiBaseView().onDataFailed(e.getMessage());
            }

            @Override
            public void requestSuccess(Object o) {
                getiBaseView().hideLoading();
                getiBaseView().onAddExpenseSuccess();
            }
        },new BaseEntity());
    }
}