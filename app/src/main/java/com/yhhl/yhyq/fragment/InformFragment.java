package com.yhhl.yhyq.fragment;

import android.app.Dialog;
import android.os.Handler;

import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.interfaces.OnItemClickListener;
import com.othershe.baseadapter.interfaces.OnLoadMoreListener;
import com.ps.mrcyclerview.WrapContentLinearLayoutManager;
import com.yhhl.yhyq.R;
import com.yhhl.yhyq.base.BaseFragment;
import com.yhhl.yhyq.base.BasePresenter;
import com.yhhl.yhyq.dialog.DialogUtil;
import com.yhhl.yhyq.event.SearchEvent;
import com.yhhl.yhyq.event.SearchEvent2;
import com.yhhl.yhyq.home.BackLogAdapter;
import com.yhhl.yhyq.home.InformAdapter;
import com.yhhl.yhyq.home.bean.BackLogBean;
import com.yhhl.yhyq.home.presenter.HomePresenter;
import com.yhhl.yhyq.home.view.BackLogView;
import com.yhhl.yhyq.util.EventBusUtil;
import com.yhhl.yhyq.util.ToastUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;

public class InformFragment extends BaseFragment<HomePresenter> implements BackLogView {

    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipe_layout;
    private RecyclerView recyclerView;
    private boolean isFailed = true;
    private InformAdapter informAdapter;
    private Dialog dialog;
    private int n=1;
    @Override
    public void initView() {
        EventBusUtil.register(this);
        recyclerView = view.findViewById(R.id.recyclerView);
        swipe_layout.setRefreshing(true);
    }

    @Override
    public void initListener() {
        swipe_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                n=1;
                miBasePresenter.back_log2("","0",n+"","1");
            }
        });
    }

    @Override
    public void initData() {
        miBasePresenter.back_log2("","0",n+"","1");
    }

    private void loadMore() {
        n++;
        miBasePresenter.back_log2("","0",n+"","1");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Search(SearchEvent2 searchEvent){
        miBasePresenter.back_log(searchEvent.getText(),"0","1","1");
    }

    @Override
    public int initLayoutId() {
        return R.layout.fragment_inform;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public HomePresenter initPresenter() {
        return new HomePresenter(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onDataSuccess(BackLogBean data) {

    }

    @Override
    public void onDataFailed(String msg) {
        swipe_layout.setRefreshing(false);
    }

    @Override
    public void onDataList(List<BackLogBean> list) {
        swipe_layout.setRefreshing(false);
        if (informAdapter!=null){
            refreshOrLoad(list);
        }else {
            informAdapter = new InformAdapter(getContext(), list, true);
            informAdapter.setLoadingView(R.layout.load_loading_layout);
            informAdapter.setLoadFailedView(R.layout.load_failed_layout);
            informAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(boolean isReload) {
                    loadMore();
                }
            });
            WrapContentLinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(getContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(informAdapter);
        }

    }

    private void refreshOrLoad(List<BackLogBean> list){
        if (n==1){
            informAdapter.setNewData(list);
        }else {
            if (list.size()==0){
                informAdapter.setLoadEndView(R.layout.load_end_layout);
                informAdapter.loadEnd();
            }else {
                informAdapter.setLoadMoreData(list);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBusUtil.unRegister(this);
    }
}
