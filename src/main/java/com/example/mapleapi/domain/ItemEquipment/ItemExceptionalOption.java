package com.example.mapleapi.domain.ItemEquipment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 *       "item_exceptional_option": {
 *         "str": "string",
 *         "dex": "string",
 *         "int": "string",
 *         "luk": "string",
 *         "max_hp": "string",
 *         "max_mp": "string",
 *         "attack_power": "string",
 *         "magic_power": "string"
 *       }
 */

@Getter @Setter
public class ItemExceptionalOption {

    private String str;
    private String dex;
    @JsonProperty("int")
    private String _int;
    private String luk;
    private String max_hp;
    private String max_mp;
    private String attack_power;
    private String magic_power;

}
