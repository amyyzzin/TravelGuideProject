package com.tistory.amyyzzin.trvl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class GoogleMapController {

	@GetMapping("/")
	public String index() {
		return "index/index";
	}

}
