package com.tistory.amyyzzin.trvl.service;

import com.tistory.amyyzzin.trvl.domain.CountryFlag;
import com.tistory.amyyzzin.trvl.dto.CountryFlagDto;
import com.tistory.amyyzzin.trvl.dto.CountryFlagResponseDto;
import com.tistory.amyyzzin.trvl.repository.CountryFlagRepository;
import com.tistory.amyyzzin.trvl.util.GenericApiUtil;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CountryFlagService extends AbstractService {

    private final GenericApiUtil genericApiUtil;

    private final CountryFlagRepository countryFlagRepository;

    @Value("${open.api.countryFlag}")
    String countryFlagUrl;

    @Override
    public void upsert() throws IOException {

        if (countryFlagRepository.count() > 0) {
            return;
        }

        CountryFlagResponseDto countryFlagResponseDto =
            (CountryFlagResponseDto) genericApiUtil.callJsonApi(countryFlagUrl,
                CountryFlagResponseDto.class, "500");

        for (CountryFlagDto countryFlagDto : countryFlagResponseDto.getData()) {
            try {
                countryFlagRepository.save(CountryFlag.of(countryFlagDto));
            } catch (Exception e) {
                log.error("[CountryFlag.insert] ERROR {}", e.getMessage());
            }
        }
    }

    public CountryFlag findByIsoAlp2(String isoAlp2) {
        return countryFlagRepository.findByIsoAlp2(isoAlp2).orElse(null);
    }
}
