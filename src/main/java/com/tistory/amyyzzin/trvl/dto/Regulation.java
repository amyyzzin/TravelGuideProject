package com.tistory.amyyzzin.trvl.dto;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Regulation {

	private String id;

	@SerializedName("country_eng_nm")
	private String countryEngNm;

	@SerializedName("country_nm")
	private String countryNm;

	@SerializedName("country_iso_alp2")
	private String countryIsoAlp2;

	@SerializedName("have_yn")
	private String haveYn;

	@SerializedName("gnrl_pspt_visa_yn")
	private String gnrlPsptVisaYn;

	@SerializedName("gnrl_pspt_visa_cn")
	private String gnrlPsptVisaCn;

	@SerializedName("ofclpspt_visa_yn")
	private String ofclpsptVisaYn;

	@SerializedName("ofclpspt_visa_cn")
	private String ofclpsptVisaCn;

	@SerializedName("dplmt_pspt_visa_yn")
	private String dplmtPsptVisaYn;

	@SerializedName("dplmt_pspt_visa_cn")
	private String dplmtPsptVisaCn;

	@SerializedName("nvisa_entry_evdc_cn")
	private String nvisaEntryEvdcCn;

	private String remark;
}
