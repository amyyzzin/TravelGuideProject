package com.tistory.amyyzzin.trvl.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.tistory.amyyzzin.trvl.dto.CountryBasicInfoResponseDto;
import com.tistory.amyyzzin.trvl.dto.CountryFlagResponseDto;
import com.tistory.amyyzzin.trvl.dto.CountryInfoResponseDto;
import com.tistory.amyyzzin.trvl.dto.NoticeListResponseDto;
import com.tistory.amyyzzin.trvl.dto.RegulationResponseDto;
import com.tistory.amyyzzin.trvl.dto.StandardCodeResponseDto;
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
}
