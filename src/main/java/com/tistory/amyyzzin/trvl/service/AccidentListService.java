package com.tistory.amyyzzin.trvl.service;

import com.tistory.amyyzzin.trvl.domain.AccidentList;
import com.tistory.amyyzzin.trvl.dto.AccidentListDto;
import com.tistory.amyyzzin.trvl.dto.AccidentListResponseDto;
import com.tistory.amyyzzin.trvl.repository.AccidentListRepository;
import com.tistory.amyyzzin.trvl.util.XmlApiUtil;
import java.io.IOException;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccidentListService {

    private final XmlApiUtil xmlApiUtil;

    private final AccidentListRepository accidentListRepository;

    @PostConstruct
    public void init() throws IOException, InterruptedException {
        if (accidentListRepository.count() > 0) {
            return;
        }

        /**
         * 세번 시도
         */
        for (int i = 0; i < 3; i++) {
            try {
                insert(xmlApiUtil.callAccidentListApi());
                break;
            } catch (Exception e) {
                log.error("[AccidentListService init] ERROR {}", e.getMessage());
                Thread.sleep(2000);
            }
        }
        Thread.sleep(2000);
    }

    public void insert(AccidentListResponseDto responseDto) {

        if (responseDto == null) {
            return;
        }

        log.info("[AccidentListDto] {}", responseDto);

        for (AccidentListDto accidentListDto : responseDto.getData()) {
            try {
                accidentListRepository.save(AccidentList.of(accidentListDto));
            } catch (Exception e) {
                log.error("[AccidentList.insert] ERROR {}", e.getMessage());
            }
        }

    }
}
