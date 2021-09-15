package com.yhhl.yhyq.workbench.addresslist.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.OnClick;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.yhhl.yhyq.R;
import com.yhhl.yhyq.base.BaseActivity;
import com.yhhl.yhyq.workbench.addresslist.IndexBar.widget.IndexBar;
import com.yhhl.yhyq.workbench.addresslist.SideBar;
import com.yhhl.yhyq.workbench.addresslist.adapter.AddressListAdapter;
import com.yhhl.yhyq.workbench.addresslist.adapter.CityAdapter;
import com.yhhl.yhyq.workbench.addresslist.adapter.PopDepartAdapter;
import com.yhhl.yhyq.workbench.addresslist.bean.AddressListBean;
import com.yhhl.yhyq.workbench.addresslist.bean.CityBean;
import com.yhhl.yhyq.workbench.addresslist.bean.CompanyBean;
import com.yhhl.yhyq.workbench.addresslist.decoration.DividerItemDecoration;
import com.yhhl.yhyq.workbench.addresslist.decoration.TitleItemDecoration;
import com.yhhl.yhyq.workbench.addresslist.presenter.AddressListPresenter;
import com.yhhl.yhyq.workbench.addresslist.view.AddressListView;
import java.util.ArrayList;
import java.util.List;

public class AddressListActivity extends BaseActivity<AddressListPresenter> implements AddressListView {
//    @BindView(R.id.linear)
//    LinearLayout linear;
//    @BindView(R.id.phoneNumber)
//    TextView phoneNumber;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipe_layout;
    @BindView(R.id.company_name)
    TextView company_name;
    @BindView(R.id.department)
    TextView department;
    private ImageView back;
    private TextView title;
    private AddressListAdapter addressListAdapter;
    private RecyclerView recyclerView;
    private SideBar sidebar;
    private TextView letter;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mManager;
    private List<CityBean> mDatas;
    private List<AddressListBean> list2;
    private List<AddressListBean> list3;
    private TitleItemDecoration mDecoration;
    private List<CompanyBean> dept_list;
    /**
     * 右侧边栏导航区域
     */
    private IndexBar mIndexBar;

    /**
     * 显示指示器DialogText
     */
    private TextView mTvSideBarHint;
    @Override
    public void initView() {
        title = findViewById(R.id.title);
        back = findViewById(R.id.back);
        recyclerView = findViewById(R.id.recyclerView);
//        sidebar = findViewById(R.id.sidebar);
        letter = findViewById(R.id.letter);
        title.setText("通讯录");
        swipe_layout.setRefreshing(true);
        miBasePresenter.getCompany();
    }

    @Override
    public void initListener() {
//        sidebar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
//            @Override
//            public void onTouchingLetterChanged(String s) {
//                letter.setVisibility(View.VISIBLE);
//                letter.setText(s);
//            }
//
//            @Override
//            public void noTouchLetter() {
//                letter.setVisibility(View.GONE);
//            }
//        });

        swipe_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                miBasePresenter.getCompany();
            }
        });

    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_address_list;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public AddressListPresenter initPresenter() {
        return new AddressListPresenter(this);
    }

    /**
     * 组织数据源
     *
     * @param data
     * @return
     */
    private void initDatas(String[] data) {
        mDatas = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            CityBean cityBean = new CityBean();
            cityBean.setCity(data[i]);//设置城市名称
            mDatas.add(cityBean);
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onDataSuccess(AddressListBean data) {

    }

    @Override
    public void onDataFailed(String msg) {
        swipe_layout.setRefreshing(false);
    }

    @Override
    public void onDataList(List<AddressListBean> list) {
        swipe_layout.setRefreshing(false);
        if (mAdapter==null){
            list3=list;
            list2=list3;
            recyclerView.setLayoutManager(mManager = new LinearLayoutManager(this));
            //initDatas();
//        initDatas(getResources().getStringArray(R.array.provinces));
            //mDatas = new ArrayList<>();//测试为空或者null的情况 已经通过 2016 09 08
            mAdapter = new CityAdapter(this, list2);
            recyclerView.setAdapter(mAdapter);
            recyclerView.addItemDecoration(mDecoration = new TitleItemDecoration(this, list));
            //如果add两个，那么按照先后顺序，依次渲染。
            //recyclerView.addItemDecoration(new TitleItemDecoration2(this,mDatas));
            recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));//下划线

            //使用indexBar
//        mTvSideBarHint = (TextView) findViewById(R.id.tvSideBarHint);//HintTextView
            mIndexBar = (IndexBar) findViewById(R.id.indexBar);//IndexBar
            mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                    .setNeedRealIndex(true)//设置需要真实的索引
                    .setmLayoutManager(mManager)//设置RecyclerView的LayoutManager
                    .setmSourceDatas(list);//设置数据源
        }else {
            list2=list3;
            for (int i = 0; i < list2.size(); i++) {
                String name = list2.get(i).getName();
                boolean n = false;
                for (int i1 = 0; i1 < list.size(); i1++) {
                    String name1 = list.get(i1).getName();
                    if (name.equals(name1)){
                        n=true;
                        break;
                    }
                }
                if (!n){
                    list2.remove(i);//删除数据源,移除集合中当前下标的数据
                    mAdapter.notifyItemRemoved(i);//刷新被删除的地方
//                    mAdapter.notifyItemRangeChanged(); //刷新被删除数据，以及其后面的数据

//                    mAdapter.notifyItemRemoved(i);
                }
            }
        }

    }

    @Override
    public void onFindCompanySuccess(List<CompanyBean> list) {
        miBasePresenter.getList("");
        String name = list.get(0).getName();
        String name1 = list.get(1).getName();
        company_name.setText(name);
        department.setText(name1);
        dept_list=list;
        CompanyBean companyBean = dept_list.get(0);
        dept_list.remove(companyBean);
    }

    @Override
    public void onFindCompanyFailed() {
        swipe_layout.setRefreshing(false);
    }

    @OnClick({R.id.back, R.id.department})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.department:
                department_pop();
                break;
        }
    }

    private void department_pop() {
        View view = LayoutInflater.from(this).inflate(R.layout.popu_department, null, false);
        RecyclerView recyclerView_pop = view.findViewById(R.id.recyclerView_pop);
        PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_workbench));
        popupWindow.showAsDropDown(department);
        String s = department.getText().toString();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView_pop.setLayoutManager(linearLayoutManager);
        PopDepartAdapter popDepartAdapter = new PopDepartAdapter(this, dept_list,s);
        recyclerView_pop.setAdapter(popDepartAdapter);
        popDepartAdapter.setOnChangeDepartmentListener(new PopDepartAdapter.OnChangeDepartmentListener() {
            @Override
            public void dept_name(String name, int id) {
                department.setText(name);
                list2=list3;
                mAdapter.notifyDataSetChanged();
                miBasePresenter.getList(id+"");
                popupWindow.dismiss();
            }
        });
    }
}