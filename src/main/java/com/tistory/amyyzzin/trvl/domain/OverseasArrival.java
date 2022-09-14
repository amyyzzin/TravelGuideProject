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

	// 영문 국가명
	private String countryEngNm;

	// ISO 2자리 코드
	private String countryIsoAlp2;

	// 한글 국가명
	private String countryNm;

	// HTML 원본 내용
	@Lob
	private String htmlOriginCn;

	// 조치현황 ID
	private String noticeId;

	// 제목
	@Lob
	private String title;

	// 글 내용
	@Lob
	private String txtOriginCn;

	// 작성일
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
