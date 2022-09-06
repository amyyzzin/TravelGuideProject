package com.tistory.amyyzzin.trvl.domain;

import com.tistory.amyyzzin.trvl.dto.EmbassyHomepageDto;
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
public class EmbassyHomepage {

    @Id
    @GeneratedValue
    private Long id;

    // 국가영문명
    private String embassyCd;

    // 국가명
    private String embassyKorNm;

    // download_url
    private String embassyOriginalNm;

    // ISO 2자리코드
    private String homepageUrl;

    // originFileNm
    private String langCd;

    // 국가영문명
    private String langCdNm;

    // 국가명
    private String siteTyCd;

    // download_url
    private String siteTyCdNm;

    public static EmbassyHomepage of(EmbassyHomepageDto embassyHomepageDto) {
        return EmbassyHomepage.builder()
            .embassyCd(embassyHomepageDto.getEmbassyCd())
            .embassyKorNm(embassyHomepageDto.getEmbassyKorNm())
            .embassyOriginalNm(embassyHomepageDto.getEmbassyOriginalNm())
            .homepageUrl(embassyHomepageDto.getHomepageUrl())
            .langCd(embassyHomepageDto.getLangCd())
            .langCdNm(embassyHomepageDto.getLangCdNm())
            .siteTyCd(embassyHomepageDto.getSiteTyCd())
            .siteTyCdNm(embassyHomepageDto.getSiteTyCdNm())
            .build();
    }

}
