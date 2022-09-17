package com.tistory.amyyzzin.trvl.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class EmbassyHomepageDto {

    @SerializedName("embassy_cd")
    private String embassyCd;

    @SerializedName("embassy_kor_nm")
    private String embassyKorNm;

    @SerializedName("embassy_original_nm")
    private String embassyOriginalNm;

    @SerializedName("homepage_url")
    private String homepageUrl;

    @SerializedName("lang_cd")
    private String langCd;

    @SerializedName("lang_cd_nm")
    private String langCdNm;

    @SerializedName("site_ty_cd")
    private String siteTyCd;

    @SerializedName("site_ty_cd_nm")
    private String siteTyCdNm;
}
