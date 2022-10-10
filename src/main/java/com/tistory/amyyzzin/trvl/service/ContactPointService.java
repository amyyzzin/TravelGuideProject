package com.tistory.amyyzzin.trvl.service;

import com.tistory.amyyzzin.trvl.domain.ContactPoint;
import com.tistory.amyyzzin.trvl.dto.ContactPointDto;
import com.tistory.amyyzzin.trvl.dto.ContactPointResponseDto;
import com.tistory.amyyzzin.trvl.repository.ContactPointRepository;
import com.tistory.amyyzzin.trvl.util.GenericApiUtil;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContactPointService extends AbstractService {

    private final GenericApiUtil genericApiUtil;

    private final ContactPointRepository contactPointRepository;

    @Value("${open.api.contactPoint}")
    String contactPointUrl;

    @Override
    @Scheduled(cron = "${scheduler.scrap.getAPI}")
    public void upsert() throws IOException {

        if (contactPointRepository.count() > 0) {
            return;
        }

        ContactPointResponseDto contactPointResponseDto =
            (ContactPointResponseDto) genericApiUtil.callJsonApi(contactPointUrl,
                ContactPointResponseDto.class, "500");

        log.info("[contactPointDto] {}", contactPointResponseDto);

        for (ContactPointDto contactPointDto : contactPointResponseDto.getData()) {
            try {
                contactPointRepository.save(ContactPoint.of(contactPointDto));
            } catch (Exception e) {
                log.error("[ContactPoint.insert] ERROR {}", e.getMessage());
            }
        }
    }

    public ContactPoint findByIso2Code(String countryIsoAlp2) {
        return contactPointRepository.findByCountryIsoAlp2(countryIsoAlp2).orElse(null);
    }
}
