package com.tistory.amyyzzin.trvl.service;

import com.tistory.amyyzzin.trvl.domain.ContactPoint;
import com.tistory.amyyzzin.trvl.domain.TravelAlarm;
import com.tistory.amyyzzin.trvl.dto.TravelAlarmDto;
import com.tistory.amyyzzin.trvl.dto.TravelAlarmResponseDto;
import com.tistory.amyyzzin.trvl.exception.OpenApiException;
import com.tistory.amyyzzin.trvl.repository.TravelAlarmRepository;
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
public class TravelAlarmService extends AbstractService {

    private final GenericApiUtil genericApiUtil;

    private final TravelAlarmRepository travelAlarmRepository;

    @Value("${open.api.travelAlarm}")
    String travelAlarmUrl;

    @Override
    @Scheduled(cron = "${scheduler.scrap.getAPI}")
    public void upsert() throws IOException {

        if (travelAlarmRepository.count() > 0) {
            return;
        }

        TravelAlarmResponseDto travelAlarmResponseDto =
            (TravelAlarmResponseDto) genericApiUtil.callJsonApi(travelAlarmUrl,
                TravelAlarmResponseDto.class, "500");

        log.info("[travelAlarmResponseDto] {}", travelAlarmResponseDto);

        for (TravelAlarmDto travelAlarmDto : travelAlarmResponseDto.getData()) {
            try {
                travelAlarmRepository.save(TravelAlarm.of(travelAlarmDto));
            } catch (Exception e) {
                log.error("[TravelAlarm.insert] ERROR {}", e.getMessage());
            }
        }
    }

    public TravelAlarm findByIso2Code(String countryIsoAlp2) {
        return travelAlarmRepository.findByCountryIsoAlp2(countryIsoAlp2).orElse(null);
    }
}
