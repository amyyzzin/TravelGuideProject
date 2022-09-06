package com.tistory.amyyzzin.trvl.dto;

import java.util.List;
import lombok.Data;

@Data
public class CountryInfoResponseDto {

    private int currentCount;

    private String resultCode;

    private String resultMsg;

    private String numOfRows;

    private String pageNo;

    private String totalCount;

    private List<CountryInfoDto> data;

}