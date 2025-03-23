package com.nntk.nba.watch.lillard.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamEntity {
    public String teamName;
    private String teamNameZh;

    private int simpleFrameSize;

    private String bgColor;

    private int movie2015FrameSize;

    private int movie2016FrameSize;


    private String scoreBoardColor;


    private String city;
}
