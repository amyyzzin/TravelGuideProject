package com.tistory.amyyzzin.trvl.util;

import static org.assertj.core.api.Assertions.assertThat;

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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;

@ActiveProfiles("security")
@SpringBootTest
@WebAppConfiguration
public class GenericApiUtilTest {

    @Autowired
    GenericApiUtil genericApiUtil;

    @Value("${open.api.regulation}")
    String regulationUrl;

    @Value("${open.api.standardCode}")
    String standardCodeUrl;

    @Value("${open.api.countryFlag}")
    String countryFlagUrl;

    @Value("${open.api.countryInfo}")
    String countryInfoUrl;

    @Value("${open.api.notice}")
    String noticeUrl;

    @Value("${open.api.safetyList}")
    String safetyListUrl;

    @Value("${open.api.covidSafety}")
    String covidSafetyUrl;

    @Value("${open.api.overSeasArrival}")
    String overSeasArrivalUrl;

    @Value("${open.api.embassyInfo}")
    String embassyInfoUrl;

    @Value("${open.api.embassyHomepage}")
    String embassyHomepageUrl;

    @Value("${open.api.contactPoint}")
    String contactPointUrl;

    @Value("${open.api.travelAlarm}")
    String travelAlarmUrl;

    @Test
    public void callRegulationApi() throws IOException {
        RegulationResponseDto responseDto
            = (RegulationResponseDto) genericApiUtil.callJsonApi(regulationUrl, RegulationResponseDto.class);

        System.out.println(responseDto);
        assertThat(responseDto.getData() != null
            && !responseDto.getData().isEmpty());
    }

    @Test
    public void callStandardCodeApi() throws IOException {
        StandardCodeResponseDto responseDto
            = (StandardCodeResponseDto) genericApiUtil.callJsonApi(standardCodeUrl, StandardCodeResponseDto.class);

        System.out.println(responseDto);
        assertThat(responseDto.getData() != null
            && !responseDto.getData().isEmpty());
    }

    @Test
    public void testCountryFlagApi() throws Exception {
        CountryFlagResponseDto responseDto
            = (CountryFlagResponseDto) genericApiUtil.callJsonApi(countryFlagUrl, CountryFlagResponseDto.class);

        System.out.println(responseDto);
        assertThat(responseDto != null
            && !responseDto.getData().isEmpty());
    }

    @Test
    public void testCountryInfoApi() throws Exception {
        CountryInfoResponseDto responseDto
            = (CountryInfoResponseDto) genericApiUtil.callJsonApi(countryInfoUrl, CountryInfoResponseDto.class);

        System.out.println(responseDto);
        assertThat(responseDto.getData() != null
            && !responseDto.getData().isEmpty());
    }

    @Test
    public void testNoticeListApi() throws Exception {
        NoticeListResponseDto responseDto
            = (NoticeListResponseDto) genericApiUtil.callJsonApi(noticeUrl, NoticeListResponseDto.class);

        System.out.println(responseDto);
        assertThat(responseDto != null
            && !responseDto.getData().isEmpty());
    }

    @Test
    public void testSafetyListApi() throws Exception {
        SafetyListResponseDto responseDto
            = (SafetyListResponseDto) genericApiUtil.callJsonApi(safetyListUrl, SafetyListResponseDto.class);

        System.out.println(responseDto);
        assertThat(responseDto != null
            && !responseDto.getData().isEmpty());
    }

    @Test
    public void testCovidSafetyApi() throws Exception {
        CovidSafetyResponseDto responseDto
            = (CovidSafetyResponseDto) genericApiUtil.callJsonApi(covidSafetyUrl, CovidSafetyResponseDto.class);

        System.out.println(responseDto);
        assertThat(responseDto != null
            && !responseDto.getData().isEmpty());
    }

    @Test
    public void testOverseasArrivalApi() throws Exception {
        OverseasArrivalResponseDto responseDto
            = (OverseasArrivalResponseDto) genericApiUtil.callJsonApi(overSeasArrivalUrl, OverseasArrivalResponseDto.class);

        System.out.println(responseDto);
        assertThat(responseDto != null
            && !responseDto.getData().isEmpty());
    }

    @Test
    public void testEmbassyInfoApi() throws Exception {
        EmbassyInfoResponseDto responseDto
            = (EmbassyInfoResponseDto) genericApiUtil.callJsonApi(embassyInfoUrl, EmbassyInfoResponseDto.class);

        System.out.println(responseDto);
        assertThat(responseDto != null
            && !responseDto.getData().isEmpty());
    }

    @Test
    public void testEmbassyHomepageApi() throws Exception {
        EmbassyHomepageResponseDto responseDto
            = (EmbassyHomepageResponseDto) genericApiUtil.callJsonApi(embassyHomepageUrl, EmbassyHomepageResponseDto.class);

        System.out.println(responseDto);
        assertThat(responseDto != null
            && !responseDto.getData().isEmpty());
    }

    @Test
    public void testContactPointApi() throws Exception {
        ContactPointResponseDto responseDto
            = (ContactPointResponseDto) genericApiUtil.callJsonApi(contactPointUrl, ContactPointResponseDto.class);

        System.out.println(responseDto);
        assertThat(responseDto != null
            && !responseDto.getData().isEmpty());
    }

    @Test
    public void testTravelAlarmApi() throws Exception {
        TravelAlarmResponseDto responseDto
            = (TravelAlarmResponseDto) genericApiUtil.callJsonApi(travelAlarmUrl, TravelAlarmResponseDto.class);

        System.out.println(responseDto);
        assertThat(responseDto != null
            && !responseDto.getData().isEmpty());
    }

}
