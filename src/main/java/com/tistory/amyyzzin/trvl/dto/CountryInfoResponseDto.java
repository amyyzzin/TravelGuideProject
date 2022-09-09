package com.tistory.amyyzzin.trvl.dto;

import java.util.List;
import lombok.Data;

@Data
public class CountryInfoResponseDto extends BaseResponseDto {

    private List<CountryInfoDto> data;

}
