package com.tistory.amyyzzin.trvl.dto;

import com.tistory.amyyzzin.trvl.domain.EmbassyHomepage;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EmbassyInfoResponseDto extends BaseResponseDto{

	private List<EmbassyInfoDto> data;
}
