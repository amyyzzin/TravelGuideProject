package com.tistory.amyyzzin.trvl.domain;

import com.tistory.amyyzzin.trvl.dto.CountryFlagDto;
import com.tistory.amyyzzin.trvl.dto.EmbassyInfoDto;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
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

    // 영사관 번호
    private String centerTelNo;

    // 국가명
    private String countryNm;

    // 영문 국가명
    private String countryEngNm;

    // ISO 2자리코드
    private String countryIsoAlp2;

    // 재외공관한글명
    private String embassyKorNm;

    // 재외공관위도
    private String embassyLat;

    // 재외공관경도
    private String embassyLng;

    // 재외공관 관리유형코드명
    private String embassyManageTyCd;

    // ISO 2자리코드
    private String embassyManageTyCdNm;

    // 재외공관유형코드
    private String embassyTyCd;

    // 재외공관유형코드명
    private String embassyTyCdNm;

    // 재외공관주소
    @Lob
    private String emblgbdAddr;

    // 무료전화번호
    private String freeTelNo;

    // 대표전화번호
    private String telNo;

    // 긴급전화번호
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
