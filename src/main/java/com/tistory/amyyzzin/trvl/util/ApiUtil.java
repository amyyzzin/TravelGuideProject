package com.tistory.amyyzzin.trvl.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tistory.amyyzzin.trvl.dto.AccidentListDto;
import com.tistory.amyyzzin.trvl.dto.AccidentListResponseDto;
import com.tistory.amyyzzin.trvl.dto.ContactPointResponseDto;
import com.tistory.amyyzzin.trvl.dto.CountryBasicInfoDto;
import com.tistory.amyyzzin.trvl.dto.CountryBasicInfoResponseDto;
import com.tistory.amyyzzin.trvl.dto.CountryFlagResponseDto;
import com.tistory.amyyzzin.trvl.dto.CountryInfoResponseDto;
import com.tistory.amyyzzin.trvl.dto.CovidSafetyResponseDto;
import com.tistory.amyyzzin.trvl.dto.EmbassyHomepageResponseDto;
import com.tistory.amyyzzin.trvl.dto.EmbassyInfoResponseDto;
import com.tistory.amyyzzin.trvl.dto.NoticeListResponseDto;
import com.tistory.amyyzzin.trvl.dto.OverseasArrivalResponseDto;
import com.tistory.amyyzzin.trvl.dto.RegulationResponseDto;
import com.tistory.amyyzzin.trvl.dto.SafetyListResponseDto;
import com.tistory.amyyzzin.trvl.dto.StandardCodeResponseDto;
import com.tistory.amyyzzin.trvl.dto.TravelAlarmResponseDto;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
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
public class ApiUtil {

    private final RestTemplate restTemplate;

    @Value("${open.api.key}")
    String openApiKey;

    @Value("${open.api.url}")
    String openApiUrl;

    @Value("${open.api.accidentList}")
    String accidentListPath;

    @Value("${open.api.countryBasicInfo}")
    String countryBasicInfoPath;

    //국가·지역별 입국허가요건
    public RegulationResponseDto callRegulationApi() throws IOException {
        StringBuilder urlBuilder = new StringBuilder(
            "http://apis.data.go.kr/1262000/EntranceVisaService2/getEntranceVisaList2"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "="
            + openApiKey); /*Service Key*/
        urlBuilder.append(
            "&" + URLEncoder.encode("returnType", "UTF-8") + "="
                + URLEncoder.encode("JSON", "UTF-8")); /*XML 또는 JSON*/
        urlBuilder.append(
            "&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
                + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append(
            "&" + URLEncoder.encode("pageNo", "UTF-8") + "="
                + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        OkHttpClient client = builder.build();

        Request.Builder requestBuilder = new Request.Builder()
            .url(urlBuilder.toString())
            .get();
        requestBuilder.addHeader("Content-type", "application/json");

        Request request = requestBuilder.build();
        Response response = client.newCall(request).execute();
        RegulationResponseDto regulationResponseDto = null;

        // API호출이 성공적으로 이루어 졌다면
        if (response.isSuccessful()) {
            ResponseBody body = response.body();

            //문자열 형태로 결과를 저장
            String responseString = body.string();

            //결과를 Json 형태로 변환
            JsonObject jsonObject = JsonParser.parseString(responseString)
                .getAsJsonObject();

            //Gson 라이브러리를 통해 Json 형태로 변환한 API 결과를 PagingWifiInfo 에 저장
            Gson gson = new Gson();
            regulationResponseDto = gson.fromJson(jsonObject,
                RegulationResponseDto.class);
        }

        return regulationResponseDto;
    }

    //국가·지역별 표준코드
    public StandardCodeResponseDto callStandardCodeApi() throws IOException {
        StringBuilder urlBuilder = new StringBuilder(
            "http://apis.data.go.kr/1262000/CountryCodeService2/getCountryCodeList2"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "="
            + openApiKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("returnType", "UTF-8") + "="
            + URLEncoder.encode("JSON", "UTF-8")); /*XML 또는 JSON*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
            + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "="
            + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder()
            .url(urlBuilder.toString())
            .get();
        builder.addHeader("Content-type", "application/json");

        Request request = builder.build();
        Response response = client.newCall(request).execute();
        StandardCodeResponseDto standardCodeResponseDto = null;

        // API호출이 성공적으로 이루어 졌다면
        if (response.isSuccessful()) {
            ResponseBody body = response.body();

            //문자열 형태로 결과를 저장
            String responseString = body.string();

            //결과를 Json 형태로 변환
            JsonObject jsonObject = JsonParser.parseString(responseString)
                .getAsJsonObject();

            //Gson 라이브러리를 통해 Json 형태로 변환한 API 결과를 PagingWifiInfo 에 저장
            Gson gson = new Gson();
            standardCodeResponseDto = gson.fromJson(jsonObject,
                StandardCodeResponseDto.class);
        }
        return standardCodeResponseDto;
    }

    // 국가∙지역별 국기 이미지
    public CountryFlagResponseDto callCountryFlagApi() throws
        IOException {
        StringBuilder urlBuilder = new StringBuilder(
            "http://apis.data.go.kr/1262000/CountryFlagService2/getCountryFlagList2"); /*URL*/

        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
            + "=" + openApiKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("returnType", "UTF-8") + "="
            + URLEncoder.encode("JSON", "UTF-8")); /*XML 또는 JSON*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
            + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "="
            + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder()
            .url(urlBuilder.toString())
            .get();
        builder.addHeader("Content-type", "application/json");

        Request request = builder.build();
        Response response = client.newCall(request).execute();
        CountryFlagResponseDto countryFlagResponseDto = null;

        // API호출이 성공적으로 이루어 졌다면
        if (response.isSuccessful()) {
            ResponseBody body = response.body();

            //문자열 형태로 결과를 저장
            String responseString = body.string();
            System.out.println(responseString);

            //결과를 Json 형태로 변환
            JsonObject jsonObject = JsonParser.parseString(responseString)
                .getAsJsonObject();

            //Gson 라이브러리를 통해 Json 형태로 변환한 API 결과를 PagingWifiInfo 에 저장
            Gson gson = new Gson();
            countryFlagResponseDto = gson.fromJson(jsonObject, CountryFlagResponseDto.class);
        }

        return countryFlagResponseDto;
    }

    //국가∙지역별 일반정보
    public CountryInfoResponseDto callCountryInfoApi() throws
        IOException {
        StringBuilder urlBuilder = new StringBuilder(
            "http://apis.data.go.kr/1262000/CountryGnrlInfoService2/getCountryGnrlInfoList2"); /*URL*/

        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
            + "=" + openApiKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("returnType", "UTF-8") + "="
            + URLEncoder.encode("JSON", "UTF-8")); /*XML 또는 JSON*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
            + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "="
            + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder()
            .url(urlBuilder.toString())
            .get();
        builder.addHeader("Content-type", "application/json");

        Request request = builder.build();
        Response response = client.newCall(request).execute();
        CountryInfoResponseDto countryInfoResponseDto = null;

        // API호출이 성공적으로 이루어 졌다면
        if (response.isSuccessful()) {
            ResponseBody body = response.body();

            //문자열 형태로 결과를 저장
            String responseString = body.string();
            System.out.println(responseString);

            //결과를 Json 형태로 변환
            JsonObject jsonObject = JsonParser.parseString(responseString)
                .getAsJsonObject();

            //Gson 라이브러리를 통해 Json 형태로 변환한 API 결과를 PagingWifiInfo 에 저장
            Gson gson = new Gson();
            countryInfoResponseDto = gson.fromJson(jsonObject, CountryInfoResponseDto.class);
        }
        return countryInfoResponseDto;
    }

    public CountryBasicInfoResponseDto callCountryBasicInfoApi() throws
        IOException {
        URI requestUrl = UriComponentsBuilder.fromHttpUrl("http://apis.data.go.kr")
            .path(countryBasicInfoPath)
            .queryParams(ApiUtil.createQueryParams())
            .build(true)
            .toUri();
        ResponseEntity<Map> responseEntity = restTemplate.exchange(
            new RequestEntity(HttpMethod.GET, requestUrl), Map.class
        );

        if (!responseEntity.getStatusCode().is2xxSuccessful()
            || responseEntity.getBody() == null) {
            log.error("Failed to get country basic infos. statusCode:" + responseEntity.getStatusCode());

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

    //외교부 공지사항
    public NoticeListResponseDto callNoticeListApi() throws
        IOException {
        StringBuilder urlBuilder = new StringBuilder(
            "http://apis.data.go.kr/1262000/NoticeService2/getNoticeList2"); /*URL*/

        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
            + "=" + openApiKey); /*Service Key*/
        urlBuilder.append(
            "&" + URLEncoder.encode("returnType", "UTF-8") + "=" + URLEncoder.encode("JSON",
                "UTF-8")); /*XML 또는 JSON*/
        urlBuilder.append(
            "&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10",
                "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1",
            "UTF-8")); /*페이지 번호*/

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder()
            .url(urlBuilder.toString())
            .get();
        builder.addHeader("Content-type", "application/json");

        Request request = builder.build();
        Response response = client.newCall(request).execute();
        NoticeListResponseDto noticeListResponseDto = null;

        // API호출이 성공적으로 이루어 졌다면
        if (response.isSuccessful()) {
            ResponseBody body = response.body();

            //문자열 형태로 결과를 저장
            String responseString = body.string();
            System.out.println(responseString);

            //결과를 Json 형태로 변환
            JsonObject jsonObject = JsonParser.parseString(responseString)
                .getAsJsonObject();

            //Gson 라이브러리를 통해 Json 형태로 변환한 API 결과를 PagingWifiInfo 에 저장
            Gson gson = new Gson();
            noticeListResponseDto = gson.fromJson(jsonObject, NoticeListResponseDto.class);
        }

        return noticeListResponseDto;
    }


    //안전사항
    public SafetyListResponseDto callSafetyListApi() throws
        IOException {
        StringBuilder urlBuilder = new StringBuilder(
            "http://apis.data.go.kr/1262000/CountrySafetyService3/getCountrySafetyList3"); /*URL*/
        urlBuilder.append(
            "?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + openApiKey); /*Service Key*/
        urlBuilder.append(
            "&" + URLEncoder.encode("returnType", "UTF-8") + "=" + URLEncoder.encode("JSON",
                "UTF-8")); /*XML 또는 JSON*/
        urlBuilder.append(
            "&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("100",
                "UTF-8")); /*한 페이지 결과 수*/
//        urlBuilder.append("&" + URLEncoder.encode("cond[country_nm::EQ]","UTF-8") + "=" + URLEncoder.encode("카자흐스탄", "UTF-8")); /*한글 국가명*/
//        urlBuilder.append("&" + URLEncoder.encode("cond[country_iso_alp2::EQ]","UTF-8") + "=" + URLEncoder.encode("KZ", "UTF-8")); /*ISO 2자리코드*/
        urlBuilder.append(
            "&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1",
                "UTF-8")); /*페이지 번호*/

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        OkHttpClient client = builder.build();
        Request.Builder requestBuilder = new Request.Builder()
            .url(urlBuilder.toString())
            .get();
        requestBuilder.addHeader("Content-type", "application/json");

        Request request = requestBuilder.build();
        Response response = client.newCall(request).execute();
        SafetyListResponseDto safetyListResponseDto = null;

        // API호출이 성공적으로 이루어 졌다면
        if (response.isSuccessful()) {
            ResponseBody body = response.body();

            //문자열 형태로 결과를 저장
            String responseString = body.string();
            System.out.println(responseString);

            //결과를 Json 형태로 변환
            JsonObject jsonObject = JsonParser.parseString(responseString)
                .getAsJsonObject();

            //Gson 라이브러리를 통해 Json 형태로 변환한 API 결과를 PagingWifiInfo 에 저장
            Gson gson = new Gson();
            safetyListResponseDto = gson.fromJson(jsonObject, SafetyListResponseDto.class);
        }

        return safetyListResponseDto;
    }

    public CovidSafetyResponseDto callCovidSafetyApi() throws
        IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1262000/CountryCovid19SafetyServiceNew/getCountrySafetyNewsListNew"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + openApiKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*XML 또는 JSON*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
//        urlBuilder.append("&" + URLEncoder.encode("cond[country_nm::EQ]","UTF-8") + "=" + URLEncoder.encode("가나", "UTF-8")); /*한글 국가명*/
//        urlBuilder.append("&" + URLEncoder.encode("cond[country_iso_alp2::EQ]","UTF-8") + "=" + URLEncoder.encode("GH", "UTF-8")); /*ISO 2자리코드*/

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder()
            .url(urlBuilder.toString())
            .get();
        builder.addHeader("Content-type", "application/json");

        Request request = builder.build();
        Response response = client.newCall(request).execute();
        CovidSafetyResponseDto covidSafetyResponseDto = null;

        // API호출이 성공적으로 이루어 졌다면
        if (response.isSuccessful()) {
            ResponseBody body = response.body();

            //문자열 형태로 결과를 저장
            String responseString = body.string();
            System.out.println(responseString);

            //결과를 Json 형태로 변환
            JsonObject jsonObject = JsonParser.parseString(responseString)
                .getAsJsonObject();

            //Gson 라이브러리를 통해 Json 형태로 변환한 API 결과를 PagingWifiInfo 에 저장
            Gson gson = new Gson();
            covidSafetyResponseDto = gson.fromJson(jsonObject, CovidSafetyResponseDto.class);
        }

        return covidSafetyResponseDto;
    }

    public OverseasArrivalResponseDto callOverseasArrivalApi() throws
        IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1262000/CountryOverseasArrivalsService/getCountryOverseasArrivalsList"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + openApiKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*XML 또는 JSON*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
//        urlBuilder.append("&" + URLEncoder.encode("cond[country_nm::EQ]","UTF-8") + "=" + URLEncoder.encode("네덜란드", "UTF-8")); /*한글 국가명*/
//        urlBuilder.append("&" + URLEncoder.encode("cond[country_iso_alp2::EQ]","UTF-8") + "=" + URLEncoder.encode("NL", "UTF-8")); /*ISO 2자리코드*/

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder()
            .url(urlBuilder.toString())
            .get();
        builder.addHeader("Content-type", "application/json");

        Request request = builder.build();
        Response response = client.newCall(request).execute();
        OverseasArrivalResponseDto overseasArrivalResponseDto = null;

        // API호출이 성공적으로 이루어 졌다면
        if (response.isSuccessful()) {
            ResponseBody body = response.body();

            //문자열 형태로 결과를 저장
            String responseString = body.string();
            System.out.println(responseString);

            //결과를 Json 형태로 변환
            JsonObject jsonObject = JsonParser.parseString(responseString)
                .getAsJsonObject();

            //Gson 라이브러리를 통해 Json 형태로 변환한 API 결과를 PagingWifiInfo 에 저장
            Gson gson = new Gson();
            overseasArrivalResponseDto = gson.fromJson(jsonObject, OverseasArrivalResponseDto.class);
        }

        return overseasArrivalResponseDto;
    }

    public EmbassyInfoResponseDto callEmbassyInfoApi() throws
        IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1262000/EmbassyService2/getEmbassyList2"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + openApiKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*XML 또는 JSON*/
//        urlBuilder.append("&" + URLEncoder.encode("cond[country_nm::EQ]","UTF-8") + "=" + URLEncoder.encode("가나", "UTF-8")); /*한글 국가명*/
//        urlBuilder.append("&" + URLEncoder.encode("cond[country_iso_alp2::EQ]","UTF-8") + "=" + URLEncoder.encode("GH", "UTF-8")); /*ISO 2자리코드*/

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder()
            .url(urlBuilder.toString())
            .get();
        builder.addHeader("Content-type", "application/json");

        Request request = builder.build();
        Response response = client.newCall(request).execute();
        EmbassyInfoResponseDto embassyInfoResponseDto = null;

        // API호출이 성공적으로 이루어 졌다면
        if (response.isSuccessful()) {
            ResponseBody body = response.body();

            //문자열 형태로 결과를 저장
            String responseString = body.string();
            System.out.println(responseString);

            //결과를 Json 형태로 변환
            JsonObject jsonObject = JsonParser.parseString(responseString)
                .getAsJsonObject();

            //Gson 라이브러리를 통해 Json 형태로 변환한 API 결과를 PagingWifiInfo 에 저장
            Gson gson = new Gson();
            embassyInfoResponseDto = gson.fromJson(jsonObject, EmbassyInfoResponseDto.class);
        }

        return embassyInfoResponseDto;
    }

    public EmbassyHomepageResponseDto callEmbassyHomepageApi() throws
        IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1262000/EmbassyHomepageService2/getEmbassyHomepageList2"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + openApiKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*XML 또는 JSON*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
//        urlBuilder.append("&" + URLEncoder.encode("cond[embassy_cd::EQ]","UTF-8") + "=" + URLEncoder.encode("ae", "UTF-8")); /*재외공관코드*/

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder()
            .url(urlBuilder.toString())
            .get();
        builder.addHeader("Content-type", "application/json");

        Request request = builder.build();
        Response response = client.newCall(request).execute();
        EmbassyHomepageResponseDto embassyHomepageResponseDto = null;

        // API호출이 성공적으로 이루어 졌다면
        if (response.isSuccessful()) {
            ResponseBody body = response.body();

            //문자열 형태로 결과를 저장
            String responseString = body.string();
            System.out.println(responseString);

            //결과를 Json 형태로 변환
            JsonObject jsonObject = JsonParser.parseString(responseString)
                .getAsJsonObject();

            //Gson 라이브러리를 통해 Json 형태로 변환한 API 결과를 PagingWifiInfo 에 저장
            Gson gson = new Gson();
            embassyHomepageResponseDto = gson.fromJson(jsonObject, EmbassyHomepageResponseDto.class);
        }

        return embassyHomepageResponseDto;
    }

    public ContactPointResponseDto callContactPointApi() throws
        IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1262000/LocalContactService2/getLocalContactList2"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + openApiKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*XML 또는 JSON*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
//        urlBuilder.append("&" + URLEncoder.encode("cond[country_nm::EQ]","UTF-8") + "=" + URLEncoder.encode("가나", "UTF-8")); /*한글 국가명*/
//        urlBuilder.append("&" + URLEncoder.encode("cond[country_iso_alp2::EQ]","UTF-8") + "=" + URLEncoder.encode("GH", "UTF-8")); /*ISO 2자리코드*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder()
            .url(urlBuilder.toString())
            .get();
        builder.addHeader("Content-type", "application/json");

        Request request = builder.build();
        Response response = client.newCall(request).execute();
        ContactPointResponseDto contactPointResponseDto = null;

        // API호출이 성공적으로 이루어 졌다면
        if (response.isSuccessful()) {
            ResponseBody body = response.body();

            //문자열 형태로 결과를 저장
            String responseString = body.string();
            System.out.println(responseString);

            //결과를 Json 형태로 변환
            JsonObject jsonObject = JsonParser.parseString(responseString)
                .getAsJsonObject();

            //Gson 라이브러리를 통해 Json 형태로 변환한 API 결과를 PagingWifiInfo 에 저장
            Gson gson = new Gson();
            contactPointResponseDto = gson.fromJson(jsonObject, ContactPointResponseDto.class);
        }

        return contactPointResponseDto;
    }

    public TravelAlarmResponseDto callTravelAlarmApi() throws
        IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1262000/TravelAlarmService2/getTravelAlarmList2"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + openApiKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*XML 또는 JSON*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
//        urlBuilder.append("&" + URLEncoder.encode("cond[country_nm::EQ]","UTF-8") + "=" + URLEncoder.encode("가나", "UTF-8")); /*한글 국가명*/
//        urlBuilder.append("&" + URLEncoder.encode("cond[country_iso_alp2::EQ]","UTF-8") + "=" + URLEncoder.encode("GH", "UTF-8")); /*ISO 2자리코드*/
//        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder()
            .url(urlBuilder.toString())
            .get();
        builder.addHeader("Content-type", "application/json");

        Request request = builder.build();
        Response response = client.newCall(request).execute();
        TravelAlarmResponseDto travelAlarmResponseDto = null;

        // API호출이 성공적으로 이루어 졌다면
        if (response.isSuccessful()) {
            ResponseBody body = response.body();

            //문자열 형태로 결과를 저장
            String responseString = body.string();
            System.out.println(responseString);

            //결과를 Json 형태로 변환
            JsonObject jsonObject = JsonParser.parseString(responseString)
                .getAsJsonObject();

            //Gson 라이브러리를 통해 Json 형태로 변환한 API 결과를 PagingWifiInfo 에 저장
            Gson gson = new Gson();
            travelAlarmResponseDto = gson.fromJson(jsonObject, TravelAlarmResponseDto.class);
        }

        return travelAlarmResponseDto;
    }

    public AccidentListResponseDto callAccidentListApi() throws
        IOException {
        URI requestUrl = UriComponentsBuilder.fromHttpUrl(openApiUrl)
            .path(accidentListPath)
            .queryParams(ApiUtil.createQueryParams())
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
