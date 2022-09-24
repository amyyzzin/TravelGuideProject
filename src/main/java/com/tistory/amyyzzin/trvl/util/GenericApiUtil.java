package com.tistory.amyyzzin.trvl.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tistory.amyyzzin.trvl.dto.BaseResponseDto;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * generic method 참고: https://stackoverflow.com/questions/450807/how-do-i-make-the-method-return-type-generic
 *
 * @param <T>
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class GenericApiUtil<T> {

    private final RestTemplate restTemplate;

    @Value("${open.api.key}")
    String openApiKey;

    @Value("${open.api.url}")
    String openApiUrl;

    @Value("${open.api.timeout}")
    Integer timeout;

    public <T extends BaseResponseDto> T callJsonApi(String url, Class<T> type, String numOfRows) throws IOException {
        StringBuilder urlBuilder = new StringBuilder(url); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "="
            + openApiKey); /*Service Key*/
        urlBuilder.append(
            "&" + URLEncoder.encode("returnType", "UTF-8") + "="
                + URLEncoder.encode("JSON", "UTF-8")); /*XML 또는 JSON*/
        urlBuilder.append(
            "&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
                + URLEncoder.encode(numOfRows, "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append(
            "&" + URLEncoder.encode("pageNo", "UTF-8") + "="
                + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        /**
         * connection timeout 에러가 자주 발생해 최대 5분으로 늘림
         */
        builder.connectTimeout(timeout, TimeUnit.SECONDS);
        builder.readTimeout(timeout, TimeUnit.SECONDS);
        builder.writeTimeout(timeout, TimeUnit.SECONDS);
        OkHttpClient client = builder.build();

        Request.Builder requestBuilder = new Request.Builder()
            .url(urlBuilder.toString())
            .get();
        requestBuilder.addHeader("Content-type", "application/json");

        Request request = requestBuilder.build();
        Response response = client.newCall(request).execute();
        T responseDto = null;

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
            responseDto = gson.fromJson(jsonObject, type);
        }

        return (T) responseDto;
    }
}