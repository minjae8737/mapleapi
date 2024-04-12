package com.example.mapleapi.domain.Stat;


import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 {
 "date": "2023-12-21T00:00+09:00",
 "character_class": "string",
 "final_stat": [
 {
 "stat_name": "최소 스탯 공격력",
 "stat_value": "43.75"
 }
 ],
 "remain_ap": 0
 }
 */

@Getter @Setter
public class Stat {

    private LocalDateTime date;
    private String character_class;
    private List<FinalStat> final_stat;
    private int remain_ap;

    public void setDate(Timestamp dateTime) {
        date = dateTime.toLocalDateTime();
    }

    public String getDate() {
        return date.toString();
    }

    public String getStatByStatName(String statName) {
        for (FinalStat stat : final_stat) {
            if (stat.getStat_name().equals(statName)) {
                return stat.getStat_value();
            }
        }
        return "null";
    }
}
