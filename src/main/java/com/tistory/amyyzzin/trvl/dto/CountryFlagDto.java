package com.tistory.amyyzzin.trvl.dto;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class CountryFlagDto {

	@SerializedName("country_nm")
	private String countryNm;

	@SerializedName("country_eng_nm")
	private String countryEngNm;

	@SerializedName("download_url")
	private String downloadUrl;

	@SerializedName("country_iso_alp2")
	private String isoAlp2;

	@SerializedName("origin_file_nm")
	private String originFileNm;
}
