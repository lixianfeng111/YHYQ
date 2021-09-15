package com.yhhl.yhyq.util;


import com.yhhl.yhyq.event.SearchEvent;
import com.yhhl.yhyq.event.SearchEvent2;

import org.greenrobot.eventbus.EventBus;

/**
 * eventBus工具类
 */
public class EventBusUtil {
    public static void register(Object o){
        if (!EventBus.getDefault().isRegistered(o)) {
            EventBus.getDefault().register(o);
        }
    }

    public static void unRegister(Object o){
        if (EventBus.getDefault().isRegistered(o)) {
            EventBus.getDefault().unregister(o);
        }
    }

    public static void post(SearchEvent searchEvent){
        EventBus.getDefault().post(searchEvent);
    }
    public static void post(SearchEvent2 searchEvent){
        EventBus.getDefault().post(searchEvent);
    }


}
