package com.example.mapleapi.controller;

import com.example.mapleapi.domain.*;
import com.example.mapleapi.domain.Stat.Stat;
import com.example.mapleapi.service.ApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class ApiController {

    private final ApiService apiService;

    @GetMapping("/")
    public String showHomepage(Model model) {
        log.info("show homepage");

        model.addAttribute("characterName", "");
        return "home";
    }

    @GetMapping("/search")
    public String searchCharacterName(@RequestParam String characterName, Model model) {

        log.info("Start getCharacterocid ={}", characterName);
        CharacterDataDto characterData = apiService.getCharacterData(characterName);

        CharacterBasic characterBasic = characterData.getCharacterBasic();
        CharacterItemEquipment characterItemEquipment = characterData.getCharacterItemEquipment();
        Stat characterStat = characterData.getCharacterStat();
        CharacterPopularity characterPopularity = characterData.getCharacterPopularity();
        List<Exp> characterExpList = characterData.getCharacterExpList();

        model.addAttribute("characterBasic", characterBasic);
        model.addAttribute("characterItemEquipment", characterItemEquipment);
        model.addAttribute("characterStat", characterStat);
        model.addAttribute("characterPopularity", characterPopularity);
        model.addAttribute("characterExpList", characterExpList);

        return "userdata";
    }

}
