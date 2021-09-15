package com.yhhl.yhyq.fragment;

import android.app.Dialog;
import android.content.pm.PackageManager;
import android.os.Handler;
import com.othershe.baseadapter.interfaces.OnLoadMoreListener;
import com.ps.mrcyclerview.WrapContentLinearLayoutManager;
import com.yhhl.yhyq.R;
import com.yhhl.yhyq.base.BaseFragment;
import com.yhhl.yhyq.dialog.DialogUtil;
import com.yhhl.yhyq.event.SearchEvent;
import com.yhhl.yhyq.home.BackLogAdapter;
import com.yhhl.yhyq.home.bean.BackLogBean;
import com.yhhl.yhyq.home.presenter.HomePresenter;
import com.yhhl.yhyq.home.view.BackLogView;
import com.yhhl.yhyq.util.EventBusUtil;
import com.yhhl.yhyq.workbench.projectapproval.bean.ProjectApprovalBean;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;

public class BackLogFragment extends BaseFragment<HomePresenter> implements BackLogView {

    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipe_layout;
    private BackLogAdapter backLogAdapter;
    private RecyclerView recyclerView;
    private boolean isFailed = true;
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
                miBasePresenter.back_log("","1",n+"","0");
            }
        });
    }

    @Override
    public void initData() {
        miBasePresenter.back_log("","1",n+"","0");
    }

    private void loadMore() {
        n++;
        miBasePresenter.back_log("","1",n+"","0");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Search(SearchEvent searchEvent){
        miBasePresenter.back_log(searchEvent.getText(),"1","1","0");
    }

    @Override
    public int initLayoutId() {
        return R.layout.fragment_backlog;
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
        if (backLogAdapter!=null){
            refreshOrLoad(list);
        }else {
            backLogAdapter = new BackLogAdapter(getContext(), list, true);
            backLogAdapter.setLoadingView(R.layout.load_loading_layout);
            backLogAdapter.setLoadFailedView(R.layout.load_failed_layout);
            backLogAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(boolean isReload) {
                    loadMore();
                }
            });
            WrapContentLinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(getContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(backLogAdapter);
            //初始化集合装载权限
            String[] PERMISSIONS = {
                    "android.permission.READ_EXTERNAL_STORAGE",
                    "android.permission.WRITE_EXTERNAL_STORAGE"};
            //检测是否有写的权限
            int permission = ContextCompat.checkSelfPermission(getContext(),
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(getActivity(), PERMISSIONS, 1);
            }
        }

    }

    private void refreshOrLoad(List<BackLogBean> list){
        if (n==1){
            backLogAdapter.setNewData(list);
        }else {
            if (list.size()==0){
                backLogAdapter.setLoadEndView(R.layout.load_end_layout);
                backLogAdapter.loadEnd();
            }else {
                backLogAdapter.setLoadMoreData(list);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBusUtil.unRegister(this);
    }
}
