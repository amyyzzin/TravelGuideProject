package com.tistory.amyyzzin.trvl.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tistory.amyyzzin.trvl.domain.StandardCode;
import com.tistory.amyyzzin.trvl.dto.GoogleModalDto;
import com.tistory.amyyzzin.trvl.repository.StandardCodeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GoogleApiController {

	private final StandardCodeRepository standardCodeRepository;

	@GetMapping("/modal")
	public ResponseEntity<GoogleModalDto> getModalDetail(@RequestParam String code) {
		StandardCode standardCode = standardCodeRepository.findByIsoAlp2(code)
			.orElse(null);

		if (standardCode == null) {
			throw new IllegalArgumentException("잘 못된 ISO 코드입니다.");
		}

		return ResponseEntity.ok(GoogleModalDto.builder()
				.countryNm(standardCode.getCountryNm())
				.countryEngNm(standardCode.getCountryEngNm())
				.build());
	}
}
