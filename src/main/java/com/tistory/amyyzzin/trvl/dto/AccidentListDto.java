package com.tistory.amyyzzin.trvl.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccidentListDto {

    @SerializedName("continent")
    private String continent;

    @SerializedName("ename")
    private String ename;

    @SerializedName("name")
    private String name;

    @SerializedName("news")
    private String news;

    private String id;

    private String iso2Code;

}
