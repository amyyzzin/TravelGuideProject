package com.tistory.amyyzzin.trvl.service;

import com.tistory.amyyzzin.trvl.domain.ContactPoint;
import com.tistory.amyyzzin.trvl.domain.CountryBasicInfo;
import com.tistory.amyyzzin.trvl.dto.ContactPointDto;
import com.tistory.amyyzzin.trvl.dto.ContactPointResponseDto;
import com.tistory.amyyzzin.trvl.exception.OpenApiException;
import com.tistory.amyyzzin.trvl.repository.ContactPointRepository;
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
public class ContactPointService {

    private final GenericApiUtil genericApiUtil;

    private final ContactPointRepository contactPointRepository;

    @Value("${open.api.contactPoint}")
    String contactPointUrl;

    @PostConstruct
    public void init() throws IOException, InterruptedException {
        if (contactPointRepository.count() > 0) {
            return;
        }

        boolean openApiError = true;

        for (int i = 0; i < 3; i++) {
            try {
                upsert((ContactPointResponseDto) genericApiUtil.callJsonApi(contactPointUrl,
                    ContactPointResponseDto.class, "500"));
                openApiError = false;

                break;
            } catch (Exception e) {
                log.error("[ContactPointService init] ERROR {}", e.getMessage());

                Thread.sleep(2000);
            }
        }

        if (openApiError) {
            throw new OpenApiException();
        }

        Thread.sleep(2000);
    }

    public void upsert(ContactPointResponseDto contactPointResponseDto) {
        if (contactPointResponseDto == null) {
            return;
        }

        log.info("[contactPointResponseDto] {}", contactPointResponseDto);

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
