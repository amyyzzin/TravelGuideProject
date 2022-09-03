package com.tistory.amyyzzin.trvl;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.tistory.amyyzzin.trvl.dto.CountryFlagResponseDto;
import com.tistory.amyyzzin.trvl.dto.RegulationResponseDto;
import com.tistory.amyyzzin.trvl.dto.StandardCodeResponseDto;
import com.tistory.amyyzzin.trvl.util.ApiUtil;

@ActiveProfiles("security")
@SpringBootTest
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
}
