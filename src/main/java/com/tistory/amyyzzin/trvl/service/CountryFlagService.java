package com.tistory.amyyzzin.trvl.service;

import com.tistory.amyyzzin.trvl.domain.CountryFlag;
import com.tistory.amyyzzin.trvl.dto.CountryFlagDto;
import com.tistory.amyyzzin.trvl.dto.CountryFlagResponseDto;
import com.tistory.amyyzzin.trvl.exception.OpenApiException;
import com.tistory.amyyzzin.trvl.repository.CountryFlagRepository;
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
public class CountryFlagService {

    private final GenericApiUtil genericApiUtil;

    private final CountryFlagRepository countryFlagRepository;

    @Value("${open.api.countryFlag}")
    String countryFlagUrl;

    @PostConstruct
    public void init() throws IOException, InterruptedException {
        if (countryFlagRepository.count() > 0) {
            return;
        }

        boolean openApiError = true;

        for (int i = 0; i < 3; i++) {
            try {
                upsert((CountryFlagResponseDto) genericApiUtil.callJsonApi(countryFlagUrl,
                    CountryFlagResponseDto.class, "500"));
                openApiError = false;

                break;
            } catch (Exception e ) {
                log.error("[CountryFlagService init] ERROR {}", e.getMessage());
                Thread.sleep(2000);
            }
        }

        if (openApiError) {
            throw new OpenApiException();
        }

        Thread.sleep(2000);
    }

    public CountryFlag findByIsoAlp2(String isoAlp2) {
        return countryFlagRepository.findByIsoAlp2(isoAlp2).orElse(null);
    }

    public void upsert(CountryFlagResponseDto countryFlagResponseDto) {
        if (countryFlagResponseDto == null) {
            return;
        }

        log.info("[countryFlagDto] {}", countryFlagResponseDto);

        for (CountryFlagDto countryFlagDto : countryFlagResponseDto.getData()) {
            try {
                countryFlagRepository.save(CountryFlag.of(countryFlagDto));
            } catch (Exception e) {
                log.error("[CountryFlag.insert] ERROR {}", e.getMessage());
            }
        }

    }
}
