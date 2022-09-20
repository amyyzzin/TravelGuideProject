package com.tistory.amyyzzin.trvl.service;

import com.tistory.amyyzzin.trvl.domain.SafetyList;
import com.tistory.amyyzzin.trvl.dto.SafetyListDto;
import com.tistory.amyyzzin.trvl.dto.SafetyListResponseDto;
import com.tistory.amyyzzin.trvl.repository.SafetyListRepository;
import com.tistory.amyyzzin.trvl.util.GenericApiUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
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
public class SafetyListService {

    private final GenericApiUtil genericApiUtil;

    private final SafetyListRepository safetyListRepository;

    @Value("${open.api.safetyList}")
    String safetyListUrl;

    @PostConstruct
    public void init() throws IOException, InterruptedException {
        if (safetyListRepository.count() > 0) {
            return;
        }

        for (int i = 0; i < 3; i++) {
            try {
                insert((SafetyListResponseDto) genericApiUtil.callJsonApi(safetyListUrl,
                    SafetyListResponseDto.class, "100"));
                break;
            } catch (Exception e) {
                log.error("[SafetyListService init] ERROR {}", e.getMessage());
                Thread.sleep(2000);
            }
        }
        Thread.sleep(2000);
    }

    public void insert(SafetyListResponseDto safetyListResponseDto) {
        if (safetyListResponseDto == null) {
            return;
        }

        log.info("[SafetyListDto] {}", safetyListResponseDto);

        for (SafetyListDto safetyListDto : safetyListResponseDto.getData()) {
            try {
                SafetyList safetyList = SafetyList.of(safetyListDto);
                safetyList.setMainNotice(safetyListDto.getTitle().startsWith("<font"));

                safetyListRepository.save(safetyList);
            } catch (Exception e) {
                log.error("[SafetyList.insert] ERROR {}", e.getMessage());
            }
        }
    }

    public List<SafetyList> getMainSafetyList() {
        return safetyListRepository.findAllByIsMainNoticeIsTrueOrderByWrtDtDesc();
    }

    public List<SafetyList> getSafetyList() {
        return safetyListRepository.findTop3ByIsMainNoticeIsFalseOrderByWrtDtDesc();
    }

    public List<SafetyList> getListAll() {
        return safetyListRepository.findAll();
    }

    public Page<SafetyList> getSafetyPageAll(PageRequest pageRequest) {
//        Pageable paging = PageRequest.of(0, 10);
        return safetyListRepository.findAllByIsMainNoticeIsFalseOrderByWrtDtDesc(pageRequest);
    }
}

