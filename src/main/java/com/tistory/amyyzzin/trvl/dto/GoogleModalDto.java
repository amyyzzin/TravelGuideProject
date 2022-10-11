package com.tistory.amyyzzin.trvl.dto;

import javax.persistence.Lob;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GoogleModalDto {

	private String downloadUrl;

	//기후 내용
	private String climateCn;

	// 국가영문명
	private String countryEngNm;

	// ISO 2자리코드
	private String isoAlp2;

	// 국가명
	private String countryNm;

	//언어 내용
	private String langCn;

	//언어명
	private String langNm;

	//주요 도시 내용
	private String mainCityCn;

	//주요 민족 내용
	private String mainEthnicCn;

	//주요 언론 내용
	private String mscmctnCn;

	//종교 내용
	private String religionCn;

	//국가 위치
	private String countryIc;

	//국가 수도명
	private String countryCptNm;

	//국가 면적
	private String countryArea;

	//국가면적 출처
	private String countryAreaSrc;

	//국가면적 설명
	private String countryAreaComment;

	private String iso2code;

}
