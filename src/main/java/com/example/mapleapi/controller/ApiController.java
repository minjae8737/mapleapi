package com.example.mapleapi.controller;

import com.example.mapleapi.service.ApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class ApiController {

    private final ApiService apiService;

    @GetMapping("")
    public String getCharacterocid() {

        log.info("Start getCharacterocid");
        apiService.getCharacterData("르샤마지끄");

        return "home";
    }
}
