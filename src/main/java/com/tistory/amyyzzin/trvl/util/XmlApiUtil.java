package com.tistory.amyyzzin.trvl.util;

import com.tistory.amyyzzin.trvl.dto.AccidentListDto;
import com.tistory.amyyzzin.trvl.dto.AccidentListResponseDto;
import com.tistory.amyyzzin.trvl.dto.CountryBasicInfoDto;
import com.tistory.amyyzzin.trvl.dto.CountryBasicInfoResponseDto;
import com.tistory.amyyzzin.trvl.dto.TravelWarningDto;
import com.tistory.amyyzzin.trvl.dto.TravelWarningResponseDto;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
@RequiredArgsConstructor
public class XmlApiUtil {

    private final RestTemplate restTemplate;

    @Value("${open.api.key}")
    String openApiKey;

    @Value("${open.api.url}")
    String openApiUrl;

    @Value("${open.api.accidentList}")
    String accidentListPath;

    @Value("${open.api.countryBasicInfo}")
    String countryBasicInfoPath;

    @Value("${open.api.travelWarning}")
    String TravelWarningPath;

    public CountryBasicInfoResponseDto callCountryBasicInfoApi() throws IOException {
        URI requestUrl = UriComponentsBuilder.fromHttpUrl(openApiUrl)
            .path(countryBasicInfoPath)
            .queryParams(XmlApiUtil.createQueryParams())
            .build(true)
            .toUri();
        ResponseEntity<Map> responseEntity = restTemplate.exchange(
            new RequestEntity(HttpMethod.GET, requestUrl), Map.class
        );

        if (!responseEntity.getStatusCode().is2xxSuccessful()
            || responseEntity.getBody() == null) {
            log.error(
                "Failed to get country basic infos. statusCode:" + responseEntity.getStatusCode());

            return new CountryBasicInfoResponseDto();
        }

        Map resultMap = responseEntity.getBody();
        Map<String, Object> responseMap = (Map<String, Object>) resultMap.get("response");
        Map<String, Object> headerMap = (Map<String, Object>) responseMap.get("header");
        Map<String, Object> bodyMap = (Map<String, Object>) responseMap.get("body");
        Map<String, Object> itemsMap = (Map<String, Object>) bodyMap.get("items");
        List<Map<String, Object>> itemList = (List<Map<String, Object>>) itemsMap.get("item");
        List<CountryBasicInfoDto> countryBasicInfoDtos = new ArrayList<>();

        for (Map<String, Object> item : itemList) {
            CountryBasicInfoDto countryBasicInfoDto = CountryBasicInfoDto.builder()
                .id(String.valueOf(item.get("id")))
                .basic((String) item.get("basic"))
                .countryNm((String) item.get("countryName"))
                .countryEngNm((String) item.get("countryEnName"))
                .continent((String) item.get("continent"))
                .imgUrl((String) item.get("imgUrl"))
                .build();

            countryBasicInfoDtos.add(countryBasicInfoDto);
        }

        return CountryBasicInfoResponseDto.builder()
            .resultCode((String) headerMap.get("resultCode"))
            .resultMsg((String) headerMap.get("resultMsg"))
            .totalCount(String.valueOf(bodyMap.get("totalCount")))
            .data(countryBasicInfoDtos)
            .build();
    }

    public AccidentListResponseDto callAccidentListApi() throws IOException {
        URI requestUrl = UriComponentsBuilder.fromHttpUrl(openApiUrl)
            .path(accidentListPath)
            .queryParams(XmlApiUtil.createQueryParams())
            .build(true)
            .toUri();
        ResponseEntity<Map> responseEntity = restTemplate.exchange(
            new RequestEntity(HttpMethod.GET, requestUrl), Map.class
        );

        if (!responseEntity.getStatusCode()
            .is2xxSuccessful() || responseEntity.getBody() == null) {
            log.error("Failed to get accident list. statusCode:" + responseEntity.getStatusCode());

            return new AccidentListResponseDto();
        }

        Map resultMap = responseEntity.getBody();
        Map<String, Object> responseMap = (Map<String, Object>) resultMap.get("response");
        Map<String, Object> headerMap = (Map<String, Object>) responseMap.get("header");
        Map<String, Object> bodyMap = (Map<String, Object>) responseMap.get("body");
        Map<String, Object> itemsMap = (Map<String, Object>) bodyMap.get("items");
        List<Map<String, Object>> itemList = (List<Map<String, Object>>) itemsMap.get("item");
        List<AccidentListDto> accidentListDtos = new ArrayList<>();

        for (Map<String, Object> item : itemList) {
            AccidentListDto accidentListDto = AccidentListDto.builder()
                .id(String.valueOf(item.get("id")))
                .continent((String) item.get("continent"))
                .ename((String) item.get("ename"))
                .name((String) item.get("name"))
                .news((String) item.get("news"))
                .build();

            accidentListDtos.add(accidentListDto);
        }

        return AccidentListResponseDto.builder()
            .resultCode((String) headerMap.get("resultCode"))
            .resultMsg((String) headerMap.get("resultMsg"))
            .totalCount(String.valueOf(bodyMap.get("totalCount")))
            .data(accidentListDtos)
            .build();
    }

    public TravelWarningResponseDto callTravelWarningApi() throws IOException {
        URI requestUrl = UriComponentsBuilder.fromHttpUrl(openApiUrl)
            .path(TravelWarningPath)
            .queryParams(XmlApiUtil.createQueryParams())
            .build(true)
            .toUri();
        ResponseEntity<Map> responseEntity = restTemplate.exchange(
            new RequestEntity(HttpMethod.GET, requestUrl), Map.class
        );

        if (!responseEntity.getStatusCode()
            .is2xxSuccessful() || responseEntity.getBody() == null) {
            log.error("Failed to get accident list. statusCode:" + responseEntity.getStatusCode());

            return new TravelWarningResponseDto();
        }

        Map resultMap = responseEntity.getBody();
        Map<String, Object> responseMap = (Map<String, Object>) resultMap.get("response");
        Map<String, Object> headerMap = (Map<String, Object>) responseMap.get("header");
        Map<String, Object> bodyMap = (Map<String, Object>) responseMap.get("body");
        Map<String, Object> itemsMap = (Map<String, Object>) bodyMap.get("items");
        List<Map<String, Object>> itemList = (List<Map<String, Object>>) itemsMap.get("item");
        List<TravelWarningDto> travelWarningDtos = new ArrayList<>();

        for (Map<String, Object> item : itemList) {
            TravelWarningDto travelWarningDto = TravelWarningDto.builder()
                .id(String.valueOf(item.get("id")))
                .continent((String) item.get("continent"))
                .control((String) item.get("control"))
                .controlNote((String) item.get("controlNote"))
                .countryEnName((String) item.get("countryEnName"))
                .countryName((String) item.get("countryName"))
                .imgUrl2((String) item.get("imgUrl2"))
                .build();

            travelWarningDtos.add(travelWarningDto);
        }

        return TravelWarningResponseDto.builder()
            .resultCode((String) headerMap.get("resultCode"))
            .resultMsg((String) headerMap.get("resultMsg"))
            .totalCount(String.valueOf(bodyMap.get("totalCount")))
            .data(travelWarningDtos)
            .build();
    }

    static MultiValueMap<String, String> createQueryParams() {
        return createQueryParams(1, 1000);
    }

    static MultiValueMap<String, String> createQueryParams(int page, int size) {
        MultiValueMap<String, String> queryParameterMap = new LinkedMultiValueMap<>();
        queryParameterMap.set("_type", "json");
        queryParameterMap.set("pageNo", String.valueOf(page));
        queryParameterMap.set("numOfRows", String.valueOf(size));
        return queryParameterMap;
    }


}
