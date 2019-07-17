package com.android.base.ui;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import com.android.base.R;
import com.android.base.bean.Event;
import com.android.base.util.AppManager;
import com.android.base.util.EventBusUtil;
import com.android.base.util.StatusBarHelper;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by android on 2019/7/5
 */
public abstract class BaseActivity extends AppCompatActivity implements LifecycleOwner {

    public BaseActivity activity;
    public Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
        activity = this;
        context = this;

        if (getContentViewId() != 0) {
            setContentView(getContentViewId());
        }

        initData();
        initView();
        initListener();

        if (isRegisterEventBus()) {
            EventBusUtil.register(this);
        }
        AppManager.getAppManager().addActivity(activity);

    }


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setStatusBar();
    }

    protected void setStatusBar(){
        StatusBarHelper.setStatusBar(activity, ContextCompat.getColor(context,R.color.white));
    }

    protected abstract int getContentViewId();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void initListener();


    /**
     * 是否注册事件分发
     *
     * @return true绑定EventBus事件分发，默认不绑定，子类需要绑定的话复写此方法返回true.
     */
    protected boolean isRegisterEventBus() {
        return false;
    }
    //接收到分发到事件再转发
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusCome(Event event) {
        if (event != null) {
            receiveEvent(event);
        }
    }

    /**
     * 收到转发
     *
     * @param event 事件
     */
    protected void receiveEvent(Event event) {
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(activity);
        if (isRegisterEventBus()) {
            EventBusUtil.unregister(this);
        }
    }
}
