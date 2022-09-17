package com.tistory.amyyzzin.trvl.domain;

import com.tistory.amyyzzin.trvl.dto.CountryFlagDto;
import com.tistory.amyyzzin.trvl.dto.NoticeListDto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Text;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeList {

	@Id
	@GeneratedValue
	private Long id;

	private String listId;

	private String fileDownloadUrl;

	@Lob
	private String title;

	@Lob
	private String txtOriginCn;

	private String writtenDt;


	public static NoticeList of(NoticeListDto countryFlagDto) {
		return NoticeList.builder()
			.listId(countryFlagDto.getListId())
			.fileDownloadUrl(countryFlagDto.getFileDownloadUrl())
			.title(countryFlagDto.getTitle())
			.txtOriginCn(countryFlagDto.getTxtOriginCn())
			.writtenDt(countryFlagDto.getWrittenDt())
			.build();
	}

}
