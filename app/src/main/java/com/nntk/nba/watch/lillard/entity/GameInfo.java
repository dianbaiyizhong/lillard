package com.nntk.nba.watch.lillard.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameInfo {

    public String homeTeam;
    private String guestTeam;

    public String homeRate;
    public String guestRate;


    public TeamEntity homeTeamEntity;
    public TeamEntity guestTeamEntity;

}
