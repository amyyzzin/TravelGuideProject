package com.tistory.amyyzzin.trvl.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class OverseasArrivalDto {

    @SerializedName("country_eng_nm")
    private String countryEngNm;

    @SerializedName("country_iso_alp2")
    private String countryIsoAlp2;

    @SerializedName("country_nm")
    private String countryNm;

    @SerializedName("html_origin_cn")
    private String htmlOriginCn;

    @SerializedName("notice_id")
    private String noticeId;

    @SerializedName("title")
    private String title;

    @SerializedName("txt_origin_cn")
    private String txtOriginCn;

    @SerializedName("wrt_dt")
    private String wrtDt;

}
