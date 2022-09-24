package com.tistory.amyyzzin.trvl.dto;

import com.google.gson.annotations.SerializedName;
import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountryBasicInfoDto {

    @SerializedName("countryName")
    private String countryNm;

    @SerializedName("countryEnName")
    private String countryEngNm;

    @SerializedName("continent")
    private String continent;

    @SerializedName("basic")
    private String basic;

    @SerializedName("imgUrl")
    private String imgUrl;

    private String id;

    private String iso3Code;

}
