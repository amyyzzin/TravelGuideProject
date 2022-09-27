package com.tistory.amyyzzin.trvl.controller;

import com.tistory.amyyzzin.trvl.constant.IsoConstant;
import com.tistory.amyyzzin.trvl.domain.CountryBasicInfo;
import com.tistory.amyyzzin.trvl.domain.CountryFlag;
import com.tistory.amyyzzin.trvl.domain.StandardCode;
import com.tistory.amyyzzin.trvl.dto.GoogleModalDto;
import com.tistory.amyyzzin.trvl.service.CountryBasicInfoService;
import com.tistory.amyyzzin.trvl.service.CountryFlagService;
import com.tistory.amyyzzin.trvl.service.StandardCodeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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


    @ApiOperation(value = "설명", notes = "이것은 노트")
    @GetMapping("/modal")
    public ResponseEntity<GoogleModalDto> getModalDetail(
        @RequestParam @ApiParam(value = "국가 ISO-CODE", example = "KO") String code) {
        StandardCode standardCode = standardCodeService.findByIsoAlp2(code);

        if (standardCode == null) {
            throw new IllegalArgumentException("잘 못된 ISO 코드입니다.");
        }

        CountryFlag countryFlag = countryFlagService.findByIsoAlp2(code);
        CountryBasicInfo countryBasicInfo
            = countryBasicInfoService.findByIso2Code(
            IsoConstant.convertCountryEngNm2Iso2(standardCode.getCountryEngNm()));

        if (countryBasicInfo == null) {
            countryBasicInfo = countryBasicInfoService.findByIso2Code(standardCode.getIsoAlp2());
        }

        return ResponseEntity.ok(GoogleModalDto.builder()
            .countryNm(standardCode != null ? standardCode.getCountryNm() : "정보 없음")
            .countryEngNm(standardCode != null ? standardCode.getCountryEngNm() : "정보 없음")
            .downloadUrl(
                standardCode != null && countryFlag != null ? countryFlag.getDownloadUrl() : "")
            .basic(countryBasicInfo != null ? countryBasicInfo.getBasic() : "정보 없음")
            .iso2code(countryBasicInfo != null ? countryBasicInfo.getIso2Code() : "")
            .build());
    }
}
