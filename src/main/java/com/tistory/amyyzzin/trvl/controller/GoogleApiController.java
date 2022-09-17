package com.tistory.amyyzzin.trvl.controller;

import com.tistory.amyyzzin.trvl.domain.CountryInfo;
import com.tistory.amyyzzin.trvl.service.CountryInfoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

	@ApiOperation(value = "설명", notes = "이것은 노트")
	@GetMapping("/modal")
	public ResponseEntity<GoogleModalDto> getModalDetail(@RequestParam  @ApiParam(value = "국가 ISO-CODE", example = "KO") String code) {
		StandardCode standardCode = standardCodeService.findByIsoAlp2(code);
		CountryFlag countryFlag = countryFlagService.findByIsoAlp2(code);
		CountryInfo countryInfo = countryInfoService.findByIsoAlp2(code);

		if (standardCode == null) {
			throw new IllegalArgumentException("잘 못된 ISO 코드입니다.");
		}

		return ResponseEntity.ok(GoogleModalDto.builder()
				.countryNm(standardCode != null ? standardCode.getCountryNm() : "정보 없음")
				.countryEngNm(standardCode != null ? standardCode.getCountryEngNm() : "정보 없음")
				.downloadUrl(standardCode != null ? countryFlag.getDownloadUrl() : "")
				.climateCn(countryInfo != null ? countryInfo.getClimateCn() : "정보 없음")
				.langCn(countryInfo != null ? countryInfo.getLangCn() : "정보 없음")
				.langNm(countryInfo != null ? countryInfo.getLangNm() : "정보 없음")
				.mainCityCn(countryInfo != null ? countryInfo.getMainCityCn() : "정보 없음")
				.mainEthnicCn(countryInfo != null ? countryInfo.getMainEthnicCn() : "정보 없음")
				.mscmctnCn(countryInfo != null ? countryInfo.getMscmctnCn() : "정보 없음")
				.religionCn(countryInfo != null ? countryInfo.getReligionCn() : "정보 없음")
				.countryIc(countryInfo != null ? countryInfo.getCountryIc() : "정보 없음")
				.countryCptNm(countryInfo != null ? countryInfo.getCountryCptNm() : "정보 없음")
				.countryArea(countryInfo != null ? countryInfo.getCountryArea() : "정보 없음")
				.countryAreaSrc(countryInfo != null ? countryInfo.getCountryAreaSrc() : "정보 없음")
				.countryAreaComment(countryInfo != null ? countryInfo.getCountryAreaComment() : "정보 없음")
				.build());
	}
}
