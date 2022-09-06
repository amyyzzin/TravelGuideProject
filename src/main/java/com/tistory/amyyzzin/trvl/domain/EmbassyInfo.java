package com.tistory.amyyzzin.trvl.domain;

import com.tistory.amyyzzin.trvl.dto.CountryFlagDto;
import com.tistory.amyyzzin.trvl.dto.EmbassyInfoDto;
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
public class EmbassyInfo {

    @Id
    @GeneratedValue
    private Long id;

    // 국가영문명
    private String centerTelNo;

    // 국가명
    private String countryNm;

    // download_url
    private String countryEngNm;

    // ISO 2자리코드
    private String countryIsoAlp2;

    // originFileNm
    private String embassyKorNm;

    // 국가영문명
    private String embassyLat;

    // 국가명
    private String embassyLng;

    // download_url
    private String embassyManageTyCd;

    // ISO 2자리코드
    private String embassyManageTyCdNm;

    // originFileNm
    private String embassyTyCd;

    // 국가영문명
    private String embassyTyCdNm;

    // 국가명
    private String emblgbdAddr;

    // download_url
    private String freeTelNo;

    // ISO 2자리코드
    private String telNo;

    // originFileNm
    private String urgencyTelNo;

    public static EmbassyInfo of(EmbassyInfoDto embassyInfoDto) {
        return EmbassyInfo.builder()
            .centerTelNo(embassyInfoDto.getCenterTelNo())
            .countryEngNm(embassyInfoDto.getCountryEngNm())
            .countryIsoAlp2(embassyInfoDto.getCountryIsoAlp2())
            .countryNm(embassyInfoDto.getCountryNm())
            .embassyKorNm(embassyInfoDto.getEmbassyKorNm())
            .embassyLat(embassyInfoDto.getEmbassyLat())
            .embassyLng(embassyInfoDto.getEmbassyLng())
            .embassyManageTyCd(embassyInfoDto.getEmbassyManageTyCd())
            .embassyManageTyCdNm(embassyInfoDto.getEmbassyManageTyCdNm())
            .embassyTyCd(embassyInfoDto.getEmbassyTyCd())
            .embassyTyCdNm(embassyInfoDto.getEmbassyTyCdNm())
            .emblgbdAddr(embassyInfoDto.getEmblgbdAddr())
            .freeTelNo(embassyInfoDto.getFreeTelNo())
            .telNo(embassyInfoDto.getTelNo())
            .urgencyTelNo(embassyInfoDto.getUrgencyTelNo())
            .build();
    }

}
