package com.tistory.amyyzzin.trvl.service;

import com.tistory.amyyzzin.trvl.domain.OverseasArrival;
import com.tistory.amyyzzin.trvl.dto.OverseasArrivalDto;
import com.tistory.amyyzzin.trvl.dto.OverseasArrivalResponseDto;
import com.tistory.amyyzzin.trvl.exception.OpenApiException;
import com.tistory.amyyzzin.trvl.repository.OverseasArrivalRepository;
import com.tistory.amyyzzin.trvl.util.GenericApiUtil;
import java.io.IOException;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OverseasArrivalService extends AbstractService {

    private final GenericApiUtil genericApiUtil;

    private final OverseasArrivalRepository overseasArrivalRepository;

    @Value("${open.api.overSeasArrival}")
    String overSeasArrivalUrl;

    @Override
    @Scheduled(cron = "${scheduler.scrap.getAPI}")
    public void upsert() throws IOException {

        if (overseasArrivalRepository.count() > 0) {
            return;
        }

        OverseasArrivalResponseDto overseasArrivalResponseDto =
            (OverseasArrivalResponseDto) genericApiUtil.callJsonApi(overSeasArrivalUrl,
                OverseasArrivalResponseDto.class, "500");

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
