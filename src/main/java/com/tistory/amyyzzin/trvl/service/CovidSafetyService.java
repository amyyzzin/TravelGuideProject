package com.tistory.amyyzzin.trvl.service;

import com.tistory.amyyzzin.trvl.domain.CovidSafety;
import com.tistory.amyyzzin.trvl.dto.CovidSafetyDto;
import com.tistory.amyyzzin.trvl.dto.CovidSafetyResponseDto;
import com.tistory.amyyzzin.trvl.repository.CovidSafetyRepository;
import com.tistory.amyyzzin.trvl.util.GenericApiUtil;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CovidSafetyService extends AbstractService {

    private final GenericApiUtil genericApiUtil;

    private final CovidSafetyRepository covidSafetyRepository;

    @Value("${open.api.covidSafety}")
    String covidSafetyUrl;

    @Override
    public void upsert() throws IOException {

        if (covidSafetyRepository.count() > 0) {
            return;
        }
        CovidSafetyResponseDto covidSafetyResponseDto =
            (CovidSafetyResponseDto) genericApiUtil.callJsonApi(covidSafetyUrl,
                CovidSafetyResponseDto.class, "200");

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
