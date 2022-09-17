package com.tistory.amyyzzin.trvl.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class CountryInfoDto {

	@SerializedName("climate_cn")
	private String climateCn;

	@SerializedName("country_eng_nm")
	private String countryEngNm;

	@SerializedName("country_iso_alp2")
	private String isoAlp2;

	@SerializedName("country_nm")
	private String countryNm;

	@SerializedName("lang_cn")
	private String langCn;

	@SerializedName("lang_nm")
	private String langNm;

	@SerializedName("main_city_cn")
	private String mainCityCn;

	@SerializedName("main_ethnic_cn")
	private String mainEthnicCn;

	@SerializedName("mscmctn_cn")
	private String mscmctnCn;

	@SerializedName("religion_cn")
	private String religionCn;

	@SerializedName("country_lc")
	private String countryIc;

	@SerializedName("country_cptl_nm")
	private String countryCptlNm;

	@SerializedName("country_area")
	private String countryArea;

	@SerializedName("country_area_src")
	private String countryAreaSrc;

	@SerializedName("country_area_comment")
	private String countryAreaComment;
}
