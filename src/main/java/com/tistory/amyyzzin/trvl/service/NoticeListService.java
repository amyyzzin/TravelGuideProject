package com.tistory.amyyzzin.trvl.service;

import com.tistory.amyyzzin.trvl.domain.NoticeList;
import com.tistory.amyyzzin.trvl.dto.NoticeListDto;
import com.tistory.amyyzzin.trvl.dto.NoticeListResponseDto;
import com.tistory.amyyzzin.trvl.exception.OpenApiException;
import com.tistory.amyyzzin.trvl.repository.NoticeListRepository;
import com.tistory.amyyzzin.trvl.util.GenericApiUtil;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

        boolean openApiError = true;

        for (int i = 0; i < 3; i++) {
            try {
                upsert((NoticeListResponseDto) genericApiUtil.callJsonApi(noticeUrl,
                    NoticeListResponseDto.class, "50"));
                openApiError = false;

                break;
            } catch (Exception e) {
                log.error("[NoticeListService init] ERROR {}", e.getMessage());
                Thread.sleep(2000);
            }
        }

        if (openApiError) {
            throw new OpenApiException();
        }

        Thread.sleep(2000);
    }

    public void upsert(NoticeListResponseDto noticeListResponseDto) {
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

    public List<NoticeList> getNoticeList() {
        return noticeListRepository.findTop5ByOrderByWrittenDtDesc();
    }

    public List<NoticeList> getListAll() {
        return noticeListRepository.findAll();
    }

    public Page<NoticeList> getAllNoticeList(PageRequest pageRequest) {
        Pageable paging = PageRequest.of(0, 10);
        return noticeListRepository.findALlByOrderByWrittenDtDesc(pageRequest);
    }

    public NoticeListDto detail(String listId) {

        Optional<NoticeList> optionalMember = noticeListRepository.findByListId(listId);

        if (!optionalMember.isPresent()) {
            return null;
        }

        NoticeList noticeList = optionalMember.get();

        return NoticeListDto.of(noticeList);
    }

}
