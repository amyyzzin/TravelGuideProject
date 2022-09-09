package com.tistory.amyyzzin.trvl.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.tistory.amyyzzin.trvl.domain.AccidentList;
import com.tistory.amyyzzin.trvl.domain.CountryBasicInfo;
import com.tistory.amyyzzin.trvl.dto.ContactPointResponseDto;
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
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@ActiveProfiles("security")
@SpringBootTest
@WebAppConfiguration
public class ApiUtilTest {

    @Autowired
    ApiUtil apiUtil;

    @Autowired
    RestTemplate restTemplate;

    @Test
    public void testRegulationApi() throws IOException {
        RegulationResponseDto regulationResponseDto = apiUtil.callRegulationApi();

        System.out.println(regulationResponseDto);
        assertThat(regulationResponseDto.getData() != null
            && !regulationResponseDto.getData().isEmpty());
    }

    @Test
    public void testStandardCodeApi() throws Exception {
        StandardCodeResponseDto standardCodeDto = apiUtil.callStandardCodeApi();

        System.out.println(standardCodeDto);
        assertThat(standardCodeDto != null);

    }

    @Test
    public void testCountryFlagApi() throws Exception {
        CountryFlagResponseDto countryFlagResponseDto = apiUtil.callCountryFlagApi();

        System.out.println(countryFlagResponseDto);
        assertThat(countryFlagResponseDto != null);

    }
    @Test
    public void testCountryInfoApi() throws Exception {
        CountryInfoResponseDto countryInfoResponseDto = apiUtil.callCountryInfoApi();

        System.out.println(countryInfoResponseDto);
        assertThat(countryInfoResponseDto.getData() != null);

    }

    @Test
    public void testCountryBasicInfoApi() throws Exception {
        URI requestUrl = UriComponentsBuilder.fromHttpUrl("http://apis.data.go.kr")
            .path("/1262000/CountryBasicService/getCountryBasicList")
            .queryParams(ApiUtil.createQueryParams())
            .build(true)
            .toUri();
        ResponseEntity<Map> responseEntity = restTemplate.exchange(
            new RequestEntity(HttpMethod.GET, requestUrl), Map.class
        );

        Map resultMap = responseEntity.getBody();
        Map<String, Object> responseMap = (Map<String, Object>) resultMap.get("response");
        Map<String, Object> bodyMap = (Map<String, Object>) responseMap.get("body");
        Map<String, Object> itemsMap = (Map<String, Object>) bodyMap.get("items");
        List<Map<String, Object>> itemList = (List<Map<String, Object>>) itemsMap.get("item");

        for (Map<String, Object> item : itemList) {
            CountryBasicInfo countryBasicInfo = CountryBasicInfo.builder()
                .id(Long.valueOf((Integer) item.get("id")))
                .basic((String) item.get("basic"))
                .countryNm((String) item.get("countryName"))
                .countryEngNm((String) item.get("countryEnName"))
                .imgUrl((String) item.get("imgUrl"))
                .continent((String) item.get("continent"))
                .build();

            System.out.println(countryBasicInfo);
        }
    }

    @Test
    public void testNoticeListApi() throws Exception {
        NoticeListResponseDto noticeListResponseDto = apiUtil.callNoticeListApi();

        System.out.println(noticeListResponseDto);

        assertThat(noticeListResponseDto != null);

    }

    @Test
    public void testSafetyListApi() throws Exception {
        SafetyListResponseDto safetyListResponseDto = apiUtil.callSafetyListApi();

        System.out.println(safetyListResponseDto);

        assertThat(safetyListResponseDto != null);
    }

    @Test
    public void testCovidSafetyApi() throws Exception {
        CovidSafetyResponseDto covidSafetyResponseDto = apiUtil.callCovidSafetyApi();

        System.out.println(covidSafetyResponseDto);

        assertThat(covidSafetyResponseDto != null);
    }
    @Test
    public void testOverseasArrivalApi() throws Exception {
        OverseasArrivalResponseDto overseasArrivalResponseDto = apiUtil.callOverseasArrivalApi();

        System.out.println(overseasArrivalResponseDto);

        assertThat(overseasArrivalResponseDto != null);
    }

    @Test
    public void testEmbassyInfoApi() throws Exception {
        EmbassyInfoResponseDto embassyInfoResponseDto = apiUtil.callEmbassyInfoApi();

        System.out.println(embassyInfoResponseDto);

        assertThat(embassyInfoResponseDto != null);
    }

    @Test
    public void testEmbassyHomepageApi() throws Exception {
        EmbassyHomepageResponseDto embassyHomepageResponseDto = apiUtil.callEmbassyHomepageApi();

        System.out.println(embassyHomepageResponseDto);

        assertThat(embassyHomepageResponseDto != null);
    }

    @Test
    public void testContactPointApi() throws Exception {
        ContactPointResponseDto contactPointResponseDto = apiUtil.callContactPointApi();

        System.out.println(contactPointResponseDto);

        assertThat(contactPointResponseDto != null);
    }

    @Test
    public void testTravelAlarmApi() throws Exception {
        TravelAlarmResponseDto travelAlarmResponseDto = apiUtil.callTravelAlarmApi();

        System.out.println(travelAlarmResponseDto);

        assertThat(travelAlarmResponseDto != null);
    }
    @Test
    public void testAccidentListApi() throws Exception {
        URI requestUrl = UriComponentsBuilder.fromHttpUrl("http://apis.data.go.kr")
            .path("/1262000/AccidentService/getAccidentList")
            .queryParams(ApiUtil.createQueryParams())
            .build(true)
            .toUri();
        ResponseEntity<Map> responseEntity = restTemplate.exchange(
            new RequestEntity(HttpMethod.GET, requestUrl), Map.class
        );

        Map resultMap = responseEntity.getBody();
        Map<String, Object> responseMap = (Map<String, Object>) resultMap.get("response");
        Map<String, Object> bodyMap = (Map<String, Object>) responseMap.get("body");
        Map<String, Object> itemsMap = (Map<String, Object>) bodyMap.get("items");
        List<Map<String, Object>> itemList = (List<Map<String, Object>>) itemsMap.get("item");

        for (Map<String, Object> item : itemList) {
            AccidentList accidentList = AccidentList.builder()
                .continent((String) item.get("continent"))
                .ename((String) item.get("ename"))
                .name((String) item.get("name"))
                .news((String) item.get("news"))
                .wrtDt((String) item.get("wrtDt"))
                .build();

            System.out.println(accidentList);
        }
    }

}
