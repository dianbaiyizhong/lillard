package com.nntk.nba.watch.lillard.util;


import static com.nntk.nba.watch.lillard.constant.SettingConst.LIVE_GAME_INFO;

import com.alibaba.fastjson2.JSON;
import com.blankj.utilcode.util.SPStaticUtils;
import com.nntk.nba.watch.lillard.entity.GameInfo;

public class NbaUtils {


    public static GameInfo getGameInfo() {
        return JSON.parseObject(SPStaticUtils.getString(LIVE_GAME_INFO), GameInfo.class);
    }

    public static GameInfo getDefault() {
        return GameInfo.builder()
                .homeTeam("主队").guestTeam("客队")
                .homeRate("主队比分").guestRate("客队比分")
                .build();
    }
}
