package com.tistory.amyyzzin.trvl.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class TravelAlarmDto {

    @SerializedName("alarm_lvl")
    private String alarmLvl;

    @SerializedName("continent_cd")
    private String continentCd;

    @SerializedName("continent_eng_nm")
    private String continentEngNm;

    @SerializedName("continent_nm")
    private String continentNm;

    @SerializedName("country_eng_nm")
    private String countryEngNm;

    @SerializedName("country_iso_alp2")
    private String countryIsoAlp2;

    @SerializedName("country_nm")
    private String countryNm;

    @SerializedName("dang_map_download_url")
    private String dangMapDownloadUrl;

    @SerializedName("region_ty")
    private String regionTy;

    @SerializedName("remark")
    private String remark;

    @SerializedName("written_dt")
    private String writtenDt;

}
