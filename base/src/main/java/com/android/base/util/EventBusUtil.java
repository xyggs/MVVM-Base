package com.android.base.util;

import com.android.base.bean.Event;
import org.greenrobot.eventbus.EventBus;

/**
 * Created by android on 2019/7/5
 */
public class EventBusUtil {

    public static void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }

    public static void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    public static void sendEvent(Event event) {
        EventBus.getDefault().post(event);
    }

}
