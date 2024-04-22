package com.example.mapleapi.domain.ItemEquipment;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * "item_equipment": [
 * {
 * "item_equipment_part": "string",
 * "item_equipment_slot": "string",
 * "item_name": "string",
 * "item_icon": "string",
 * "item_description": "string",
 * "item_shape_name": "string",
 * "item_shape_icon": "string",
 * "item_gender": "string",
 * "item_total_option": {
 * "str": "string",
 * "dex": "string",
 * "int": "string",
 * "luk": "string",
 * "max_hp": "string",
 * "max_mp": "string",
 * "attack_power": "string",
 * "magic_power": "string",
 * "armor": "string",
 * "speed": "string",
 * "jump": "string",
 * "boss_damage": "string",
 * "ignore_monster_armor": "string",
 * "all_stat": "string",
 * "damage": "string",
 * "equipment_level_decrease": 0,
 * "max_hp_rate": "string",
 * "max_mp_rate": "string"
 * },
 * "item_base_option": {
 * "str": "string",
 * "dex": "string",
 * "int": "string",
 * "luk": "string",
 * "max_hp": "string",
 * "max_mp": "string",
 * "attack_power": "string",
 * "magic_power": "string",
 * "armor": "string",
 * "speed": "string",
 * "jump": "string",
 * "boss_damage": "string",
 * "ignore_monster_armor": "string",
 * "all_stat": "string",
 * "max_hp_rate": "string",
 * "max_mp_rate": "string",
 * "base_equipment_level": 0
 * },
 * "potential_option_grade": "string",
 * "additional_potential_option_grade": "string",
 * "potential_option_1": "string",
 * "potential_option_2": "string",
 * "potential_option_3": "string",
 * "additional_potential_option_1": "string",
 * "additional_potential_option_2": "string",
 * "additional_potential_option_3": "string",
 * "equipment_level_increase": 0,
 * "item_exceptional_option": {
 * "str": "string",
 * "dex": "string",
 * "int": "string",
 * "luk": "string",
 * "max_hp": "string",
 * "max_mp": "string",
 * "attack_power": "string",
 * "magic_power": "string"
 * },
 * "item_add_option": {
 * "str": "string",
 * "dex": "string",
 * "int": "string",
 * "luk": "string",
 * "max_hp": "string",
 * "max_mp": "string",
 * "attack_power": "string",
 * "magic_power": "string",
 * "armor": "string",
 * "speed": "string",
 * "jump": "string",
 * "boss_damage": "string",
 * "damage": "string",
 * "all_stat": "string",
 * "equipment_level_decrease": 0
 * },
 * "growth_exp": 0,
 * "growth_level": 0,
 * "scroll_upgrade": "string",
 * "cuttable_count": "string",
 * "golden_hammer_flag": "string",
 * "scroll_resilience_count": "string",
 * "scroll_upgradable_count": "string",
 * "soul_name": "string",
 * "soul_option": "string",
 * "item_etc_option": {
 * "str": "string",
 * "dex": "string",
 * "int": "string",
 * "luk": "string",
 * "max_hp": "string",
 * "max_mp": "string",
 * "attack_power": "string",
 * "magic_power": "string",
 * "armor": "string",
 * "speed": "string",
 * "jump": "string"
 * },
 * "starforce": "string",
 * "starforce_scroll_flag": "string",
 * "item_starforce_option": {
 * "str": "string",
 * "dex": "string",
 * "int": "string",
 * "luk": "string",
 * "max_hp": "string",
 * "max_mp": "string",
 * "attack_power": "string",
 * "magic_power": "string",
 * "armor": "string",
 * "speed": "string",
 * "jump": "string"
 * },
 * "special_ring_level": 0,
 * "date_expire": "2023-12-21T17:28+09:00"
 * }
 * ]
 */

@Slf4j
@Getter
@Setter
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

    public void setPotential_option_1(String potential_option_1) {
        this.potential_option_1 = rename_potential_option(potential_option_1);
    }

    public void setPotential_option_2(String potential_option_2) {
        this.potential_option_2 = rename_potential_option(potential_option_2);
    }

    public void setPotential_option_3(String potential_option_3) {
        this.potential_option_3 = rename_potential_option(potential_option_3);
    }

    public void setAdditional_potential_option_1(String additional_potential_option_1) {
        this.additional_potential_option_1 = rename_potential_option(additional_potential_option_1);
    }

    public void setAdditional_potential_option_2(String additional_potential_option_2) {
        this.additional_potential_option_2 = rename_potential_option(additional_potential_option_2);
    }

    public void setAdditional_potential_option_3(String additional_potential_option_3) {
        this.additional_potential_option_3 = rename_potential_option(additional_potential_option_3);
    }

    private String rename_potential_option(String potential_option) {

        if(potential_option == null) return null;
        String optionName = potential_option.replace(":", "");
        String replacedOptionName = optionName;

        if (optionName.contains("최대 HP")) replacedOptionName = optionName.replace("최대 HP", "HP");
        if (optionName.contains("최대 MP")) replacedOptionName = optionName.replace("최대 MP", "MP");
        if (optionName.contains("메소 획득량")) replacedOptionName = optionName.replace("메소 획득량", "메획");
        if (optionName.contains("아이템 드롭률")) replacedOptionName = optionName.replace("아이템 드롭률", "아획");
        if (optionName.contains("캐릭터 기준 9레벨 당 ")) replacedOptionName = optionName.replace("캐릭터 기준 9레벨 당 ", "9렙당");
        if (optionName.contains("보스 몬스터 공격 시 데미지")) replacedOptionName = optionName.replace("보스 몬스터 공격 시 데미지", "보공");
        if (optionName.contains("몬스터 방어율 무시")) replacedOptionName = optionName.replace("몬스터 방어율 무시", "방무");
        if (optionName.contains("크리티컬 데미지")) replacedOptionName = optionName.replace("크리티컬 데미지", "크뎀");
        if (optionName.contains("모든 스킬의 재사용 대기시간")){
            replacedOptionName = optionName.replace("모든 스킬의 재사용 대기시간", "쿨감");
            if (optionName.contains("(10초 이하는 10%감소, 5초 미만으로 감소 불가)")) replacedOptionName = replacedOptionName.replace("(10초 이하는 10%감소, 5초 미만으로 감소 불가)", "");
            if (optionName.contains("(10초 이하는 5%감소, 5초 미만으로 감소 불가)")) replacedOptionName = replacedOptionName.replace("(10초 이하는 5%감소, 5초 미만으로 감소 불가)", "");
        }

        return replacedOptionName.replaceAll("\\s", "");
    }
}
