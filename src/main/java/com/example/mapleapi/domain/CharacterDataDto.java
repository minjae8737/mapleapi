package com.example.mapleapi.domain;

import com.example.mapleapi.domain.Stat.Stat;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CharacterDataDto {

    private CharacterBasic characterBasic;
    private Stat characterStat;
    private CharacterItemEquipment characterItemEquipment;
}
