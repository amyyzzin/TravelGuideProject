package com.tistory.amyyzzin.trvl.service;

import com.tistory.amyyzzin.trvl.domain.SafetyList;
import com.tistory.amyyzzin.trvl.dto.SafetyListDto;
import com.tistory.amyyzzin.trvl.dto.SafetyListResponseDto;
import com.tistory.amyyzzin.trvl.repository.SafetyListRepository;
import com.tistory.amyyzzin.trvl.util.GenericApiUtil;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
    public void init() throws IOException {
        if (safetyListRepository.count() > 0) {
            return;
        }

        insert((SafetyListResponseDto) genericApiUtil.callJsonApi(safetyListUrl,
            SafetyListResponseDto.class));
    }

    public void insert(SafetyListResponseDto safetyListResponseDto) {
        if (safetyListResponseDto == null) {
            return;
        }

        log.info("[SafetyListDto] {}", safetyListResponseDto);

        for (SafetyListDto safetyListDto : safetyListResponseDto.getData()) {
            try {
                safetyListRepository.save(SafetyList.of(safetyListDto));
            } catch (Exception e) {
                log.error("[SafetyList.insert] ERROR {}", e.getMessage());
            }
        }
    }

    public List<SafetyList> getSafetyList() {
        return safetyListRepository.findTop5ByOrderByWrtDtDesc();
    }

}
