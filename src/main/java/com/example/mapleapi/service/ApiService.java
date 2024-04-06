package com.example.mapleapi.service;

import com.example.mapleapi.domain.ApiData;
import com.example.mapleapi.domain.CharacterBasicDto;
import com.example.mapleapi.domain.CharacterItemEquipment;
import com.example.mapleapi.domain.OcidDto;
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

//    public void getApiByHttpURLConnection() {
//        try {
//            String characterName = URLEncoder.encode("", StandardCharsets.UTF_8);
//
//            String urlString = "https://open.api.nexon.com/maplestory/v1/id?character_name=" + characterName;
//            URL url = new URL(urlString);
//
//            // HTTP connection 설정
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//            connection.setRequestProperty("x-nxopen-api-key", ApiData.API_KEY);
//
//            int responseCode = connection.getResponseCode();
//
//            BufferedReader in;
//            if (responseCode == 200) {
//                // responseCode 200 정상응답
//                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            } else {
//                // responseCode 200 이외의 코드가 반환되었을 경우
//                in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
//            }
//
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            in.close();
//
//            log.info(response.toString());
//        } catch (Exception e) {
//            log.info(e.getMessage());
//        }
//    }

//    public void getApiByWebClient(String characterName) {
//
//        String urlString = "https://open.api.nexon.com";
//        String uri = "/maplestory/v1/id";
//
//        WebClient webClient = WebClient.builder()
//                .baseUrl("https://open.api.nexon.com")
//                .defaultHeader("x-nxopen-api-key", ApiData.API_KEY)
//                .build();
//
//
//        OcidDto result = webClient.get()
//                .uri(uriBuilder -> uriBuilder.path("/maplestory/v1/id")
//                        .queryParam("character_name", characterName)
//                        .build())
//                .retrieve()
//                .bodyToMono(OcidDto.class)
//                .onErrorResume(WebClientResponseException.BadRequest.class, ex -> {
//                    log.info("400BadRequest");
//                    return Mono.empty();
//                })
//                .block();
//
//        log.info("result={}", result.getOcid());
//    }

//    public void getApiByRestTemplate(String characterName) {//
//        String urlString = "https://open.api.nexon.com";
//        String uriPath = "/maplestory/v1/id";
//
//        //헤더 생성
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("x-nxopen-api-key", ApiData.API_KEY);
//        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
//
//        //uri 생성
//        URI uri = UriComponentsBuilder
//                .fromUriString("https://open.api.nexon.com")
//                .path("/maplestory/v1/id")
//                .queryParam("character_name", characterName)
//                .encode()
//                .build()
//                .toUri();
//
//        try {
//            RestTemplate restTemplate = new RestTemplate();
//            ResponseEntity<OcidDto> response = restTemplate.exchange(uri, HttpMethod.GET, entity, OcidDto.class);
//            log.info("ocid={}", response.getBody().getOcid());
//        } catch (HttpStatusCodeException e) {
//            log.info("HttpStatusCodeException statusCode={}", e.getStatusCode().value());
//        }
//    }

    public void getCharacterData(String characterName) {

        LocalDateTime nowTime = LocalDateTime.now();
        LocalDateTime yesterdayTime = nowTime.minusDays(1);

        String ocid = getOcid(characterName);

        try {
//            CharacterBasicDto characterBasic = getCharacterBasic(ocid, yesterdayTime);
//            log.info("characterBasic={}", objectMapper.writeValueAsString(characterBasic));
            CharacterItemEquipment characterItemEquipment = getCharacterItemEquipment(ocid, yesterdayTime);
            log.info("characterItemEquipment={}", objectMapper.writeValueAsString(characterItemEquipment));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public String getOcid(String charcaterName) {

        String urlString = "https://open.api.nexon.com";
        String uriPath = "/maplestory/v1/id";

        //헤더 생성
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("x-nxopen-api-key", ApiData.API_KEY);
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        //uri 생성
        URI uri = UriComponentsBuilder
                .fromUriString("https://open.api.nexon.com")
                .path("/maplestory/v1/id")
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

    public CharacterBasicDto getCharacterBasic(String ocid, LocalDateTime dateTime) {

        String urlString = "https://open.api.nexon.com";
        String uriPath = "/maplestory/v1/character/basic";
        String stringTime = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        //헤더 생성
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("x-nxopen-api-key", ApiData.API_KEY);
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        //uri 생성
        URI uri = UriComponentsBuilder
                .fromUriString("https://open.api.nexon.com")
                .path("/maplestory/v1/character/basic")
                .queryParam("ocid", ocid)
                .queryParam("date", stringTime)
                .encode()
                .build()
                .toUri();

        try {
            ResponseEntity<CharacterBasicDto> response = restTemplate.exchange(uri, HttpMethod.GET, entity, CharacterBasicDto.class);
            return response.getBody();
        } catch (HttpStatusCodeException e) {
            log.info("HttpStatusCodeException statusCode={}", e.getStatusCode().value());
            return null;
        }
    }

    public CharacterItemEquipment getCharacterItemEquipment(String ocid, LocalDateTime dateTime) {

        String urlString = "https://open.api.nexon.com";
        String uriPath = "/maplestory/v1/character/item-equipment";
        String stringTime = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        //헤더 생성
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("x-nxopen-api-key", ApiData.API_KEY);
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        //uri 생성
        URI uri = UriComponentsBuilder
                .fromUriString("https://open.api.nexon.com")
                .path("/maplestory/v1/character/item-equipment")
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
