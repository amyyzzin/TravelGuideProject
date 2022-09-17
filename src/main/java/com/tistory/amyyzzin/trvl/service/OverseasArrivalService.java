package com.tistory.amyyzzin.trvl.service;

import com.tistory.amyyzzin.trvl.domain.OverseasArrival;
import com.tistory.amyyzzin.trvl.dto.OverseasArrivalDto;
import com.tistory.amyyzzin.trvl.dto.OverseasArrivalResponseDto;
import com.tistory.amyyzzin.trvl.repository.OverseasArrivalRepository;
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
public class OverseasArrivalService {

    private final GenericApiUtil genericApiUtil;

    private final OverseasArrivalRepository overseasArrivalRepository;

    @Value("${open.api.overSeasArrival}")
    String overSeasArrivalUrl;

    @PostConstruct
    public void init() throws IOException, InterruptedException {
        if (overseasArrivalRepository.count() > 0) {
            return;
        }

        for (int i = 0; i < 3; i++) {
            try {
                insert((OverseasArrivalResponseDto) genericApiUtil.callJsonApi(overSeasArrivalUrl,
                    OverseasArrivalResponseDto.class, "500"));
                break;
            } catch (Exception e) {
                log.error("[OverseasArrivalService init] ERROR {}", e.getMessage());
                Thread.sleep(2000);
            }
        }
        Thread.sleep(2000);
    }

    public void insert(OverseasArrivalResponseDto overseasArrivalResponseDto) {
        if (overseasArrivalResponseDto == null) {
            return;
        }

        log.info("[overseasArrivalDto] {}", overseasArrivalResponseDto);

        for (OverseasArrivalDto overseasArrivalDto : overseasArrivalResponseDto.getData()) {
            try {
                overseasArrivalRepository.save(OverseasArrival.of(overseasArrivalDto));
            } catch (Exception e) {
                log.error("[OverseasArrival.insert] ERROR {}", e.getMessage());
            }
        }

    }
}
