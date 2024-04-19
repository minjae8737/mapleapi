package com.example.mapleapi.domain;

import com.example.mapleapi.domain.Stat.Stat;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CharacterDataDto {

    private CharacterBasic characterBasic;
    private Stat characterStat;
    private CharacterItemEquipment characterItemEquipment;
    private CharacterPopularity characterPopularity;
    private List<Exp> characterExpList;
}
