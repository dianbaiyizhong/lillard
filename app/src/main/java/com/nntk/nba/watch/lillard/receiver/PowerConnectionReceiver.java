package com.nntk.nba.watch.lillard.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.nntk.nba.watch.lillard.TimerBroadcastHelper;
import com.nntk.nba.watch.lillard.constant.SettingConst;
import com.nntk.nba.watch.lillard.http.ApiUtil;
import com.orhanobut.logger.Logger;

public class PowerConnectionReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_SCREEN_ON.equals(intent.getAction())) {
            Logger.i("亮屏");

            if (SPStaticUtils.getBoolean(SettingConst.LIVE_ENABLE)) {
                // 启动之前，先来一波请求
                ApiUtil.getResult(context);
                TimerBroadcastHelper.setRepeatingAlarm(context);
            }

        } else if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction())) {
            // 熄屏，取消定时器
            Logger.i("熄屏");
            TimerBroadcastHelper.cancelAlarm(context);
        }
    }
}