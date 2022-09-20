package com.tistory.amyyzzin.trvl.dto;

import com.google.gson.annotations.SerializedName;
import com.tistory.amyyzzin.trvl.domain.NoticeList;
import javax.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NoticeListDto {

    @SerializedName("id")
    private String listId;

    @SerializedName("file_download_url")
    private String fileDownloadUrl;

    @SerializedName("title")
    private String title;

    @SerializedName("txt_origin_cn")
    private String txtOriginCn;

    @SerializedName("written_dt")
    private String writtenDt;


    public static NoticeListDto of(NoticeList noticeList) {
        return NoticeListDto.builder()
            .listId(noticeList.getListId())
            .fileDownloadUrl(noticeList.getFileDownloadUrl())
            .title(noticeList.getTitle())
            .txtOriginCn(noticeList.getTxtOriginCn())
            .writtenDt(noticeList.getWrittenDt())
            .build();
    }
}
