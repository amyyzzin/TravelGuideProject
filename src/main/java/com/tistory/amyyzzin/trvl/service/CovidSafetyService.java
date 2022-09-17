package com.tistory.amyyzzin.trvl.service;

import com.tistory.amyyzzin.trvl.domain.CovidSafety;
import com.tistory.amyyzzin.trvl.dto.CovidSafetyDto;
import com.tistory.amyyzzin.trvl.dto.CovidSafetyResponseDto;
import com.tistory.amyyzzin.trvl.repository.CovidSafetyRepository;
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
public class CovidSafetyService {

    private final GenericApiUtil genericApiUtil;

    private final CovidSafetyRepository covidSafetyRepository;

    @Value("${open.api.covidSafety}")
    String covidSafetyUrl;

    @PostConstruct
    public void init() throws IOException, InterruptedException {
        if (covidSafetyRepository.count() > 0) {
            return;
        }

        for (int i = 0; i < 3; i++) {
            try {
                insert((CovidSafetyResponseDto) genericApiUtil.callJsonApi(covidSafetyUrl,
                    CovidSafetyResponseDto.class, "500"));
                break;
            } catch (Exception e) {
                log.error("[CovidSafetyService init] ERROR {}", e.getMessage());
                Thread.sleep(2000);
            }
        }
        Thread.sleep(2000);
    }

    public void insert(CovidSafetyResponseDto covidSafetyResponseDto) {
        if (covidSafetyResponseDto == null) {
            return;
        }

        log.info("[CovidSafetyDto] {}", covidSafetyResponseDto);

        for (CovidSafetyDto covidSafetyDto : covidSafetyResponseDto.getData()) {
            try {
                covidSafetyRepository.save(CovidSafety.of(covidSafetyDto));
            } catch (Exception e) {
                log.error("[CovidSafety.insert] ERROR {}", e.getMessage());
            }
        }

    }
}
