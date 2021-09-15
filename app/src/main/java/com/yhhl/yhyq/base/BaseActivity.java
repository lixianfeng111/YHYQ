package com.yhhl.yhyq.base;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.yhhl.yhyq.R;
import com.yhhl.yhyq.net.LogUtil;
import com.yhhl.yhyq.receiver.NetReceiver;
import com.yhhl.yhyq.statusBar.StatusManager;
import com.yhhl.yhyq.titlebar.SimpleTitleBar;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * baseActivity
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements NetReceiver.NetStatuMonitor {
    protected T miBasePresenter;
    private StatusView statusView;
    private NetReceiver netBroadcastReceiver;
    private boolean netStatus;
    private ErrorView errorView;
    protected ViewManager mViewManager;

    private BaseTitleBar mBaseTitleBar;
    private FrameLayout baseContent;
    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        errorView = new ErrorView(this);
        ((ViewGroup) getWindow().getDecorView()).addView(errorView);
        if (initLayoutId() != 0) {
            initVariable();
            setContentView(initLayoutId());
            bind = ButterKnife.bind(this);
            StatusManager.getInstance().initStatusTheme(this);
            StatusManager.getInstance().loadDefaultStatusTheme(this);
            miBasePresenter = initPresenter();
            initView();
            AppManager.getAppManager().addActivity(this);
            initData();
            initListener();
            //注册广播
            if (netBroadcastReceiver == null) {
                netBroadcastReceiver = new NetReceiver();
                IntentFilter filter = new IntentFilter();
                filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
                registerReceiver(netBroadcastReceiver, filter);
                //设置监听
                netBroadcastReceiver.setNetStatuMonitor(this);
            }

        } else {
            finish();
        }
    }


    public abstract void initView();

    public abstract void initListener();

    public abstract void initData();

    public abstract int initLayoutId();

    //初始化变量
    public abstract void initVariable();

    public abstract T initPresenter();

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        initStatuView(view);
    }

    @Override
    public void setContentView(int layoutResID) {
        View inflate = View.inflate(this, layoutResID, null);
//        baseContent.addView(inflate);
        statusView = initStatuView(inflate);
        super.setContentView(statusView);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        statusView = initStatuView(view);
        super.setContentView(view, params);
    }

    private StatusView initStatuView(View content) {
        StatusView.Builder builder = new StatusView.Builder(this);
        statusView = builder.contentView(content)
                .emptyId(R.layout.layout_empity2)
                .erroryId(R.layout.net)
                .loadingId(R.layout.layout_loading)
                .build();
        return statusView;
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    //销毁
    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (miBasePresenter != null) {
            miBasePresenter.onDestory();
        }
    }

    /**
     * ondestory中取消注册
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销广播
        if (netBroadcastReceiver!=null){
            unregisterReceiver(netBroadcastReceiver);
        }
        if (bind!=null){
            bind.unbind();
        }
    }

    @Override
    public void onNetChange(boolean netStatus) {
        this.netStatus = netStatus;
        isNetConnect();
    }

    /**
     * 监听网络状态做出相应改变
     */
    private void isNetConnect() {
        if (netStatus) {
            errorView.setVisibility(View.GONE);
        } else {
            //无网状态
            errorView.setVisibility(View.VISIBLE);
        }
    }


    //显示空布局
    public void showEmptyLayout() {
        statusView.showEmpty();
    }

    //加载错误的布局
    public void showErrorLayout() {
        statusView.showError();
    }

    //显示正在加载布局
    public void showLoadingLayout() {
        statusView.showLoading();
    }

    //显示内容
    public void showContent() {
        statusView.showContent();
    }

    /**
     * 获取titleBar的布局
     *
     * @return
     */
    public BaseTitleBar createTitleBar() {
        SimpleTitleBar titleBar = new SimpleTitleBar(this);
        titleBar.setLeftIcon(getLeftIcon()).setLeftClickListener(getLeftClickListener())
                .setTitleContent(getTitleContent());
        return titleBar;
    }

    /**
     * 获取页面的内容布局
     *
     * @return
     */
//    public abstract @LayoutRes
//    int getContentViewRes();

    /**
     * 获取左侧图标
     *
     * @return
     */
    public int getLeftIcon() {
        return R.mipmap.icon;
    }

    /**
     * 左侧按钮 点击事件
     *
     * @return
     */
    public View.OnClickListener getLeftClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        };
    }

    /**
     * 页面标题信息
     *
     * @return
     */
    public String getTitleContent() {
        return "";
    }

    /**
     * titleBar 的处理
     *
     * @param titleBar
     */
    protected void onCreateTitleBar(BaseTitleBar titleBar) {

    }
}
