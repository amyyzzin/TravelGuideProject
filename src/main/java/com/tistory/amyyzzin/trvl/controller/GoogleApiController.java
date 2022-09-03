package com.tistory.amyyzzin.trvl.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tistory.amyyzzin.trvl.domain.CountryFlag;
import com.tistory.amyyzzin.trvl.domain.StandardCode;
import com.tistory.amyyzzin.trvl.dto.GoogleModalDto;
import com.tistory.amyyzzin.trvl.service.CountryFlagService;
import com.tistory.amyyzzin.trvl.service.StandardCodeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GoogleApiController {

	private final StandardCodeService standardCodeService;

	private final CountryFlagService countryFlagService;

	@GetMapping("/modal")
	public ResponseEntity<GoogleModalDto> getModalDetail(@RequestParam String code) {
		StandardCode standardCode = standardCodeService.findByIsoAlp2(code);
		CountryFlag countryFlag = countryFlagService.findByIsoAlp2(code);

		if (standardCode == null || countryFlag == null) {
			throw new IllegalArgumentException("잘 못된 ISO 코드입니다.");
		}

		return ResponseEntity.ok(GoogleModalDto.builder()
				.countryNm(standardCode.getCountryNm())
				.countryEngNm(standardCode.getCountryEngNm())
				.downloadUrl(countryFlag.getDownloadUrl())
				.build());
	}
}
