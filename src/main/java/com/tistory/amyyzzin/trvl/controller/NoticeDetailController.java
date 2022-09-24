package com.tistory.amyyzzin.trvl.controller;

import com.tistory.amyyzzin.trvl.config.PageConfig;
import com.tistory.amyyzzin.trvl.domain.NoticeList;
import com.tistory.amyyzzin.trvl.dto.NoticeListDto;
import com.tistory.amyyzzin.trvl.service.NoticeListService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
@RequiredArgsConstructor
public class NoticeDetailController extends BaseController {
    private final NoticeListService noticeListService;

    @GetMapping("index/noticeDetail/{listId}")
    public String index(@PathVariable String listId, Model model, NoticeList id) {
        NoticeListDto noticeDetail = noticeListService.detail(id.getListId());

        model.addAttribute("notice", noticeDetail);

        return "index/noticeDetail";
    }
}
