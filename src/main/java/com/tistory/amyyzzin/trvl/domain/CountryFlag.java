package com.tistory.amyyzzin.trvl.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.tistory.amyyzzin.trvl.dto.CountryFlagDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryFlag {

	@Id
	@GeneratedValue
	private Long id;

	// 국가영문명
	private String countryEngNm;

	// 국가명
	private String countryNm;

	// download_url
	private String downloadUrl;

	// ISO 2자리코드
	private String isoAlp2;

	// originFileNm
	private String originFileNm;

	public static CountryFlag of(CountryFlagDto countryFlagDto) {
		return CountryFlag.builder()
			.countryEngNm(countryFlagDto.getCountryEngNm())
			.countryNm(countryFlagDto.getCountryNm())
			.downloadUrl(countryFlagDto.getDownloadUrl())
			.isoAlp2(countryFlagDto.getIsoAlp2())
			.originFileNm(countryFlagDto.getOriginFileNm())
			.build();
	}

}
