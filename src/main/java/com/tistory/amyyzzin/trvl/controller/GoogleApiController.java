package com.tistory.amyyzzin.trvl.controller;

import com.tistory.amyyzzin.trvl.domain.CountryInfo;
import com.tistory.amyyzzin.trvl.service.CountryInfoService;
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

	private final CountryInfoService countryInfoService;

	@GetMapping("/modal")
	public ResponseEntity<GoogleModalDto> getModalDetail(@RequestParam String code) {
		StandardCode standardCode = standardCodeService.findByIsoAlp2(code);
		CountryFlag countryFlag = countryFlagService.findByIsoAlp2(code);
		CountryInfo countryInfo = countryInfoService.findByIsoAlp2(code);

		if (standardCode == null || countryFlag == null) {
			throw new IllegalArgumentException("잘 못된 ISO 코드입니다.");
		}

		return ResponseEntity.ok(GoogleModalDto.builder()
				.countryNm(standardCode.getCountryNm())
				.countryEngNm(standardCode.getCountryEngNm())

				.downloadUrl(countryFlag.getDownloadUrl())

				.climateCn(countryInfo.getClimateCn())
				.langCn(countryInfo.getLangCn())
				.langNm(countryInfo.getLangNm())
				.mainCityCn(countryInfo.getMainCityCn())
				.mainEthnicCn(countryInfo.getMainEthnicCn())
				.mscmctnCn(countryInfo.getMscmctnCn())
				.religionCn(countryInfo.getReligionCn())
				.countryIc(countryInfo.getCountryIc())
				.countryCptNm(countryInfo.getCountryCptNm())
				.countryArea(countryInfo.getCountryArea())
				.countryAreaSrc(countryInfo.getCountryAreaSrc())
				.countryAreaComment(countryInfo.getCountryAreaComment())
				.build());
	}
}
