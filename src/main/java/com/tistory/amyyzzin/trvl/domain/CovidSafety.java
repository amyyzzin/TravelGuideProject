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
import org.jsoup.Jsoup;

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
	@Lob
	private String fileDownloadUrl;

	// 파일경로
	@Lob
	private String filePath;

	// HTML원본 내용
	@Lob
	private String htmlOriginCn;

	// 안전공지 ID
	private String sftyNoticeId;

	// 제목
	@Lob
	private String title;

	//원본내용
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
			.htmlOriginCn(Jsoup.parse(covidSafetyDto.getHtmlOriginCn()).text().replace("\uFEFF", ""))
			.sftyNoticeId(covidSafetyDto.getSftyNoticeId())
			.title(covidSafetyDto.getTitle())
			.txtOriginCn(Jsoup.parse(covidSafetyDto.getTxtOriginCn()).text().replace("\uFEFF", ""))
			.wrtDt(covidSafetyDto.getWrtDt())
			.build();
	}

}
