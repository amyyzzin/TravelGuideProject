package com.tistory.amyyzzin.trvl.dto;

import java.util.ArrayList;
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
public class CountryBasicInfoResponseDto extends BaseResponseDto {

    private List<CountryBasicInfoDto> data = new ArrayList<>();

}
