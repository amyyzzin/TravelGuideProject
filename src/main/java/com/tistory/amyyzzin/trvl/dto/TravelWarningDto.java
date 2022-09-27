package com.tistory.amyyzzin.trvl.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TravelWarningDto {

    @SerializedName("continent")
    private String continent;

    @SerializedName("control")
    private String control;

    @SerializedName("controlNote")
    private String controlNote;

    @SerializedName("countryEnName")
    private String countryEnName;

    @SerializedName("countryName")
    private String countryName;

    @SerializedName("imgUrl2")
    private String imgUrl2;

    private String id;

    private String iso2Code;

}
