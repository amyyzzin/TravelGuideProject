package com.tistory.amyyzzin.trvl.controller;

import com.tistory.amyyzzin.trvl.config.PageConfig;
import com.tistory.amyyzzin.trvl.domain.SafetyList;
import com.tistory.amyyzzin.trvl.service.SafetyListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SafetyListController extends BaseController {
    private final SafetyListService safetyListService;

    @GetMapping("index/safetyDetailList")
    public String index(Model model, PageConfig parameter) {

        parameter.init();

        PageRequest pageRequest = PageRequest.of((int) parameter.getPageIndex() - 1,
            (int) parameter.getPageSize());

        Page<SafetyList> safetyLists = safetyListService.getSafetyPageAll(pageRequest);

        Long totalCount = Long.valueOf(safetyListService.getListAll().size());
        String queryString = parameter.getQueryString();
        String pagerHtml = getPagerHtml(totalCount, parameter.getPageSize(),
            parameter.getPageIndex(), queryString);

        model.addAttribute("safetyListMain", safetyListService.getMainSafetyList());
        model.addAttribute("getSafetyListAll", safetyLists);

        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);
        model.addAttribute("pageIndex", parameter.getPageIndex() - 1);

        return "index/safetyDetailList";
    }
}
