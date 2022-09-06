package com.tistory.amyyzzin.trvl.dto;

import com.tistory.amyyzzin.trvl.domain.EmbassyHomepage;
import java.util.List;
import lombok.Data;

@Data
public class EmbassyInfoResponseDto {

	private int currentCount;

	private String resultCode;

	private String resultMsg;

	private String numOfRows;

	private String pageNo;

	private String totalCount;

	private List<EmbassyInfoDto> data;
}
