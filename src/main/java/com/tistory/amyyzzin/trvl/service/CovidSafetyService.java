package com.tistory.amyyzzin.trvl.service;

import com.tistory.amyyzzin.trvl.domain.CovidSafety;
import com.tistory.amyyzzin.trvl.domain.SafetyList;
import com.tistory.amyyzzin.trvl.dto.CovidSafetyDto;
import com.tistory.amyyzzin.trvl.dto.CovidSafetyResponseDto;
import com.tistory.amyyzzin.trvl.dto.SafetyListDto;
import com.tistory.amyyzzin.trvl.dto.SafetyListResponseDto;
import com.tistory.amyyzzin.trvl.repository.CovidSafetyRepository;
import com.tistory.amyyzzin.trvl.util.ApiUtil;
import java.io.IOException;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CovidSafetyService {

	private final ApiUtil apiUtil;

	private final CovidSafetyRepository covidSafetyRepository;

	@PostConstruct
	public void init() throws IOException {
		if (covidSafetyRepository.count() > 0) {
			return;
		}

		insert(apiUtil.callCovidSafetyApi());
	}

	public void insert(CovidSafetyResponseDto covidSafetyResponseDto) {
		if (covidSafetyResponseDto == null) {
			return;
		}

		log.info("[CovidSafetyDto] {}", covidSafetyResponseDto);

		for (CovidSafetyDto covidSafetyDto : covidSafetyResponseDto.getData()) {
			try {
				covidSafetyRepository.save(CovidSafety.of(covidSafetyDto));
			} catch (Exception e) {
				log.error("[CovidSafety.insert] ERROR {}", e.getMessage());
			}
		}

	}
}
