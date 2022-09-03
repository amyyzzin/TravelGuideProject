package com.tistory.amyyzzin.trvl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.tistory.amyyzzin.trvl.service.CountryFlagService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class GoogleMapController {

	private final CountryFlagService countryFlagService;

	public GoogleMapController(CountryFlagService countryFlagService) {
		this.countryFlagService = countryFlagService;
	}

	@GetMapping("/")
	public String index() {
		return "index/index";
	}

}
