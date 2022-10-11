package com.tistory.amyyzzin.trvl.controller;

import com.tistory.amyyzzin.trvl.constant.IsoConstant;
import com.tistory.amyyzzin.trvl.domain.CountryFlag;
import com.tistory.amyyzzin.trvl.domain.CountryInfo;
import com.tistory.amyyzzin.trvl.domain.StandardCode;
import com.tistory.amyyzzin.trvl.dto.GoogleModalDto;
import com.tistory.amyyzzin.trvl.service.CountryBasicInfoService;
import com.tistory.amyyzzin.trvl.service.CountryFlagService;
import com.tistory.amyyzzin.trvl.service.CountryInfoService;
import com.tistory.amyyzzin.trvl.service.StandardCodeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GoogleApiController {

    private final StandardCodeService standardCodeService;

    private final CountryFlagService countryFlagService;

    private final CountryBasicInfoService countryBasicInfoService;
    private final CountryInfoService countryInfoService;


    @ApiOperation(value = "설명", notes = "이것은 노트")
    @GetMapping("/modal")
    public ResponseEntity<GoogleModalDto> getModalDetail(
        @RequestParam @ApiParam(value = "국가 ISO-CODE", example = "KO") String code) {
        StandardCode standardCode = standardCodeService.findByIsoAlp2(code);

        if (standardCode == null) {
            throw new IllegalArgumentException("잘 못된 ISO 코드입니다.");
        }

        CountryFlag countryFlag = countryFlagService.findByIsoAlp2(code);

        CountryInfo countryInfo
            = countryInfoService.findByIsoAlp2(
            IsoConstant.convertCountryEngNm2Iso2(standardCode.getCountryEngNm()));

        if (countryInfo == null) {
            countryInfo = countryInfoService.findByIsoAlp2(standardCode.getIsoAlp2());
        }
        return ResponseEntity.ok(GoogleModalDto.builder()
            .countryNm(standardCode != null && standardCode.getCountryNm() != null ? standardCode.getCountryNm() : "정보 없음")
            .countryEngNm(standardCode != null && standardCode.getCountryEngNm() != null ? standardCode.getCountryEngNm() : "정보 없음")
            .downloadUrl(
                standardCode != null && countryFlag != null ? countryFlag.getDownloadUrl() : "")
            .countryEngNm(countryInfo != null && countryInfo.getCountryEngNm() != null && !Objects.equals(
                countryInfo.getCountryEngNm(), "") ? countryInfo.getCountryEngNm() : "")
            .climateCn(countryInfo != null && countryInfo.getClimateCn() != null && !Objects.equals(
                countryInfo.getClimateCn(), "") ? "기온 : " + countryInfo.getClimateCn() : "")
            .langCn(countryInfo != null && countryInfo.getLangCn() != null && !Objects.equals(
                countryInfo.getLangCn(), "") ? "언어 설명: " + countryInfo.getLangCn() : "")
            .langNm(countryInfo != null && countryInfo.getLangNm() != null && !Objects.equals(
                countryInfo.getLangNm(), "") ? "언어 : " + countryInfo.getLangNm() : "")
            .mainCityCn(countryInfo != null && countryInfo.getMainCityCn() != null && !Objects.equals(
                countryInfo.getMainCityCn(), "") ? "주요 도시 안내 : " + countryInfo.getMainCityCn() : "")
            .mainEthnicCn(countryInfo != null && countryInfo.getMainEthnicCn() != null && !Objects.equals(
                countryInfo.getMainEthnicCn(), "") ? "주요 민족 안내 : " + countryInfo.getMainEthnicCn() : "")
            .mscmctnCn(countryInfo != null && countryInfo.getMscmctnCn() != null && !Objects.equals(
                countryInfo.getMscmctnCn(), "") ? "주요 언론 안내 : " + countryInfo.getMscmctnCn() : "")
            .religionCn(countryInfo != null && countryInfo.getReligionCn() != null  && !Objects.equals(
                countryInfo.getReligionCn(), "") ? "종교 : " + countryInfo.getReligionCn() : "")
            .countryIc(countryInfo != null && countryInfo.getCountryIc() != null && !Objects.equals(
                countryInfo.getCountryIc(), "") ? "국가 위치 : " + countryInfo.getCountryIc() : "")
            .countryCptNm(countryInfo != null && countryInfo.getCountryCptNm() != null && !Objects.equals(
                countryInfo.getCountryCptNm(), "") ? "국가 수도 : " + countryInfo.getCountryCptNm() : "")
            .countryArea(countryInfo != null && countryInfo.getCountryArea() != null && !Objects.equals(
                countryInfo.getCountryArea(), "") ? "국가 면적 : " + countryInfo.getCountryArea() : "")
            .countryAreaComment(countryInfo != null && countryInfo.getCountryAreaComment() != null && !Objects.equals(
                countryInfo.getCountryAreaComment(), "") ? "국가 면적 안내 : " + countryInfo.getCountryAreaComment() : "")

            .iso2code(standardCode != null && standardCode.getIsoAlp2() != null ? standardCode.getIsoAlp2() : "")
            .build());
    }


}
