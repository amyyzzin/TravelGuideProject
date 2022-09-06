package com.tistory.amyyzzin.trvl.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.tistory.amyyzzin.trvl.dto.ContactPointResponseDto;
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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;

@ActiveProfiles("security")
@SpringBootTest
@WebAppConfiguration
public class ApiUtilTest {

    @Autowired
    ApiUtil apiUtil;

    @Test
    public void testRegulationApi() throws IOException {
        RegulationResponseDto regulationResponseDto = apiUtil.callRegulationApi();

        System.out.println(regulationResponseDto);
        assertThat(regulationResponseDto != null);
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
        assertThat(countryInfoResponseDto != null);

    }

//    @Test
//    public void testCountryBasicInfoApi() throws Exception {
//        CountryBasicInfoResponseDto countryBasicInfoResponseDto = test.callCountryBasicInfoApi();
//
//        System.out.println(countryBasicInfoResponseDto);
//        assertThat(countryBasicInfoResponseDto != null);
//
//    }

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
}
