package com.example.mapleapi.domain.Stat;

import lombok.Getter;
import lombok.Setter;

/**
 "final_stat": [
 {
 "stat_name": "최소 스탯 공격력",
 "stat_value": "43.75"
 }
 ]
 */

@Getter @Setter
public class FinalStat {

    private String stat_name;
    private String stat_value;
}
