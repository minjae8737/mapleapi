package com.example.mapleapi.domain.ItemEquipment;

import lombok.Getter;
import lombok.Setter;

/**
 *   "dragon_equipment": [
 *     {
 *       "item_equipment_part": "string",
 *       "equipment_slot": "string",
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
 */

@Getter @Setter
public class DragonEquipment {

    private String item_equipment_part;
    private String item_equipment_slot;
    private String item_name;
    private String item_icon;
    private String item_description;
    private String item_shape_name;
    private String item_shape_icon;
    private String item_gender;
    private ItemTotalOption item_total_option;

}
