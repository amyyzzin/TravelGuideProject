package com.tistory.amyyzzin.trvl.domain;

import com.tistory.amyyzzin.trvl.dto.CovidSafetyDto;
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
public class CovidSafety {

	@Id
	@GeneratedValue
	private Long id;

	// 대륙코드
	private String continentCd;

	// 영문 대륙명
	private String continentEngNm;

	// 한글 대륙명
	private String continentNm;

	// 영문 국가명
	private String countryEngNm;

	// ISO 2자리코드
	private String countryIsoAlp2;

	// 한글 국가명
	private String countryNm;

	// 파일다운로드 url
	private String fileDownloadUrl;

	// 파일경로
	private String filePath;

	// 제목
	@Lob
	private String htmlOriginCn;

	// 텍스트원본내용
	private String sftyNoticeId;

	// 작성일
	private String title;

	@Lob
	private String txtOriginCn;

	// 작성일
	private String wrtDt;


	public static CovidSafety of(CovidSafetyDto covidSafetyDto) {
		return CovidSafety.builder()
			.continentCd(covidSafetyDto.getContinentCd())
			.continentEngNm(covidSafetyDto.getContinentEngNm())
			.continentNm(covidSafetyDto.getContinentNm())
			.countryEngNm(covidSafetyDto.getCountryEngNm())
			.countryIsoAlp2(covidSafetyDto.getCountryIsoAlp2())
			.countryNm(covidSafetyDto.getCountryNm())
			.fileDownloadUrl(covidSafetyDto.getFileDownloadUrl())
			.filePath(covidSafetyDto.getFilePath())
			.htmlOriginCn(covidSafetyDto.getHtmlOriginCn())
			.sftyNoticeId(covidSafetyDto.getSftyNoticeId())
			.title(covidSafetyDto.getTitle())
			.txtOriginCn(covidSafetyDto.getTxtOriginCn())
			.wrtDt(covidSafetyDto.getWrtDt())
			.build();
	}

}
