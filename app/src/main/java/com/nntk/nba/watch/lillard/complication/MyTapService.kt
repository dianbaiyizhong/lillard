package com.nntk.nba.watch.lillard.complication

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.nntk.nba.watch.lillard.http.ApiUtil

class MyTapService : Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // 在这里执行你的简单方法
        Log.i("TAP_ACTION", "点击事件触发！")

        ApiUtil.getResult(baseContext)

        // 例如：更新数据、发送网络请求等
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null
}