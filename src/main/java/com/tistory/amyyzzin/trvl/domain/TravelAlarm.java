package com.tistory.amyyzzin.trvl.domain;

import com.tistory.amyyzzin.trvl.dto.ContactPointDto;
import com.tistory.amyyzzin.trvl.dto.TravelAlarmDto;
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
public class TravelAlarm {

    @Id
    @GeneratedValue
    private Long id;

    // 경보단계
    private String alarmLvl;

    // 대륙코드
    private String continentCd;

    // 영문 대륙명
    private String continentEngNm;

    // 한글 대륙명
    private String continentNm;

    // 영문 국가명
    private String countryEngNm;

    // ISO 2자리코드
    private String countryIsoAlp2;

    // 한글 국가명
    private String countryNm;

    // 위험지도경로
    private String dangMapDownloadUrl;

    // 지역유형
    private String regionTy;

    // 비고
    private String remark;

    // 작성일
    private String writtenDt;

    public static TravelAlarm of(TravelAlarmDto travelAlarmDto) {
        return TravelAlarm.builder()
            .alarmLvl(travelAlarmDto.getAlarmLvl())
            .continentCd(travelAlarmDto.getContinentCd())
            .continentEngNm(travelAlarmDto.getContinentEngNm())
            .continentNm(travelAlarmDto.getContinentNm())
            .countryEngNm(travelAlarmDto.getCountryEngNm())
            .countryIsoAlp2(travelAlarmDto.getCountryIsoAlp2())
            .countryNm(travelAlarmDto.getCountryNm())
            .dangMapDownloadUrl(travelAlarmDto.getDangMapDownloadUrl())
            .regionTy(travelAlarmDto.getRegionTy())
            .remark(travelAlarmDto.getRemark())
            .writtenDt(travelAlarmDto.getWrittenDt())
            .build();
    }

}
