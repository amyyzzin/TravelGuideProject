package com.tistory.amyyzzin.trvl.domain;

import com.tistory.amyyzzin.trvl.dto.CountryBasicInfoDto;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryBasicInfo {

    @Id
    @GeneratedValue
    private Long id;

    // 국기이미지경로
    private String imgUrl;

    // 국가명
    private String countryNm;

    // 영문국가명
    private String countryEngNm;

    // 대륙
    private String continent;

    // 기본정보
    private String basic;


    public static CountryBasicInfo of(CountryBasicInfoDto countryBasicInfoDto) {
        return CountryBasicInfo.builder()
            .countryNm(countryBasicInfoDto.getCountryNm())
            .countryEngNm(countryBasicInfoDto.getCountryEngNm())
            .continent(countryBasicInfoDto.getContinent())
            .basic(countryBasicInfoDto.getBasic())
            .build();
    }

}