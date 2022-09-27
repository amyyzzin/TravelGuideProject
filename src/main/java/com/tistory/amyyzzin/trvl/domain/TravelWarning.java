package com.tistory.amyyzzin.trvl.domain;

import com.tistory.amyyzzin.trvl.dto.TravelWarningDto;
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
public class TravelWarning {

    @Id
    @GeneratedValue
    private Long id;

    private String continent;

    // 경고상태
    private String control;

    // 경고지역
    private String controlNote;

    private String countryEnName;

    private String countryName;

    // 국가지도
    private String imgUrl2;

    private String iso2Code;


    public static TravelWarning of(TravelWarningDto travelWarningDto) {
        return TravelWarning.builder()
            .continent(travelWarningDto.getContinent())
            .control(travelWarningDto.getControl())
            .controlNote(travelWarningDto.getControlNote())
            .countryEnName(travelWarningDto.getCountryEnName())
            .countryName(travelWarningDto.getCountryName())
            .imgUrl2(travelWarningDto.getImgUrl2())
            .iso2Code(travelWarningDto.getIso2Code())
            .build();
    }
}
