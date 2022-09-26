package com.tistory.amyyzzin.trvl.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GoogleModalDto {

	private String countryNm;

	private String countryEngNm;

	private String downloadUrl;

	private String basic;

	private String iso2code;

}
