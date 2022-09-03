package com.tistory.amyyzzin.trvl;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tistory.amyyzzin.trvl.dto.RegulationResponseDto;
import com.tistory.amyyzzin.trvl.util.ApiUtil;

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
}
