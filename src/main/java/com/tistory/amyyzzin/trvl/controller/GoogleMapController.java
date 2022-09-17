package com.tistory.amyyzzin.trvl.controller;

import com.tistory.amyyzzin.trvl.service.CountryFlagService;
import com.tistory.amyyzzin.trvl.service.SafetyListService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class GoogleMapController {

    private final CountryFlagService countryFlagService;
    private final SafetyListService safetyListService;


    @GetMapping("/")
    public String index(Model model) {
//        model.addAttribute("safetyListMain", );
        model.addAttribute("safetyList", safetyListService.getSafetyList());

        return "index/index";
    }
}
