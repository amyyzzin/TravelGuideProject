package com.tistory.amyyzzin.trvl.service;

import com.tistory.amyyzzin.trvl.domain.CountryInfo;
import com.tistory.amyyzzin.trvl.domain.SafetyList;
import com.tistory.amyyzzin.trvl.dto.CountryInfoDto;
import com.tistory.amyyzzin.trvl.dto.CountryInfoResponseDto;
import com.tistory.amyyzzin.trvl.repository.CountryInfoRepository;
import com.tistory.amyyzzin.trvl.util.GenericApiUtil;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CountryInfoService extends AbstractService {

    private final GenericApiUtil genericApiUtil;

    private final CountryInfoRepository countryInfoRepository;

    @Value("${open.api.countryInfo}")
    String countryInfoUrl;

    @Override
    @Scheduled(cron = "${scheduler.get.API}")
    public void upsert() throws IOException {

        if (countryInfoRepository.count() > 0) {
            return;
        }

        CountryInfoResponseDto countryInfoResponseDto =
            (CountryInfoResponseDto) genericApiUtil.callJsonApi(countryInfoUrl,
                CountryInfoResponseDto.class, "500");

        log.info("[countryInfoDto] {}", countryInfoResponseDto);

        for (CountryInfoDto countryInfoDto : countryInfoResponseDto.getData()) {
            try {
                countryInfoRepository.save(CountryInfo.of(countryInfoDto));
            } catch (Exception e) {
                log.error("[CountryInfo.insert] ERROR {}", e.getMessage());
            }
        }
    }

    public CountryInfo findByIsoAlp2(String isoAlp2) {
        return countryInfoRepository.findFirstByIsoAlp2(isoAlp2).orElse(null);
    }

    public List<CountryInfo> getCountryInfo() {
        return countryInfoRepository.findAll();
    }
}
