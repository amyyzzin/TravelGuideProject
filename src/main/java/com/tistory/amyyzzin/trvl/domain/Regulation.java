package com.tistory.amyyzzin.trvl.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.tistory.amyyzzin.trvl.dto.RegulationDto;

import javax.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Regulation {

	@Id
	private Long id;

	// 국가영문명
	private String countryEngNm;

	// 국가명
	private String countryNm;

	// ISO 숫자코드
	private String countryIsoAlp2;

	// 여권소지여부
	private String haveYn;

	// 일반여권 입국허가요건 여부
	private String gnrlPsptVisaYn;

	// 일반여권 입국허가요건 내용
	@Lob
	private String gnrlPsptVisaCn;

	// 관용여권 입국허가요건 여부
	private String ofclpsptVisaYn;

	// 관용여권 입국허가요건 내용
	@Lob
	private String ofclpsptVisaCn;

	// 외교관여권 입국허가요건 여부
	private String dplmtPsptVisaYn;

	// 외교관여권 입국허가요건 내용
	@Lob
	private String dplmtPsptVisaCn;

	// 무비자 입국근거내용
	private String nvisaEntryEvdcCn;

	// 비고
	@Lob
	private String remark;

	public static Regulation of(RegulationDto regulationDto) {

		return Regulation.builder()
			.id(Long.valueOf(regulationDto.getId()))
			.countryEngNm(regulationDto.getCountryEngNm())
			.countryNm(regulationDto.getCountryNm())
			.countryIsoAlp2(regulationDto.getCountryIsoAlp2())
			.haveYn(regulationDto.getHaveYn())
			.gnrlPsptVisaYn(regulationDto.getGnrlPsptVisaYn())
			.gnrlPsptVisaCn(regulationDto.getGnrlPsptVisaCn())
			.ofclpsptVisaYn(regulationDto.getOfclpsptVisaYn())
			.ofclpsptVisaCn(regulationDto.getOfclpsptVisaCn())
			.dplmtPsptVisaYn(regulationDto.getDplmtPsptVisaYn())
			.dplmtPsptVisaCn(regulationDto.getDplmtPsptVisaCn())
			.nvisaEntryEvdcCn(regulationDto.getNvisaEntryEvdcCn())
			.remark(regulationDto.getRemark())
			.build();
	}
}
