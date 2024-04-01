package com.example.mapleapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
@RequestMapping("")
public class ApiController {

    @GetMapping("")
    public String getCharacterocid() {

        log.info("Start getCharacterocid");

        try {
            String API_KEY = "test_404db065b4630a2d73694fba66a23c1c7018d602f8ae00995ff8298d4d958085160eb8da905a5022f47271f77c0d8686";
            String characterName = URLEncoder.encode("르샤마지끄", StandardCharsets.UTF_8);

            String urlString = "https://open.api.nexon.com/maplestory/v1/id?character_name=" + characterName;
            URL url = new URL(urlString);

            // HTTP connection 설정
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("x-nxopen-api-key", API_KEY);

            int responseCode = connection.getResponseCode();

            BufferedReader in;
            if(responseCode == 200) {
                // responseCode 200 정상응답
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                // responseCode 200 이외의 코드가 반환되었을 경우
                in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            log.info(response.toString());
        } catch (Exception e) {
            log.info(e.getMessage());
        }

        return "";
    }
}
