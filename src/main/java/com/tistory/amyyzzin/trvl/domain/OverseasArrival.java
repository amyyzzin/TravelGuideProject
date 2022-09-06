package com.tistory.amyyzzin.trvl.domain;

import com.tistory.amyyzzin.trvl.dto.OverseasArrivalDto;
import com.tistory.amyyzzin.trvl.dto.SafetyListDto;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
public class OverseasArrival {

	@Id
	@GeneratedValue
	private Long id;

	// 대륙코드
	private String countryEngNm;

	// 영문 대륙명
	private String countryIsoAlp2;

	// 한글 대륙명
	private String countryNm;

	@Lob
	private String htmlOriginCn;

	// 영문 국가명
	private String noticeId;

	// ISO 2자리코드
	private String title;

	// 한글 국가명
	@Lob
	private String txtOriginCn;

	// 공지사항구분
	private String wrtDt;

	public static OverseasArrival of(OverseasArrivalDto overseasArrivalDto) {
		return OverseasArrival.builder()
			.countryEngNm(overseasArrivalDto.getCountryEngNm())
			.countryIsoAlp2(overseasArrivalDto.getCountryIsoAlp2())
			.countryNm(overseasArrivalDto.getCountryNm())
			.htmlOriginCn(overseasArrivalDto.getHtmlOriginCn())
			.noticeId(overseasArrivalDto.getNoticeId())
			.title(overseasArrivalDto.getTitle())
			.txtOriginCn(overseasArrivalDto.getTxtOriginCn())
			.wrtDt(overseasArrivalDto.getWrtDt())
			.build();
	}

}
