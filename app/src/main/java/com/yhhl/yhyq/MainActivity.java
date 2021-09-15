package com.yhhl.yhyq;

import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.ps.mrcyclerview.event.AddEvent;
import com.ps.mrcyclerview.event.DismissEvent;
import com.yhhl.yhyq.base.AppManager;
import com.yhhl.yhyq.base.BaseActivity;
import com.yhhl.yhyq.base.BasePresenter;
import com.yhhl.yhyq.fragment.HomeFragment;
import com.yhhl.yhyq.fragment.MyFragment;
import com.yhhl.yhyq.fragment.WorkBenchFragment;
import com.yhhl.yhyq.statusBar.StatusManager;
import com.yhhl.yhyq.tabview.TabViewChild;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends BaseActivity {
//    private TabView tabView;
    private List<TabViewChild> mTabViewList = new ArrayList<>();
    private FragmentManager supportFragmentManager;
    private List<Fragment> fragments=new ArrayList<>();
    private Fragment curFragment;
//    @BindView(R.id.viewPager)
    private ViewPager viewPager;
//    @BindView(R.id.radioGroup)
    private RadioGroup radioGroup;
    private long exitTime=0;

    @Override
    public void initView() {
//        tabView = findViewById(R.id.tabView);
        viewPager = findViewById(R.id.viewPager);
        radioGroup = findViewById(R.id.radioGroup);
        supportFragmentManager = getSupportFragmentManager();
        EventBus.getDefault().register(this);
    }

    @Override
    public void initListener() {

    }


    @Override
    public void initData() {
        //设置底部导航
//        TabViewChild tabViewChild_home = new TabViewChild(R.mipmap.home_page_checked, R.mipmap.home_page_checked, "首页", new HomeFragment());
//        TabViewChild tabViewChild_1 = new TabViewChild(R.mipmap.workbench_checked, R.mipmap.workbench_checked, "工作台", new WorkBenchFragment());
//        TabViewChild tabViewChild_2 = new TabViewChild(R.mipmap.personage_checked, R.mipmap.personage_checked, "个人", new MyFragment());
//
//        mTabViewList.add(tabViewChild_home);
//        mTabViewList.add(tabViewChild_1);
//        mTabViewList.add(tabViewChild_2);
//        tabView.setTabViewChild(mTabViewList, getSupportFragmentManager());
//
//        tabView.setOnTabChildClickListener(new TabView.OnTabChildClickListener() {
//            @Override
//            public void onTabChildClick(int position, ImageView imageView, TextView textView) {
//                clearAll(position);
//            }
//        });
        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new WorkBenchFragment());
        list.add(new MyFragment());
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        viewPager.setOffscreenPageLimit(list.size());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                radioGroup.check(radioGroup.getChildAt(position).getId());
                clearAll(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioHome:
                        StatusManager.getInstance().loadDefaultStatusTheme(MainActivity.this);
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.radioWork:
                        StatusManager.getInstance().immersionStatusBar(MainActivity.this,false);
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.radioMy:
                        StatusManager.getInstance().immersionStatusBar(MainActivity.this,false);
                        viewPager.setCurrentItem(2);
                        break;
                }
            }
        });
    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Subscribe
    public void eventAdd(AddEvent event){
        if(viewPager.getVisibility()!= View.GONE){
            viewPager.setVisibility(View.GONE);
        }
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        if(curFragment!=null){
            transaction.hide(curFragment);
        }
        curFragment = event.getNeedAdd();
        fragments.add(curFragment);
        transaction.add(R.id.frameLayout,curFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @Subscribe
    public void eventDismiss(DismissEvent event){
        Fragment needDismiss = event.getNeedDismiss();
        fragments.remove(needDismiss);
        fragments.remove(needDismiss);
        curFragment=null;
        if(fragments.size()>0){
            curFragment=fragments.get(fragments.size()-1);
        }else if(viewPager.getVisibility()!=View.VISIBLE){
            viewPager.setVisibility(View.VISIBLE);
        }
        supportFragmentManager.popBackStack();
    }



    private void clearAll(int position) {
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            supportFragmentManager.popBackStack();
        }
        curFragment=null;
        fragments.clear();
        viewPager.setVisibility(View.VISIBLE);
        viewPager.setCurrentItem(position);
//        tabview.setTabViewDefaultPosition(position);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        fragments.remove(curFragment);
        curFragment=null;
        if(fragments.size()>0){
            curFragment=fragments.get(fragments.size()-1);
        }else if(viewPager.getVisibility()!=View.VISIBLE){
            viewPager.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_BACK){
            exit();
            return false;
        }
        return super.onKeyDown(keyCode,event);
    }

    private void exit(){
        if((System.currentTimeMillis()-exitTime)>2000) {
            Toast.makeText(getApplicationContext(),
                    "再按一次退出云合易企", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else{
            AppManager.getAppManager().finishAllActivity();
            System.exit(0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}