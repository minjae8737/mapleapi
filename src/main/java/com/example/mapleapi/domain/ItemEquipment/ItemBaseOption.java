package com.example.mapleapi.domain.ItemEquipment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * "item_base_option": {
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
 *         "max_hp_rate": "string",
 *         "max_mp_rate": "string",
 *         "base_equipment_level": 0
 *       },
 */

@Getter @Setter
public class ItemBaseOption {

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
    private String all_stat;
    private String max_hp_rate;
    private String max_mp_rate;
    private int base_equipment_level;

}
