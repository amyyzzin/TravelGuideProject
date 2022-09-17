package com.tistory.amyyzzin.trvl.dto;

import com.google.gson.annotations.SerializedName;
import javax.persistence.Column;
import lombok.Data;

@Data
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
}
