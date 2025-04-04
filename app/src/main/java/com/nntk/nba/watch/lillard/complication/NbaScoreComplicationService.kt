package com.nntk.nba.watch.lillard.complication

import android.app.PendingIntent
import android.content.Intent
import android.content.res.Configuration
import android.graphics.drawable.Icon
import androidx.wear.watchface.complications.data.ComplicationData
import androidx.wear.watchface.complications.data.ComplicationType
import androidx.wear.watchface.complications.data.LongTextComplicationData
import androidx.wear.watchface.complications.data.MonochromaticImage
import androidx.wear.watchface.complications.data.PlainComplicationText
import androidx.wear.watchface.complications.data.SmallImage
import androidx.wear.watchface.complications.data.SmallImageType
import androidx.wear.watchface.complications.datasource.ComplicationRequest
import androidx.wear.watchface.complications.datasource.SuspendingComplicationDataSourceService
import com.blankj.utilcode.util.ResourceUtils
import com.blankj.utilcode.util.SPStaticUtils
import com.nntk.nba.watch.lillard.TimerBroadcastHelper
import com.nntk.nba.watch.lillard.constant.SettingConst
import com.nntk.nba.watch.lillard.entity.GameInfo
import com.nntk.nba.watch.lillard.util.NbaUtils
import com.orhanobut.logger.Logger

/**
 * Skeleton for complication data source that returns short text.
 */
class NbaScoreComplicationService : SuspendingComplicationDataSourceService() {

    override fun getPreviewData(type: ComplicationType): ComplicationData? {
        if (type != ComplicationType.SMALL_IMAGE && type != ComplicationType.LONG_TEXT) {
            return null
        }
        return createComplicationData(
            NbaUtils.getDefault()
        )
    }


    override suspend fun onComplicationRequest(request: ComplicationRequest): ComplicationData? {


        SPStaticUtils.put(SettingConst.LIVE_ENABLE, true);
        TimerBroadcastHelper.setRepeatingAlarm(baseContext)
        return createComplicationData(
            NbaUtils.getGameInfo()
        )
    }


    private fun createComplicationData(
        gameInfo: GameInfo,
    ): ComplicationData {


        val homeIcon = ResourceUtils.getDrawableIdByName("qiumiwu_" + gameInfo.homeTeamEntity.teamName)
        val guestIcon =
            ResourceUtils.getDrawableIdByName("qiumiwu_" + gameInfo.guestTeamEntity.teamName)


        return LongTextComplicationData.Builder(
            text = PlainComplicationText.Builder(
                text = gameInfo.guestRate,
            ).build(),
            contentDescription = PlainComplicationText.Builder("contentDescription").build()
        ).setSmallImage(
            SmallImage.Builder(
                image = Icon.createWithResource(this, guestIcon),
                type = SmallImageType.ICON,
            ).build()
        ).setTapAction(
            PendingIntent.getService(
                this,
                0,
                Intent(this, MyTapService::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        ).setMonochromaticImage(
            MonochromaticImage.Builder(
                image = Icon.createWithResource(this, homeIcon),
            ).build(),
        ).setTitle(
            PlainComplicationText.Builder(
                text = gameInfo.homeRate,
            ).build(),
        ).build()
    }
}