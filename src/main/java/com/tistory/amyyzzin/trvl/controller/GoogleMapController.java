package com.tistory.amyyzzin.trvl.controller;

import com.tistory.amyyzzin.trvl.service.CountryFlagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class GoogleMapController {

    private final CountryFlagService countryFlagService;

    @GetMapping("/")
    public String index() {
        return "index/index";
    }
}
