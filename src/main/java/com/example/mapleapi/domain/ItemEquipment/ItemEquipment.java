package com.example.mapleapi.domain.ItemEquipment;

import lombok.Getter;
import lombok.Setter;

/**
 *     "item_equipment": [
 *     {
 *       "item_equipment_part": "string",
 *       "item_equipment_slot": "string",
 *       "item_name": "string",
 *       "item_icon": "string",
 *       "item_description": "string",
 *       "item_shape_name": "string",
 *       "item_shape_icon": "string",
 *       "item_gender": "string",
 *       "item_total_option": {
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
 *       },
 *       "item_base_option": {
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
 *       "potential_option_grade": "string",
 *       "additional_potential_option_grade": "string",
 *       "potential_option_1": "string",
 *       "potential_option_2": "string",
 *       "potential_option_3": "string",
 *       "additional_potential_option_1": "string",
 *       "additional_potential_option_2": "string",
 *       "additional_potential_option_3": "string",
 *       "equipment_level_increase": 0,
 *       "item_exceptional_option": {
 *         "str": "string",
 *         "dex": "string",
 *         "int": "string",
 *         "luk": "string",
 *         "max_hp": "string",
 *         "max_mp": "string",
 *         "attack_power": "string",
 *         "magic_power": "string"
 *       },
 *       "item_add_option": {
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
 *         "damage": "string",
 *         "all_stat": "string",
 *         "equipment_level_decrease": 0
 *       },
 *       "growth_exp": 0,
 *       "growth_level": 0,
 *       "scroll_upgrade": "string",
 *       "cuttable_count": "string",
 *       "golden_hammer_flag": "string",
 *       "scroll_resilience_count": "string",
 *       "scroll_upgradable_count": "string",
 *       "soul_name": "string",
 *       "soul_option": "string",
 *       "item_etc_option": {
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
 *         "jump": "string"
 *       },
 *       "starforce": "string",
 *       "starforce_scroll_flag": "string",
 *       "item_starforce_option": {
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
 *         "jump": "string"
 *       },
 *       "special_ring_level": 0,
 *       "date_expire": "2023-12-21T17:28+09:00"
 *     }
 *   ]
 *
 */

@Getter @Setter
public class ItemEquipment {

    private String item_equipment_part;
    private String item_equipment_slot;
    private String item_name;
    private String item_icon;
    private String item_description;
    private String item_shape_name;
    private String item_shape_icon;
    private String item_gender;

    private ItemTotalOption item_total_option;
    private ItemBaseOption item_base_option;

    private String potential_option_grade;
    private String additional_potential_option_grade;
    private String potential_option_1;
    private String potential_option_2;
    private String potential_option_3;
    private String additional_potential_option_1;
    private String additional_potential_option_2;
    private String additional_potential_option_3;

    private int equipment_level_increase;

    private ItemExceptionalOption item_exceptional_option;
    private ItemAddOption item_add_option;

    private String growth_exp;
    private String growth_level;
    private String scroll_upgrade;
    private String cuttable_count;
    private String golden_hammer_flag;
    private String scroll_resilience_count;
    private String scroll_upgradable_count;
    private String soul_name;
    private String soul_option;

    private ItemEtcOption item_etc_option;

    private String starforce;
    private String starforce_scroll_flag;
    private ItemStarforceOption item_starforce_option;

    private int special_ring_level;
    private String date_expire;

}
