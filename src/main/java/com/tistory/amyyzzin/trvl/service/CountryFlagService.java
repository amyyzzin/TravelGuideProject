package com.tistory.amyyzzin.trvl.service;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.tistory.amyyzzin.trvl.domain.CountryFlag;
import com.tistory.amyyzzin.trvl.dto.CountryFlagDto;
import com.tistory.amyyzzin.trvl.dto.CountryFlagResponseDto;
import com.tistory.amyyzzin.trvl.repository.CountryFlagRepository;
import com.tistory.amyyzzin.trvl.util.ApiUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CountryFlagService {

	private final ApiUtil apiUtil;

	private final CountryFlagRepository countryFlagRepository;

	@PostConstruct
	public void init() throws IOException {
		if (countryFlagRepository.count() > 0) {
			return;
		}

		insert(apiUtil.callCountryFlagApi());
	}

	public CountryFlag findByIsoAlp2(String isoAlp2) {
		return countryFlagRepository.findByIsoAlp2(isoAlp2).orElse(null);
	}

	public void insert(CountryFlagResponseDto countryFlagResponseDto) {
		if (countryFlagResponseDto == null) {
			return;
		}

		log.info("[countryFlagDto] {}", countryFlagResponseDto);

		for (CountryFlagDto countryFlagDto : countryFlagResponseDto.getData()) {
			try {
				countryFlagRepository.save(CountryFlag.of(countryFlagDto));
			} catch (Exception e) {
				log.error("[CountryFlag.insert] ERROR {}", e.getMessage());
			}
		}

	}
}
