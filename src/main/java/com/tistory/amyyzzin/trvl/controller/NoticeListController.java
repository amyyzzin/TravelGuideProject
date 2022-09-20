package com.tistory.amyyzzin.trvl.controller;

import com.tistory.amyyzzin.trvl.config.PageConfig;
import com.tistory.amyyzzin.trvl.domain.NoticeList;
import com.tistory.amyyzzin.trvl.service.CountryFlagService;
import com.tistory.amyyzzin.trvl.service.NoticeListService;
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
public class NoticeListController extends BaseController {
    private final NoticeListService noticeListService;


    @GetMapping("index/noticeListDetail")
    public String index(Model model, PageConfig parameter) {

        parameter.init();

        PageRequest pageRequest = PageRequest.of((int) parameter.getPageIndex() - 1,
            (int) parameter.getPageSize());

        Page<NoticeList> noticeLists = noticeListService.getAllNoticeList(pageRequest);

        Long totalCount = Long.valueOf(noticeListService.getListAll().size());
        String queryString = parameter.getQueryString();
        String pagerHtml = getPagerHtml(totalCount, parameter.getPageSize(),
            parameter.getPageIndex(), queryString);

        model.addAttribute("allNoticeList", noticeLists);

        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);
        model.addAttribute("pageIndex", parameter.getPageIndex() - 1);

        return "index/noticeListDetail";
    }
}
