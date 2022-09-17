package com.tistory.amyyzzin.trvl.service;

import com.tistory.amyyzzin.trvl.domain.SafetyList;
import com.tistory.amyyzzin.trvl.dto.SafetyListDto;
import com.tistory.amyyzzin.trvl.dto.SafetyListResponseDto;
import com.tistory.amyyzzin.trvl.repository.SafetyListRepository;
import com.tistory.amyyzzin.trvl.util.GenericApiUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SafetyListService {

    private final GenericApiUtil genericApiUtil;

    private final SafetyListRepository safetyListRepository;

    @Value("${open.api.safetyList}")
    String safetyListUrl;

    @PostConstruct
    public void init() throws IOException, InterruptedException {
        if (safetyListRepository.count() > 0) {
            return;
        }

        for (int i = 0; i < 3; i++) {
            try {
                insert((SafetyListResponseDto) genericApiUtil.callJsonApi(safetyListUrl,
                    SafetyListResponseDto.class, "100"));
                break;
            } catch (Exception e) {
                log.error("[SafetyListService init] ERROR {}", e.getMessage());
                Thread.sleep(2000);
            }
        }
        Thread.sleep(2000);
    }

    public void insert(SafetyListResponseDto safetyListResponseDto) {
        if (safetyListResponseDto == null) {
            return;
        }

        log.info("[SafetyListDto] {}", safetyListResponseDto);

        for (SafetyListDto safetyListDto : safetyListResponseDto.getData()) {
            try {
                SafetyList safetyList = SafetyList.of(safetyListDto);
                safetyList.setMainNotice(safetyListDto.getTitle().startsWith("<font"));

                safetyListRepository.save(safetyList);
            } catch (Exception e) {
                log.error("[SafetyList.insert] ERROR {}", e.getMessage());
            }
        }
    }

    public List<SafetyList> getSafetyList() {
        return safetyListRepository.findTop5ByOrderByWrtDtDesc();
    }


    public static void main(String[] args) throws IOException {
        StringBuilder urlBuilder = new StringBuilder(
            "http://apis.data.go.kr/1262000/CountrySafetyService3/getCountrySafetyList3"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=Nd%2BLFj1ko2GZ%2BMrPTWWa%2FNi3TWAnGAOEGthshlAmxbpIA3Fw50RJ2tUm7G9QRu17yNsyCesQeHdLpUOHOYvbGw%3D%3D"); /*Service Key*/
        urlBuilder.append(
            "&" + URLEncoder.encode("returnType", "UTF-8") + "=" + URLEncoder.encode("JSON",
                "UTF-8")); /*XML 또는 JSON*/
        urlBuilder.append(
            "&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("100",
                "UTF-8")); /*한 페이지 결과 수*/
//        urlBuilder.append(
//            "&" + URLEncoder.encode("cond[country_nm::EQ]", "UTF-8") + "=" + URLEncoder.encode("호주",
//                "UTF-8")); /*한글 국가명*/
//        urlBuilder.append("&" + URLEncoder.encode("cond[country_iso_alp2::EQ]", "UTF-8") + "="
//            + URLEncoder.encode("AU", "UTF-8")); /*ISO 2자리코드*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1",
            "UTF-8")); /*페이지 번호*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
    }

}
