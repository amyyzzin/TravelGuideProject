package com.tistory.amyyzzin.trvl.service;

import com.tistory.amyyzzin.trvl.domain.CountryInfo;
import com.tistory.amyyzzin.trvl.dto.CountryInfoDto;
import com.tistory.amyyzzin.trvl.dto.CountryInfoResponseDto;
import com.tistory.amyyzzin.trvl.exception.OpenApiException;
import com.tistory.amyyzzin.trvl.repository.CountryInfoRepository;
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
public class CountryInfoService {

    private final GenericApiUtil apiUtil;

    private final CountryInfoRepository countryInfoRepository;

    @Value("${open.api.countryInfo}")
    String countryInfoUrl;

    @PostConstruct
    public void init() throws IOException, InterruptedException {
        if (countryInfoRepository.count() > 0) {
            return;
        }

        boolean openApiError = true;

        for (int i = 0; i < 3; i++) {
            try {
                upsert((CountryInfoResponseDto) apiUtil.callJsonApi(countryInfoUrl,
                    CountryInfoResponseDto.class, "500"));
                openApiError = false;

                break;
            } catch (Exception e) {
                log.error("[CountryInfoService init] ERROR {}", e.getMessage());
                Thread.sleep(2000);
            }
        }

        if (openApiError) {
            throw new OpenApiException();
        }

        Thread.sleep(2000);
    }

    public CountryInfo findByIsoAlp2(String isoAlp2) {
        return countryInfoRepository.findFirstByIsoAlp2(isoAlp2).orElse(null);
    }

    public void upsert(CountryInfoResponseDto countryInfoResponseDto) {
        if (countryInfoResponseDto == null) {
            return;
        }

        log.info("[countryInfoDto] {}", countryInfoResponseDto);

        for (CountryInfoDto countryInfoDto : countryInfoResponseDto.getData()) {
            try {
                countryInfoRepository.save(CountryInfo.of(countryInfoDto));
            } catch (Exception e) {
                log.error("[CountryInfo.insert] ERROR {}", e.getMessage());
            }
        }

    }
}
