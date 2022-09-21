package com.tistory.amyyzzin.trvl.dto;

import com.google.gson.annotations.SerializedName;
import com.tistory.amyyzzin.trvl.domain.NoticeList;
import com.tistory.amyyzzin.trvl.domain.SafetyList;
import java.util.Map;
import javax.persistence.Lob;
import lombok.Builder;
import lombok.Data;
import org.jsoup.Jsoup;

@Data
@Builder
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

    public static SafetyListDto of(SafetyList safetyList) {
        return SafetyListDto.builder()
            .continentCd(safetyList.getContinentCd())
            .continentEngNm(safetyList.getContinentEngNm())
            .continentNm(safetyList.getContinentNm())
            .countryEngNm(safetyList.getCountryEngNm())
            .countryIsoAlp2(safetyList.getCountryIsoAlp2())
            .countryNm(safetyList.getCountryNm())
            .ctgyNm(safetyList.getCtgyNm())
            .fileDownloadUrl(safetyList.getFileDownloadUrl())
            .filePath(safetyList.getFilePath())
            .title(safetyList.getTitle())
            .txtOriginCn(safetyList.getTxtOriginCn())
//            .title(Jsoup.parse(safetyList.getTitle()).text().replace("\uFEFF", ""))
//            .txtOriginCn(Jsoup.parse(safetyList.getTxtOriginCn()).text().replace("\uFEFF", ""))
            .wrtDt(safetyList.getWrtDt())
            .build();
    }

}
