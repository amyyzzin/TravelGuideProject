package com.tistory.amyyzzin.trvl.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountryDetailDto {

	private String countryNm;

	private String countryEngNm;

	private String downloadUrl;

	private String climateCn;

	private String langCn;

	private String langNm;

	private String mainCityCn;

	private String mainEthnicCn;

	private String mscmctnCn;

	private String religionCn;

	private String countryIc;

	private String countryCptNm;

	private String countryArea;

	private String countryAreaSrc;

	private String countryAreaComment;

	private String basic;

}
