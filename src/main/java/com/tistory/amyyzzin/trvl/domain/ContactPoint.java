package com.tistory.amyyzzin.trvl.domain;

import com.tistory.amyyzzin.trvl.dto.ContactPointDto;
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

    // 현지연락처정보
    @Lob
    private String contactRemark;

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

    // 한글 대륙명
    private String countryNm;


    public static ContactPoint of(ContactPointDto contactPointDto) {
        return ContactPoint.builder()
            .contactRemark(contactPointDto.getContactRemark()
                .replace("h3", "p style=\"font-weight: bold;\""))
//            .contactRemark(Jsoup.parse(contactPointDto.getContactRemark()).text().replace("\uFEFF", ""))
            .continentCd(contactPointDto.getContinentCd())
            .continentEngNm(contactPointDto.getContinentEngNm())
            .continentNm(contactPointDto.getContinentNm())
            .countryEngNm(contactPointDto.getCountryEngNm())
            .countryIsoAlp2(contactPointDto.getCountryIsoAlp2())
            .countryNm(contactPointDto.getCountryNm())
            .build();
    }

}
