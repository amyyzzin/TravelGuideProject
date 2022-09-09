package com.tistory.amyyzzin.trvl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponseDto {

    private int currentCount;

    private String resultCode;

    private String resultMsg;

    private String numOfRows;

    private String pageNo;

    private String totalCount;

}
