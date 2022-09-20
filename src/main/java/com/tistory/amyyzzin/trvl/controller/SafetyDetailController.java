package com.tistory.amyyzzin.trvl.controller;

import com.tistory.amyyzzin.trvl.domain.NoticeList;
import com.tistory.amyyzzin.trvl.dto.NoticeListDto;
import com.tistory.amyyzzin.trvl.dto.SafetyListDto;
import com.tistory.amyyzzin.trvl.service.NoticeListService;
import com.tistory.amyyzzin.trvl.service.SafetyListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SafetyDetailController extends BaseController {
    private final SafetyListService safetyListService;

    @GetMapping("index/safetyDetail/{id}")
    public String index(@PathVariable Long id, Model model, NoticeList list) {
        SafetyListDto safetyListDto = safetyListService.detail(list.getId());

        model.addAttribute("safety", safetyListDto);

        return "index/safetyDetail";
    }

}
