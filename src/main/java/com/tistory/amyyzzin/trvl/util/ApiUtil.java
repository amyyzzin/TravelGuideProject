package com.tistory.amyyzzin.trvl.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tistory.amyyzzin.trvl.dto.RegulationResponseDto;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

@Component
public class ApiUtil {

	@Value("${open.api.regulation.key}")
	String regulationKey;

	public RegulationResponseDto callRegulationApi() throws IOException {
		StringBuilder urlBuilder = new StringBuilder(
			"http://apis.data.go.kr/1262000/EntranceVisaService2/getEntranceVisaList2"); /*URL*/
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + regulationKey); /*Service Key*/
		urlBuilder.append(
			"&" + URLEncoder.encode("returnType", "UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*XML 또는 JSON*/
		urlBuilder.append(
			"&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
		urlBuilder.append(
			"&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/

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
			JsonObject jsonObject = JsonParser.parseString(responseString).getAsJsonObject();

			//Gson 라이브러리를 통해 Json 형태로 변환한 API 결과를 PagingWifiInfo 에 저장
			Gson gson = new Gson();
			regulationResponseDto = gson.fromJson(jsonObject, RegulationResponseDto.class);
		}

		return regulationResponseDto;
	}



}