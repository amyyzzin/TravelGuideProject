package com.tistory.amyyzzin.trvl.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.tistory.amyyzzin.trvl.dto.AccidentListResponseDto;
import com.tistory.amyyzzin.trvl.dto.CountryBasicInfoResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

@ActiveProfiles("security")
@SpringBootTest
@WebAppConfiguration
public class ApiUtilTest {

    @Autowired
    ApiUtil apiUtil;

    @Autowired
    RestTemplate restTemplate;

    @Test
    public void testCountryBasicInfoApi() throws Exception {
        CountryBasicInfoResponseDto responseDto = apiUtil.callCountryBasicInfoApi();

        System.out.println(responseDto);
        assertThat(responseDto.getData() != null
            && !responseDto.getData().isEmpty());
    }

    @Test
    public void testAccidentListApi() throws Exception {
        AccidentListResponseDto responseDto = apiUtil.callAccidentListApi();

        System.out.println(responseDto);
        assertThat(responseDto.getData() != null
            && !responseDto.getData().isEmpty());
    }

}
