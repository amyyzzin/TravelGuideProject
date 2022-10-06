package com.tistory.amyyzzin.trvl.service;

import com.tistory.amyyzzin.trvl.domain.NoticeList;
import com.tistory.amyyzzin.trvl.dto.NoticeListDto;
import com.tistory.amyyzzin.trvl.dto.NoticeListResponseDto;
import com.tistory.amyyzzin.trvl.repository.NoticeListRepository;
import com.tistory.amyyzzin.trvl.util.GenericApiUtil;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
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
public class NoticeListService extends AbstractService {

    private final GenericApiUtil genericApiUtil;

    private final NoticeListRepository noticeListRepository;

    @Value("${open.api.notice}")
    String noticeUrl;

    @Override
    public void upsert() throws IOException {

        if (noticeListRepository.count() > 0) {
            return;
        }

        NoticeListResponseDto noticeListResponseDto =
            (NoticeListResponseDto) genericApiUtil.callJsonApi(noticeUrl,
                NoticeListResponseDto.class, "50");

        log.info("[noticeListDto] {}", noticeListResponseDto);

        for (NoticeListDto noticeListDto : noticeListResponseDto.getData()) {
            try {
                NoticeList byListId = noticeListRepository.findByListId(
                    noticeListDto.getListId()).orElse(null);

                if (byListId == null) {
                    noticeListRepository.save(NoticeList.of(noticeListDto));
                } else {
                    updateNoticeList(noticeListDto, byListId);
                    noticeListRepository.save(byListId);
                }
            } catch (Exception e) {
                log.error("[NoticeList.insert] ERROR {}", e.getMessage());
            }
        }
    }

    private static void updateNoticeList(NoticeListDto noticeListDto, NoticeList byListId) {
        byListId.setFileDownloadUrl(noticeListDto.getFileDownloadUrl());
        byListId.setTxtOriginCn(noticeListDto.getTxtOriginCn());
        byListId.setTitle(noticeListDto.getTitle());
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
