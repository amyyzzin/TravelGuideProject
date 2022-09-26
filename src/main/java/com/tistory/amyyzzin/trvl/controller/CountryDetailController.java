package com.tistory.amyyzzin.trvl.controller;

import com.tistory.amyyzzin.trvl.constant.IsoConstant;
import com.tistory.amyyzzin.trvl.domain.CountryBasicInfo;
import com.tistory.amyyzzin.trvl.domain.CountryFlag;
import com.tistory.amyyzzin.trvl.domain.CountryInfo;
import com.tistory.amyyzzin.trvl.domain.NoticeList;
import com.tistory.amyyzzin.trvl.domain.StandardCode;
import com.tistory.amyyzzin.trvl.dto.CountryDetailDto;
import com.tistory.amyyzzin.trvl.dto.GoogleModalDto;
import com.tistory.amyyzzin.trvl.dto.SafetyListDto;
import com.tistory.amyyzzin.trvl.service.CountryBasicInfoService;
import com.tistory.amyyzzin.trvl.service.CountryFlagService;
import com.tistory.amyyzzin.trvl.service.CountryInfoService;
import com.tistory.amyyzzin.trvl.service.StandardCodeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CountryDetailController {

	private final StandardCodeService standardCodeService;

	private final CountryBasicInfoService countryBasicInfoService;

	@ApiOperation(value = "설명", notes = "이것은 노트")
	@GetMapping("/index/detail/{id}")
	public String index(@PathVariable String id, Model model) {

		model.addAttribute("basicInfo", countryBasicInfoService.findByIso3Code(id));

		return "index/detail";
	}
}
