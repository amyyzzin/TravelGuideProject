package com.tistory.amyyzzin.trvl.domain;

import com.tistory.amyyzzin.trvl.dto.NoticeListDto;
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
public class NoticeList {

    @Id
    @GeneratedValue
    private Long id;

    private String listId;

    private String fileDownloadUrl;

    @Lob
    private String title;

    @Lob
    private String txtOriginCn;

    private String writtenDt;


    public static NoticeList of(NoticeListDto noticeListDto) {
        return NoticeList.builder()
            .listId(noticeListDto.getListId())
            .fileDownloadUrl(noticeListDto.getFileDownloadUrl().replace("sn=1", "sn=0"))
            .title(noticeListDto.getTitle())
//            .txtOriginCn(noticeListDto.getTxtOriginCn())
            .txtOriginCn(Jsoup.parse(noticeListDto.getTxtOriginCn()).text()
                .replace("다.", "다. <br>")
                .replace("○", "<br> ○")
                .replace("□", "<br> □"))
            .writtenDt(noticeListDto.getWrittenDt())
            .build();
    }

}
