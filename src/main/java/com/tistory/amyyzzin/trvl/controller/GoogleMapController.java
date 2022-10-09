package com.tistory.amyyzzin.trvl.controller;

import com.tistory.amyyzzin.trvl.service.CountryFlagService;
import com.tistory.amyyzzin.trvl.service.CountryInfoService;
import com.tistory.amyyzzin.trvl.service.NoticeListService;
import com.tistory.amyyzzin.trvl.service.SafetyListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class GoogleMapController extends BaseController {
    private final SafetyListService safetyListService;
    private final NoticeListService noticeListService;
    private final CountryInfoService countryInfoService;
    private final CountryFlagService countryFlagService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("safetyListMain", safetyListService.getMainSafetyList());
        model.addAttribute("safetyList", safetyListService.getSafetyList());
        model.addAttribute("noticeListMain", noticeListService.getNoticeList());

        model.addAttribute("countryInfo", countryInfoService.getCountryInfo());
        model.addAttribute("countryFlagService", countryFlagService.getCountryFlag());

        return "index/index";
    }
}
