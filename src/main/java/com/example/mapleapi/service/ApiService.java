package com.example.mapleapi.service;

import com.example.mapleapi.domain.*;
import com.example.mapleapi.domain.Stat.Stat;
import com.example.mapleapi.repository.ApiRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class ApiService {

    private final ApiRepository apiRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;


    // 캐릭터 날짜별 경험치 데이터를 가져오기 위한 임시 로직
    public void getExpssss(String characterName) {

        LocalDateTime nowTime = LocalDateTime.now();
        String ocid = getOcid(characterName);

        for (int i = 2; i <= 10; i++) {
            LocalDateTime yesterdayTime = nowTime.minusDays(i);

            CharacterBasic characterBasic = getCharacterBasic(ocid, yesterdayTime);
            UserCharacter userCharacter = saveUserCharacter(ocid, characterBasic);
            UserCharacter findUserCharacter = apiRepository.findByCharacterName(userCharacter).get();
            saveExp(characterBasic, findUserCharacter, LocalDate.now().minusDays(i));
        }
    }

    public CharacterDataDto getCharacterData(String characterName) {

        LocalDateTime nowTime = LocalDateTime.now();
        LocalDateTime yesterdayTime = nowTime.minusDays(1);

        String ocid = getOcid(characterName);

        try {
            //Json 데이터 받기
            CharacterBasic characterBasic = getCharacterBasic(ocid, yesterdayTime);
            log.info("characterBasic={}", objectMapper.writeValueAsString(characterBasic));
            CharacterItemEquipment characterItemEquipment = getCharacterItemEquipment(ocid, yesterdayTime);
//            log.info("characterItemEquipment={}", objectMapper.writeValueAsString(characterItemEquipment));
            Stat characterStat = getCharacterStat(ocid, yesterdayTime);
//            log.info("characterStat={}", objectMapper.writeValueAsString(characterStat));
            CharacterPopularity characterPopularity = getCharacterPopularity(ocid, yesterdayTime);

            // 캐릭터 날짜별 경험치 기록 리스트 가져오기
            List<Exp> characterExpList = getCharacterExpList(ocid, characterBasic);
            log.info("characterExpList ={}", characterExpList == null);

            CharacterDataDto characterDataDto = new CharacterDataDto();
            characterDataDto.setCharacterBasic(characterBasic);
            characterDataDto.setCharacterItemEquipment(characterItemEquipment);
            characterDataDto.setCharacterStat(characterStat);
            characterDataDto.setCharacterPopularity(characterPopularity);
            characterDataDto.setCharacterExpList(characterExpList);

            return characterDataDto;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Exp> getCharacterExpList(String ocid, CharacterBasic characterBasic) {
        UserCharacter userCharacter = saveUserCharacter(ocid, characterBasic);

        //저장된 유저 찾아오기
        UserCharacter findUserCharacter = apiRepository.findByCharacterName(userCharacter).get();

        //오늘 경험치 저장하기(나중에 오늘갱신 되었는지 확인 후 업데이트 하는 로직 추가)
        saveExp(characterBasic, findUserCharacter, LocalDate.now());

        List<Exp> expList = apiRepository.findExpsByCharacterName(findUserCharacter);
        Collections.reverse(expList);

        return expList;
    }

    private void saveExp(CharacterBasic characterBasic, UserCharacter findUserCharacter,LocalDate date) {
        Exp exp = new Exp();
        exp.setCharacterExp(characterBasic.getCharacter_exp_rate());
        exp.setDate(date);
        exp.setCharacterId(findUserCharacter.getId());

        if (apiRepository.findExpByCharacterNameAtDate(findUserCharacter, exp.getDate()).isEmpty()) {
            apiRepository.expSave(exp);
        } else {
            Exp findExp = apiRepository.findExpByCharacterNameAtDate(findUserCharacter, date).get();
            apiRepository.updateExp(findExp);
        }
    }

    private UserCharacter saveUserCharacter(String ocid, CharacterBasic characterBasic) {
        UserCharacter userCharacter = new UserCharacter();
        userCharacter.setOcid(ocid);
        userCharacter.setCharacterName(characterBasic.getCharacter_name());

        if (apiRepository.findByCharacterName(userCharacter).isEmpty()) {
            apiRepository.characterSave(userCharacter);
        }

        return userCharacter;
    }


    public String getOcid(String charcaterName) {

        String uriPath = "/maplestory/v1/id";

        //헤더 생성
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(ApiData.API_KEY_HEADER, ApiData.API_KEY);
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        //uri 생성
        URI uri = UriComponentsBuilder
                .fromUriString(ApiData.BASE_URL)
                .path(uriPath)
                .queryParam("character_name", charcaterName)
                .encode()
                .build()
                .toUri();

        try {
            ResponseEntity<OcidDto> response = restTemplate.exchange(uri, HttpMethod.GET, entity, OcidDto.class);
            log.info("ocid={}", response.getBody().getOcid());
            return response.getBody().getOcid();
        } catch (HttpStatusCodeException e) {
            log.info("HttpStatusCodeException statusCode={}", e.getStatusCode().value());
            return null;
        }

    }

    public CharacterBasic getCharacterBasic(String ocid, LocalDateTime dateTime) {

        String uriPath = "/maplestory/v1/character/basic";
        String stringTime = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        //헤더 생성
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(ApiData.API_KEY_HEADER, ApiData.API_KEY);
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        //uri 생성
        URI uri = UriComponentsBuilder
                .fromUriString(ApiData.BASE_URL)
                .path(uriPath)
                .queryParam("ocid", ocid)
                .queryParam("date", stringTime)
                .encode()
                .build()
                .toUri();

        try {
            ResponseEntity<CharacterBasic> response = restTemplate.exchange(uri, HttpMethod.GET, entity, CharacterBasic.class);
            return response.getBody();
        } catch (HttpStatusCodeException e) {
            log.info("HttpStatusCodeException statusCode={}", e.getStatusCode().value());
            return null;
        }
    }

    public Stat getCharacterStat(String ocid, LocalDateTime dateTime) {
        String uriPath = "/maplestory/v1/character/stat";
        String stringTime = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        //헤더 생성
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(ApiData.API_KEY_HEADER, ApiData.API_KEY);
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        //uri 생성
        URI uri = UriComponentsBuilder
                .fromUriString(ApiData.BASE_URL)
                .path(uriPath)
                .queryParam("ocid", ocid)
                .queryParam("date", stringTime)
                .encode()
                .build()
                .toUri();

        try {
            ResponseEntity<Stat> response = restTemplate.exchange(uri, HttpMethod.GET, entity, Stat.class);
            return response.getBody();
        } catch (HttpStatusCodeException e) {
            log.info("HttpStatusCodeException statusCode={}", e.getStatusCode().value());
            return null;
        }
    }

    public CharacterItemEquipment getCharacterItemEquipment(String ocid, LocalDateTime dateTime) {

        String uriPath = "/maplestory/v1/character/item-equipment";
        String stringTime = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        //헤더 생성
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(ApiData.API_KEY_HEADER, ApiData.API_KEY);
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        //uri 생성
        URI uri = UriComponentsBuilder
                .fromUriString(ApiData.BASE_URL)
                .path(uriPath)
                .queryParam("ocid", ocid)
                .queryParam("date", stringTime)
                .encode()
                .build()
                .toUri();

        try {
            ResponseEntity<CharacterItemEquipment> response = restTemplate.exchange(uri, HttpMethod.GET, entity, CharacterItemEquipment.class);
            return response.getBody();
        } catch (HttpStatusCodeException e) {
            log.info("HttpStatusCodeException statusCode={}", e.getStatusCode().value());
            return null;
        }
    }

    public CharacterPopularity getCharacterPopularity(String ocid, LocalDateTime dateTime) {

        String uriPath = "/maplestory/v1/character/popularity";
        String stringTime = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        //헤더 생성
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(ApiData.API_KEY_HEADER, ApiData.API_KEY);
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        //uri 생성
        URI uri = UriComponentsBuilder
                .fromUriString(ApiData.BASE_URL)
                .path(uriPath)
                .queryParam("ocid", ocid)
                .queryParam("date", stringTime)
                .encode()
                .build()
                .toUri();

        try {
            ResponseEntity<CharacterPopularity> response = restTemplate.exchange(uri, HttpMethod.GET, entity, CharacterPopularity.class);
            return response.getBody();
        } catch (HttpStatusCodeException e) {
            log.info("HttpStatusCodeException statusCode={}", e.getStatusCode().value());
            return null;
        }
    }
}
