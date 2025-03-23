package com.nntk.nba.watch.lillard;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.nntk.nba.watch.lillard.receiver.Receiver;

import java.text.SimpleDateFormat;


public class TimerBroadcastHelper {

    public static final String ACTION_GAME = "ACTION_GAME";


    private static final int ALARM_REQUEST_CODE = 0x1234;

    public static void setRepeatingAlarm(Context context) {


        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, Receiver.class);
        intent.setAction(TimerBroadcastHelper.ACTION_GAME);

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = dateformat.format(System.currentTimeMillis());
        intent.putExtra("logTime", dateStr);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context,
                ALARM_REQUEST_CODE,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        // 设置从现在开始每隔 10 秒触发一次广播。
        long intervalMillis = 60 * 1000;

        // 使用 setExactAndAllowWhileIdle() 以确保即使在设备处于空闲状态时也能触发。
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + intervalMillis, pendingIntent);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), intervalMillis, pendingIntent);

    }


    public static void cancelAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, Receiver.class);
        intent.setAction(TimerBroadcastHelper.ACTION_GAME);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context,
                ALARM_REQUEST_CODE,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        alarmManager.cancel(pendingIntent);
    }
}