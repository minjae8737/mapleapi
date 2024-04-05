package com.example.mapleapi.domain.ItemEquipment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * "item_total_option": {
 *         "str": "string",
 *         "dex": "string",
 *         "int": "string",
 *         "luk": "string",
 *         "max_hp": "string",
 *         "max_mp": "string",
 *         "attack_power": "string",
 *         "magic_power": "string",
 *         "armor": "string",
 *         "speed": "string",
 *         "jump": "string",
 *         "boss_damage": "string",
 *         "ignore_monster_armor": "string",
 *         "all_stat": "string",
 *         "damage": "string",
 *         "equipment_level_decrease": 0,
 *         "max_hp_rate": "string",
 *         "max_mp_rate": "string"
 *       }
 */

@Getter @Setter
public class ItemTotalOption {
    private String str;
    private String dex;
    @JsonProperty("int")
    private String _int;
    private String luk;
    private String max_hp;
    private String max_mp;
    private String attack_power;
    private String magic_power;
    private String armor;
    private String speed;
    private String jump;
    private String boss_damage;
    private String ignore_monster_armor;
    private String all_stat;
    private String damage;
    private int equipment_level_decrease;
    private String max_hp_rate;
    private String max_mp_rate;
}
