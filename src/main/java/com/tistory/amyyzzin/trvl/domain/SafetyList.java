package com.tistory.amyyzzin.trvl.domain;

import com.tistory.amyyzzin.trvl.dto.CountryFlagDto;
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
public class SafetyList {

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

	// 공지사항구분
	private String ctgyNm;

	// 파일다운로드url
	@Lob
	private String fileDownloadUrl;

	// 파일경로
	@Lob
	private String filePath;

	// 제목
	private String title;

	// 텍스트원본내용
	@Lob
	private String txtOriginCn;

	// 작성일
	private String wrtDt;

	private boolean isMainNotice;

	public static SafetyList of(SafetyListDto safetyListDto) {
		return SafetyList.builder()
			.continentCd(safetyListDto.getContinentCd())
			.continentEngNm(safetyListDto.getContinentEngNm())
			.continentNm(safetyListDto.getContinentNm())
			.countryEngNm(safetyListDto.getCountryEngNm())
			.countryIsoAlp2(safetyListDto.getCountryIsoAlp2())
			.countryNm(safetyListDto.getCountryNm())
			.ctgyNm(safetyListDto.getCtgyNm())
			.fileDownloadUrl(safetyListDto.getFileDownloadUrl())
			.filePath(safetyListDto.getFilePath())
			.title(Jsoup.parse(safetyListDto.getTitle()).text().replace("\uFEFF",""))
			.txtOriginCn(Jsoup.parse(safetyListDto.getTxtOriginCn()).text().replace("\uFEFF",""))
			.wrtDt(safetyListDto.getWrtDt())
			.build();
	}

}
