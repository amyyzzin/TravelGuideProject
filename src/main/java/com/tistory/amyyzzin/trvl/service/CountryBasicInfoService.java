package com.tistory.amyyzzin.trvl.service;

import com.tistory.amyyzzin.trvl.constant.IsoConstant;
import com.tistory.amyyzzin.trvl.domain.CountryBasicInfo;
import com.tistory.amyyzzin.trvl.dto.CountryBasicInfoDto;
import com.tistory.amyyzzin.trvl.dto.CountryBasicInfoResponseDto;
import com.tistory.amyyzzin.trvl.repository.CountryBasicInfoRepository;
import com.tistory.amyyzzin.trvl.util.XmlApiUtil;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CountryBasicInfoService extends AbstractService {

    private final XmlApiUtil xmlApiUtil;

    private final CountryBasicInfoRepository countryBasicInfoRepository;

    @Override
    @Scheduled(cron = "${scheduler.scrap.getAPI}")
    public void upsert() throws IOException {

        if (countryBasicInfoRepository.count() > 0) {
            return;
        }

        CountryBasicInfoResponseDto countryBasicInfoResponseDto =
            (CountryBasicInfoResponseDto) xmlApiUtil.callCountryBasicInfoApi();

        for (CountryBasicInfoDto countryBasicInfoDto : countryBasicInfoResponseDto.getData()) {
            try {
                countryBasicInfoDto.setIso2Code(
                    IsoConstant.convertCountryEngNm2Iso2(countryBasicInfoDto.getCountryEngNm()));
                countryBasicInfoRepository.save(CountryBasicInfo.of(countryBasicInfoDto));
            } catch (Exception e) {
                log.error("[CountryBasicInfo.insert] ERROR {}", e.getMessage());
            }
        }
    }

    public CountryBasicInfo findByIso2Code(String iso2Code) {
        return countryBasicInfoRepository.findByIso2Code(iso2Code).orElse(null);
    }
}