package com.example.mapleapi.service;

import com.example.mapleapi.domain.*;
import com.example.mapleapi.domain.Stat.Stat;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Slf4j
@Service
@RequiredArgsConstructor
public class ApiService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public CharacterDataDto getCharacterData(String characterName) {

        LocalDateTime nowTime = LocalDateTime.now();
        LocalDateTime yesterdayTime = nowTime.minusDays(1);

        String ocid = getOcid(characterName);

        try {
            CharacterBasic characterBasic = getCharacterBasic(ocid, yesterdayTime);
//            log.info("characterBasic={}", objectMapper.writeValueAsString(characterBasic));
            CharacterItemEquipment characterItemEquipment = getCharacterItemEquipment(ocid, yesterdayTime);
            log.info("characterItemEquipment={}", objectMapper.writeValueAsString(characterItemEquipment));
            Stat characterStat = getCharacterStat(ocid, yesterdayTime);
//            log.info("characterStat={}", objectMapper.writeValueAsString(characterStat));

            CharacterDataDto characterDataDto = new CharacterDataDto();
            characterDataDto.setCharacterBasic(characterBasic);
            characterDataDto.setCharacterItemEquipment(characterItemEquipment);
            characterDataDto.setCharacterStat(characterStat);

            return characterDataDto;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

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
}
