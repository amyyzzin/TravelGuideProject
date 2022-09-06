package com.tistory.amyyzzin.trvl.domain;

import com.tistory.amyyzzin.trvl.dto.CountryInfoDto;
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
public class CountryInfo {

    @Id
    @GeneratedValue
    private Long id;

    //기후 내용
    private String climateCn;

    // 국가영문명
    private String countryEngNm;

    // ISO 2자리코드
    private String isoAlp2;

    // 국가명
    private String countryNm;

    //언어 내용
    private String langCn;

    //언어명
    private String langNm;

    //주요 도시 내용
    private String mainCityCn;

    //주요 민족 내용
    private String mainEthnicCn;

    //주요 언론 내용 XX
    private String mscmctnCn;

    //종교 내용
    private String religionCn;

    //국가 위치
    private String countryIc;

    //국가 수도명
    private String countryCptNm;

    //국가 면적
    private String countryArea;

    //국가면적 출처
    private String countryAreaSrc;

    //국가면적 설명
    private String countryAreaComment;


    public static CountryInfo of(CountryInfoDto countryInfoDto) {
        return CountryInfo.builder()
            .climateCn(countryInfoDto.getClimateCn())
            .countryEngNm(countryInfoDto.getCountryEngNm())
            .isoAlp2(countryInfoDto.getIsoAlp2())
            .countryNm(countryInfoDto.getCountryNm())
            .langCn(countryInfoDto.getLangCn())
            .langNm(countryInfoDto.getLangNm())
            .mainCityCn(countryInfoDto.getMainCityCn())
            .mainEthnicCn(countryInfoDto.getMainEthnicCn())
            .mscmctnCn(countryInfoDto.getMscmctnCn())
            .religionCn(countryInfoDto.getReligionCn())
            .countryIc(countryInfoDto.getCountryIc())
            .countryCptNm(countryInfoDto.getCountryCptlNm())
            .countryArea(countryInfoDto.getCountryArea())
            .countryAreaSrc(countryInfoDto.getCountryAreaSrc())
            .countryAreaComment(countryInfoDto.getCountryAreaComment())
            .build();
    }

}
