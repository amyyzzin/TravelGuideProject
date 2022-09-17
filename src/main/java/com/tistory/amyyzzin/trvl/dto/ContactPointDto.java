package com.tistory.amyyzzin.trvl.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ContactPointDto {

    @SerializedName("contact_remark")
    private String contactRemark;

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

}
