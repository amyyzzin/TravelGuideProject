package com.tistory.amyyzzin.trvl.dto;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class StandardCodeDto {

	@SerializedName("country_nm")
	private String countryNm;

	@SerializedName("country_eng_nm")
	private String countryEngNm;

	@SerializedName("iso_num")
	private String isoNum;

	@SerializedName("country_iso_alp2")
	private String isoAlp2;

	@SerializedName("iso_alp3")
	private String isoAlp3;

}
