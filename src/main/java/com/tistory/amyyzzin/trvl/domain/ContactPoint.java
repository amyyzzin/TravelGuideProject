package com.tistory.amyyzzin.trvl.domain;

import com.tistory.amyyzzin.trvl.dto.ContactPointDto;
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
public class ContactPoint {

    @Id
    @GeneratedValue
    private Long id;

    // 국가영문명
    @Lob
    private String contactRemark;

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


    public static ContactPoint of(ContactPointDto contactPointDto) {
        return ContactPoint.builder()
            .contactRemark(contactPointDto.getContactRemark())
            .continentCd(contactPointDto.getContinentCd())
            .continentEngNm(contactPointDto.getContinentEngNm())
            .continentNm(contactPointDto.getContinentNm())
            .countryEngNm(contactPointDto.getCountryEngNm())
            .countryIsoAlp2(contactPointDto.getCountryIsoAlp2())
            .countryNm(contactPointDto.getCountryNm())
            .build();
    }

}
