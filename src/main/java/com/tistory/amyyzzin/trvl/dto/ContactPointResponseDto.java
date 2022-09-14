package com.tistory.amyyzzin.trvl.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ContactPointResponseDto extends BaseResponseDto{

	private List<ContactPointDto> data;
}
