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

    // 국가영문명
    private String alarmLvl;

    // 국가명
    private String continentCd;

    // download_url
    private String continentEngNm;

    // ISO 2자리코드
    private String continentNm;

    // 국가영문명
    private String countryEngNm;

    // 국가명
    private String countryIsoAlp2;

    // download_url
    private String countryNm;

    // 국가영문명
    private String regionTy;

    // 국가명
    private String remark;

    // download_url
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
            .regionTy(travelAlarmDto.getRegionTy())
            .remark(travelAlarmDto.getRemark())
            .writtenDt(travelAlarmDto.getWrittenDt())
            .build();
    }

}
