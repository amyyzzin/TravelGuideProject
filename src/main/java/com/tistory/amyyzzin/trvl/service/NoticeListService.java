package com.tistory.amyyzzin.trvl.service;

import com.tistory.amyyzzin.trvl.domain.CountryFlag;
import com.tistory.amyyzzin.trvl.domain.NoticeList;
import com.tistory.amyyzzin.trvl.dto.CountryFlagDto;
import com.tistory.amyyzzin.trvl.dto.CountryFlagResponseDto;
import com.tistory.amyyzzin.trvl.dto.NoticeListDto;
import com.tistory.amyyzzin.trvl.dto.NoticeListResponseDto;
import com.tistory.amyyzzin.trvl.repository.NoticeListRepository;
import com.tistory.amyyzzin.trvl.util.ApiUtil;
import java.io.IOException;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeListService {

	private final ApiUtil apiUtil;

	private final NoticeListRepository noticeListRepository;

	@PostConstruct
	public void init() throws IOException {
		if (noticeListRepository.count() > 0) {
			return;
		}

		insert(apiUtil.callNoticeListApi());
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
