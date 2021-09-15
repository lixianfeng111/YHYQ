package com.yhhl.yhyq.fragment;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.google.android.material.tabs.TabLayout;
import com.yhhl.yhyq.R;
import com.yhhl.yhyq.base.BaseFragment;
import com.yhhl.yhyq.base.BasePresenter;
import com.yhhl.yhyq.event.SearchEvent;
import com.yhhl.yhyq.event.SearchEvent2;
import com.yhhl.yhyq.util.EventBusUtil;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class HomeFragment extends BaseFragment {

    private ArrayList<String> titles;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView search;
    private EditText search_content;
    private LinearLayout search_linear;
    private int n=1;
    private TextView deal_with;
    private TextView no_deal_with;

    //    @BindView(R.id.tab)
//    TabLayout tabLayout;
//    @BindView(R.id.viewPager)
//    ViewPager viewPager;
    @Override
    public void initView() {
        tabLayout = view.findViewById(R.id.tab);
        viewPager = view.findViewById(R.id.viewPager);
        search = view.findViewById(R.id.search);
        search_content = view.findViewById(R.id.search_content);
        search_linear = view.findViewById(R.id.search_linear);
        deal_with = view.findViewById(R.id.deal_with);
        no_deal_with = view.findViewById(R.id.no_deal_with);
//        search_content.setFocusable(false);
    }

    @Override
    public void initListener() {

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = search_content.getText().toString();
            }
        });

        search_content.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                search_content.setFocusable(true);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // 先隐藏键盘
                    ((InputMethodManager)search_content.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    //实现自己的搜索逻辑
                    String s = search_content.getText().toString().trim();
                    if (!s.isEmpty()){
                        EventBusUtil.post(new SearchEvent(s));
                        EventBusUtil.post(new SearchEvent2(s));
                        search_content.setText("");
                    }
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void initData() {
        //设置界面文件和文字一一对应
        titles = new ArrayList<>();
        titles.add("待办");
        titles.add("通知");
        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new BackLogFragment());
        list.add(new InformFragment());

        //预加载
        viewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
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
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==0){
                    n=1;
                }else {
                    n=2;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setOffscreenPageLimit(list.size());
        //将viewpager与tabLayout绑定
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.home_tab_content, null);
            TextView textView = view.findViewById(R.id.tab_content_text);
            ImageView imageView = view.findViewById(R.id.tab_content_image);
            LinearLayout backlog_linear = view.findViewById(R.id.backlog_linear);

            textView.setText(titles.get(i));
            if (i==0){
                textView.setTextColor(getResources().getColor(R.color.color_5473E8));
                imageView.setImageResource(R.mipmap.main_down);
            }else {
                imageView.setVisibility(View.GONE);//通知不需要图标
            }

            //设置样式
            tabLayout.getTabAt(i).setCustomView(view);
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    for (int i = 0; i < list.size(); i++) {
                        TabLayout.Tab tab2 = tabLayout.getTabAt(i);
                        TextView tv_tab = tab2.getCustomView().findViewById(R.id.tab_content_text);//找到标题textview
                        //找到标题textview
                        if (i == tabLayout.getSelectedTabPosition()) {//选中的标题
                            tv_tab.setTextColor(getResources().getColor(R.color.color_5473E8));//设置标题文字颜色
                        } else {//未选中的标题
                            tv_tab.setTextColor(getResources().getColor(R.color.color_999999));
                        }
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (n==1){//当待办是选中页面的时候点击才会有弹出框
                        View view1 = LayoutInflater.from(getContext()).inflate(R.layout.popu_backlog, null, false);
                        TextView deal_with = view1.findViewById(R.id.deal_with);
                        TextView no_deal_with = view1.findViewById(R.id.no_deal_with);
                        PopupWindow popupWindow = new PopupWindow(view1, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        popupWindow.setOutsideTouchable(false);
                        popupWindow.setFocusable(true);
                        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_workbench));
                        popupWindow.showAsDropDown(backlog_linear,120,0);
                        deal_with.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                popupWindow.dismiss();
                            }
                        });
                        no_deal_with.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                popupWindow.dismiss();
                            }
                        });
                    }else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }

    @Override
    public int initLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
