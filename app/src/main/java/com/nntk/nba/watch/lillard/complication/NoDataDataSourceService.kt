/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nntk.nba.watch.lillard.complication

import androidx.wear.watchface.complications.data.ComplicationData
import androidx.wear.watchface.complications.data.ComplicationType
import androidx.wear.watchface.complications.data.NoDataComplicationData
import androidx.wear.watchface.complications.datasource.ComplicationDataSourceService
import androidx.wear.watchface.complications.datasource.ComplicationRequest
import com.blankj.utilcode.util.SPStaticUtils
import com.nntk.nba.watch.lillard.TimerBroadcastHelper
import com.nntk.nba.watch.lillard.constant.SettingConst
import com.orhanobut.logger.Logger

/**
 * A complication provider that always returns [ComplicationType.NO_DATA].
 */
class NoDataDataSourceService : ComplicationDataSourceService() {
    override fun onComplicationRequest(
        request: ComplicationRequest,
        listener: ComplicationRequestListener,
    ) {
        Logger.i("NoDataDataSourceService onComplicationRequest")
        SPStaticUtils.put(SettingConst.LIVE_ENABLE, false);

        TimerBroadcastHelper.cancelAlarm(baseContext)
        listener.onComplicationData(NoDataComplicationData())
    }

    override fun getPreviewData(type: ComplicationType): ComplicationData =
        NoDataComplicationData()
}
