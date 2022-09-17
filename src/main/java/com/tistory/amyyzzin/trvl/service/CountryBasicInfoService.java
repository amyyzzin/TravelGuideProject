package com.tistory.amyyzzin.trvl.service;

import com.tistory.amyyzzin.trvl.domain.CountryBasicInfo;
import com.tistory.amyyzzin.trvl.dto.CountryBasicInfoDto;
import com.tistory.amyyzzin.trvl.dto.CountryBasicInfoResponseDto;
import com.tistory.amyyzzin.trvl.repository.CountryBasicInfoRepository;
import com.tistory.amyyzzin.trvl.util.XmlApiUtil;
import java.io.IOException;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CountryBasicInfoService {

    private final XmlApiUtil xmlApiUtil;

    private final CountryBasicInfoRepository countryBasicInfoRepository;

    @PostConstruct
    public void init() throws IOException, InterruptedException {
        if (countryBasicInfoRepository.count() > 0) {
            return;
        }

        for (int i = 0; i < 3; i++) {
            try {
                insert(xmlApiUtil.callCountryBasicInfoApi());
                break;
            }catch (Exception e) {
                log.error("[CountryBasicInfoService init] ERROR {}", e.getMessage());
                Thread.sleep(2000);
            }
        }
        Thread.sleep(2000);
    }

    public void insert(CountryBasicInfoResponseDto responseDto) {

        if (responseDto == null) {
            return;
        }

        log.info("[countryBasicInfoDto] {}", responseDto);

        for (CountryBasicInfoDto countryBasicInfoDto : responseDto.getData()) {
            try {
                countryBasicInfoRepository.save(CountryBasicInfo.of(countryBasicInfoDto));
            } catch (Exception e) {
                log.error("[CountryBasicInfo.insert] ERROR {}", e.getMessage());
            }
        }

    }
}