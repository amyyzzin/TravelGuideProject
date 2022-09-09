package com.tistory.amyyzzin.trvl.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccidentListResponseDto {

//    private int currentCount;

    private String resultCode;

    private String resultMsg;

    private String numOfRows;

    private String pageNo;

    private String totalCount;

    private List<AccidentListDto> data = new ArrayList<>();

}
