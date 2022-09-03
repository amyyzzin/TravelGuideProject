package com.tistory.amyyzzin.trvl.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.tistory.amyyzzin.trvl.dto.Regulation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegulationVO {

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
	private String gnrlPsptVisaCn;

	// 관용여권 입국허가요건 여부
	private String ofclpsptVisaYn;

	// 관용여권 입국허가요건 여부
	private String ofclpsptVisaCn;

	// 외교관여권 입국허가요건 여부
	private String dplmtPsptVisaYn;

	// 외교관여권 입국허가요건 내용
	private String dplmtPsptVisaCn;

	// 무비자 입국근거내용
	private String nvisaEntryEvdcCn;

	// 비고
	private String remark;

	public static RegulationVO of(Regulation regulation) {

		return RegulationVO.builder()
			.id(Long.valueOf(regulation.getId()))
			.countryEngNm(regulation.getCountryEngNm())
			.countryNm(regulation.getCountryNm())
			.countryIsoAlp2(regulation.getCountryIsoAlp2())
			.haveYn(regulation.getHaveYn())
			.gnrlPsptVisaYn(regulation.getGnrlPsptVisaYn())
			.gnrlPsptVisaCn(regulation.getGnrlPsptVisaCn())
			.ofclpsptVisaYn(regulation.getOfclpsptVisaYn())
			.ofclpsptVisaCn(regulation.getOfclpsptVisaCn())
			.dplmtPsptVisaYn(regulation.getDplmtPsptVisaYn())
			.dplmtPsptVisaCn(regulation.getDplmtPsptVisaCn())
			.nvisaEntryEvdcCn(regulation.getNvisaEntryEvdcCn())
			.remark(regulation.getRemark())
			.build();
	}
}
