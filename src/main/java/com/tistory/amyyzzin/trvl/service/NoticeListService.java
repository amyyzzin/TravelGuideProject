package com.tistory.amyyzzin.trvl.service;

import com.tistory.amyyzzin.trvl.domain.NoticeList;
import com.tistory.amyyzzin.trvl.dto.NoticeListDto;
import com.tistory.amyyzzin.trvl.dto.NoticeListResponseDto;
import com.tistory.amyyzzin.trvl.repository.NoticeListRepository;
import com.tistory.amyyzzin.trvl.util.GenericApiUtil;
import java.io.IOException;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeListService {

    private final GenericApiUtil genericApiUtil;

    private final NoticeListRepository noticeListRepository;

    @Value("${open.api.notice}")
    String noticeUrl;

    @PostConstruct
    public void init() throws IOException, InterruptedException {
        if (noticeListRepository.count() > 0) {
            return;
        }

        for (int i = 0; i < 3; i++) {
            try {
                insert((NoticeListResponseDto) genericApiUtil.callJsonApi(noticeUrl,
                    NoticeListResponseDto.class, "100"));
                break;
            } catch (Exception e) {
                log.error("[NoticeListService init] ERROR {}", e.getMessage());
                Thread.sleep(2000);
            }
        }
        Thread.sleep(2000);
    }

    public void insert(NoticeListResponseDto noticeListResponseDto) {
        if (noticeListResponseDto == null) {
            return;
        }

        log.info("[NoticeListDto] {}", noticeListResponseDto);

        for (NoticeListDto noticeListDto : noticeListResponseDto.getData()) {
            try {
                noticeListRepository.save(NoticeList.of(noticeListDto));
            } catch (Exception e) {
                log.error("[NoticeList.insert] ERROR {}", e.getMessage());
            }
        }

    }
}
