package com.tistory.amyyzzin.trvl.controller;

import com.tistory.amyyzzin.trvl.constant.IsoConstant;
import com.tistory.amyyzzin.trvl.domain.AccidentList;
import com.tistory.amyyzzin.trvl.domain.CountryBasicInfo;
import com.tistory.amyyzzin.trvl.domain.CountryFlag;
import com.tistory.amyyzzin.trvl.domain.CountryInfo;
import com.tistory.amyyzzin.trvl.domain.NoticeList;
import com.tistory.amyyzzin.trvl.domain.StandardCode;
import com.tistory.amyyzzin.trvl.domain.TravelAlarm;
import com.tistory.amyyzzin.trvl.dto.CountryDetailDto;
import com.tistory.amyyzzin.trvl.dto.GoogleModalDto;
import com.tistory.amyyzzin.trvl.dto.SafetyListDto;
import com.tistory.amyyzzin.trvl.service.AccidentListService;
import com.tistory.amyyzzin.trvl.service.ContactPointService;
import com.tistory.amyyzzin.trvl.service.CountryBasicInfoService;
import com.tistory.amyyzzin.trvl.service.CountryFlagService;
import com.tistory.amyyzzin.trvl.service.CountryInfoService;
import com.tistory.amyyzzin.trvl.service.EmbassyHomepageService;
import com.tistory.amyyzzin.trvl.service.NoticeListService;
import com.tistory.amyyzzin.trvl.service.RegulationService;
import com.tistory.amyyzzin.trvl.service.SafetyListService;
import com.tistory.amyyzzin.trvl.service.StandardCodeService;
import com.tistory.amyyzzin.trvl.service.TravelAlarmService;
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

	private final CountryFlagService countryFlagService;
	private final CountryBasicInfoService countryBasicInfoService;
	private final CountryInfoService countryInfoService;
	private final AccidentListService accidentListService;
	private final ContactPointService contactPointService;
	private final SafetyListService safetyListService;
	private final NoticeListService noticeListService;

	private final TravelAlarmService travelAlarmService;
	private final RegulationService regulationService;
	private final EmbassyHomepageService embassyHomepageService;


	@ApiOperation(value = "설명", notes = "이것은 노트")
	@GetMapping("/index/detail/{id}") // iso2Code
	public String index(@PathVariable String id, Model model) {

		model.addAttribute("countryNm", standardCodeService.findByIsoAlp2(id));

		model.addAttribute("countryFlag", countryFlagService.findByIsoAlp2(id));

		model.addAttribute("safetyListMain", safetyListService.getMainSafetyList());
		model.addAttribute("safetyCountryList", safetyListService.getCountrySafetyList(id));
		model.addAttribute("noticeListMain", noticeListService.getNoticeList());
		model.addAttribute("noticeListMain", noticeListService.getNoticeList());

		model.addAttribute("basicInfo", countryBasicInfoService.findByIso2Code(id));
		model.addAttribute("accidentList", accidentListService.findByIso2Code(id));
		model.addAttribute("contactPoint", contactPointService.findByIso2Code(id));
		model.addAttribute("travelAlarm", travelAlarmService.findByIso2Code(id));
		model.addAttribute("regulation", regulationService.findByIso2Code(id));
		model.addAttribute("embassyHomepage", embassyHomepageService.getEmbassyHomepage(id));


		return "index/detail";
	}
}
