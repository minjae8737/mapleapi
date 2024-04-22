package com.example.mapleapi.domain.Character;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.time.LocalDateTime;


/**
 *   "date": "2024-04-03T00:00+09:00",
 *   "character_name": "르샤마지끄",
 *   "world_name": "스카니아",
 *   "character_gender": "여",
 *   "character_class": "아크메이지(썬,콜)",
 *   "character_class_level": "6",
 *   "character_level": 278,
 *   "character_exp": 10800695284299,
 *   "character_exp_rate": "71.325",
 *   "character_guild_name": null,
 *   "character_image": "https://open.api.nexon.com/static/maplestory/Character/MOKNDFCAEGHMMPKLPCMKHDFIHHBELCJKLPLEIEOBOKBCPCAILJNCPADDBCCMONPEDLMCNBIDLIPDNMJOPKGHKLFMPJCKKNGMAKFNIJLEHDKDPLPONANAGBHGIGMCFNJDCONPCNMMGGANDKPPJGHCOOJIEECPCCDAJMADCCOFMLCKACAJJDJKKCIHNIJCKPFKFBEDDNOKGDDBLFCIOKPPBPAPLABPMIDKNKHMOIGKDMAMDFKCFGJOOIDJIKCIHCEA.png"
 */

@Slf4j
@Getter
@Setter
public class CharacterBasic {

    private LocalDateTime date;
    private String character_name;
    private String world_name;
    private String character_gender;
    private String character_class;
    private int character_class_level;
    private int character_level;
    private long character_exp;
    private double character_exp_rate;
    private String character_guild_name;
    private String character_image;

    public void setDate(Timestamp dateTime) {
        date = dateTime.toLocalDateTime();
    }

    public String getDate() {
        return date.toString();
    }
}
