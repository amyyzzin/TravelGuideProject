package com.tistory.amyyzzin.trvl.dto;

import com.google.gson.annotations.SerializedName;
import java.util.Map;
import lombok.Data;

@Data
public class CountryBasicInfoDto {

    @SerializedName("countryName")
    private String countryNm;

    @SerializedName("countryEnName")
    private String countryEngNm;

    @SerializedName("continent")
    private String continent;

    @SerializedName("basic")
    private String basic;

    private Map<String, Object> data;
    private String id;

}
