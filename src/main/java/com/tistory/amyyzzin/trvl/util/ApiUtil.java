package com.tistory.amyyzzin.trvl.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tistory.amyyzzin.trvl.dto.CountryFlagResponseDto;
import com.tistory.amyyzzin.trvl.dto.CountryInfoResponseDto;
import com.tistory.amyyzzin.trvl.dto.NoticeListResponseDto;
import com.tistory.amyyzzin.trvl.dto.RegulationResponseDto;
import com.tistory.amyyzzin.trvl.dto.StandardCodeResponseDto;
import java.io.IOException;
import java.net.URLEncoder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiUtil {

    @Value("${open.api.regulation.key}")
    String regulationKey;

    @Value("${open.api.standard.code.key}")
    String standardCodeKey;

    @Value("${open.api.country.flag.key}")
    String countryFlagKey;

    @Value("${open.api.country.Info.key}")
    String countryInfoKey;


    //국가·지역별 입국허가요건
    public RegulationResponseDto callRegulationApi() throws IOException {
        StringBuilder urlBuilder = new StringBuilder(
            "http://apis.data.go.kr/1262000/EntranceVisaService2/getEntranceVisaList2"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "="
            + regulationKey); /*Service Key*/
        urlBuilder.append(
            "&" + URLEncoder.encode("returnType", "UTF-8") + "="
                + URLEncoder.encode("JSON", "UTF-8")); /*XML 또는 JSON*/
        urlBuilder.append(
            "&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
                + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append(
            "&" + URLEncoder.encode("pageNo", "UTF-8") + "="
                + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder()
            .url(urlBuilder.toString())
            .get();
        builder.addHeader("Content-type", "application/json");

        Request request = builder.build();
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
            + standardCodeKey); /*Service Key*/
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
            + "=" + countryFlagKey); /*Service Key*/
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
            + "=" + countryInfoKey); /*Service Key*/
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

//    public CountryBasicInfoResponseDto callCountryBasicInfoApi() throws
//        IOException {
//        StringBuilder urlBuilder = new StringBuilder(
//            "http://apis.data.go.kr/1262000/CountryBasicService"); /*URL*/
//
//        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
//            + "=" + countryInfoKey); /*Service Key*/
//        urlBuilder.append("&" + URLEncoder.encode("returnType", "UTF-8") + "="
//            + URLEncoder.encode("JSON", "UTF-8")); /*XML 또는 JSON*/
//        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
//            + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
//        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "="
//            + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
//
//        OkHttpClient client = new OkHttpClient();
//        Request.Builder builder = new Request.Builder()
//            .url(urlBuilder.toString())
//            .get();
//        builder.addHeader("Content-type", "application/json");
//
//        Request request = builder.build();
//        Response response = client.newCall(request).execute();
//        CountryBasicInfoResponseDto countryBasicInfoResponseDto = null;
//
//        // API호출이 성공적으로 이루어 졌다면
//        if (response.isSuccessful()) {
//            ResponseBody body = response.body();
//
//            //문자열 형태로 결과를 저장
//            String responseString = body.string();
//            System.out.println(responseString);
//
//            //결과를 Json 형태로 변환
//            JsonObject jsonObject = JsonParser.parseString(responseString)
//                .getAsJsonObject();
//
//            //Gson 라이브러리를 통해 Json 형태로 변환한 API 결과를 PagingWifiInfo 에 저장
//            Gson gson = new Gson();
//            countryBasicInfoResponseDto = gson.fromJson(jsonObject, CountryBasicInfoResponseDto.class);
//        }
//        return countryBasicInfoResponseDto;
//    }

    //외교부 공지사항
    public NoticeListResponseDto callNoticeListApi() throws
        IOException {
        StringBuilder urlBuilder = new StringBuilder(
            "http://apis.data.go.kr/1262000/NoticeService2/getNoticeList2"); /*URL*/

        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
            + "=" + countryFlagKey); /*Service Key*/
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

}
