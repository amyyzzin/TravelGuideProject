package com.tistory.amyyzzin.trvl.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.tistory.amyyzzin.trvl.dto.StandardCodeDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StandardCode {

	@Id
	@GeneratedValue
	private Long id;

	// 국가영문명
	private String countryEngNm;

	// 국가명
	private String countryNm;

	// ISO 숫자코드
	private String isoNum;

	// ISO 2자리코드
	private String isoAlp2;

	// ISO 3자리코드
	private String isoAlp3;

	public static StandardCode of(StandardCodeDto standardCodeDto) {
		return StandardCode.builder()
			.countryEngNm(standardCodeDto.getCountryEngNm())
			.countryNm(standardCodeDto.getCountryNm())
			.isoNum(standardCodeDto.getIsoNum())
			.isoAlp2(standardCodeDto.getIsoAlp2())
			.isoAlp3(standardCodeDto.getIsoAlp3())
			.build();
	}
}
