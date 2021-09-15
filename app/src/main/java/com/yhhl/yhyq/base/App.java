package com.yhhl.yhyq.base;

import android.app.Application;
import android.content.Context;
import com.yhhl.yhyq.net.LogUtil;
import com.yhhl.yhyq.net.OkHttpUtil;
import com.yhhl.yhyq.util.SpzUtils;
import com.yhhl.yhyq.util.UtilManger;

//App类
public class App extends Application {

    public static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        SpzUtils.init(sContext);
        OkHttpUtil.init(sContext);
        UtilManger.init(sContext);
        LogUtil.init();
    }

}