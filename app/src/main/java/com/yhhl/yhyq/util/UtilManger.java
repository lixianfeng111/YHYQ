package com.yhhl.yhyq.util;

import android.content.Context;

public class UtilManger {
    private static Context sContext;

    public static void init(Context context) {
        sContext = context;
    }

    public static Context getContext() {
        return sContext;
    }
}
