package com.tistory.amyyzzin.trvl.dto;

import com.google.gson.annotations.SerializedName;
import java.util.Map;
import javax.persistence.Lob;
import lombok.Data;

@Data
public class SafetyListDto {

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

    @SerializedName("ctgy_nm")
    private String ctgyNm;

    @SerializedName("file_download_url")
    private String fileDownloadUrl;

    @SerializedName("file_path")
    private String filePath;

    @SerializedName("title")
    private String title;

    @SerializedName("txt_origin_cn")
    private String txtOriginCn;

    @SerializedName("wrt_dt")
    private String wrtDt;

}
