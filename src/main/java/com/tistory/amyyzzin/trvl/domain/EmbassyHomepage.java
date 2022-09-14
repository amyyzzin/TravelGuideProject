package com.tistory.amyyzzin.trvl.domain;

import com.tistory.amyyzzin.trvl.dto.EmbassyHomepageDto;
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
public class EmbassyHomepage {

    @Id
    @GeneratedValue
    private Long id;

    // 재외공관코드
    private String embassyCd;

    // 재외공관한글명
    private String embassyKorNm;

    //
    private String embassyOriginalNm;

    // 홈페이지 url
    @Lob
    private String homepageUrl;

    //
    private String langCd;

    //
    private String langCdNm;

    //
    private String siteTyCd;

    //
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
