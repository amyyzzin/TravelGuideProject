package com.tistory.amyyzzin.trvl.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class EmbassyInfoDto {

    @SerializedName("center_tel_no")
    private String centerTelNo;

    @SerializedName("country_eng_nm")
    private String countryEngNm;

    @SerializedName("country_iso_alp2")
    private String countryIsoAlp2;

    @SerializedName("country_nm")
    private String countryNm;

    @SerializedName("embassy_kor_nm")
    private String embassyKorNm;

    @SerializedName("embassy_lat")
    private String embassyLat;

    @SerializedName("embassy_lng")
    private String embassyLng;

    @SerializedName("embassy_manage_ty_cd")
    private String embassyManageTyCd;

    @SerializedName("embassy_manage_ty_cd_nm")
    private String embassyManageTyCdNm;

    @SerializedName("embassy_ty_cd")
    private String embassyTyCd;

    @SerializedName("embassy_ty_cd_nm")
    private String embassyTyCdNm;

    @SerializedName("emblgbd_addr")
    private String emblgbdAddr;

    @SerializedName("free_tel_no")
    private String freeTelNo;

    @SerializedName("tel_no")
    private String telNo;

    @SerializedName("urgency_tel_no")
    private String urgencyTelNo;
}
