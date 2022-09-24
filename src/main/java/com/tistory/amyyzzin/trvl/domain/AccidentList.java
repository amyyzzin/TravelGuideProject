package com.tistory.amyyzzin.trvl.domain;

import com.tistory.amyyzzin.trvl.dto.AccidentListDto;
import com.tistory.amyyzzin.trvl.dto.ContactPointDto;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccidentList {

    @Id
    @GeneratedValue
    private Long id;

    private String continent;

    // 국가명
    private String ename;

    // download_url
    private String name;

    @Lob
    private String news;

    // 국가영문명
    private String wrtDt;


    public static AccidentList of(AccidentListDto accidentListDto) {
        return AccidentList.builder()
            .continent(accidentListDto.getContinent())
            .ename(accidentListDto.getEname())
            .name(accidentListDto.getName())
            .news(Jsoup.parse(accidentListDto.getNews()).text().replace("\uFEFF", ""))
            .build();
    }

}
