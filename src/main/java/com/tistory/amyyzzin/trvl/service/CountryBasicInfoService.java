package com.tistory.amyyzzin.trvl.service;

import com.tistory.amyyzzin.trvl.domain.CountryBasicInfo;
import com.tistory.amyyzzin.trvl.dto.CountryBasicInfoDto;
import com.tistory.amyyzzin.trvl.dto.CountryBasicInfoResponseDto;
import com.tistory.amyyzzin.trvl.repository.CountryBasicInfoRepository;
import com.tistory.amyyzzin.trvl.util.ApiUtil;
import com.tistory.amyyzzin.trvl.util.Test;
import com.tistory.amyyzzin.trvl.util.XmlTest;
import java.io.IOException;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CountryBasicInfoService {

//    private XmlTest xmlTest;
//
//    private final CountryBasicInfoRepository countryBasicInfoRepository;
//
//    @PostConstruct
//    public void init() throws IOException {
//
//        if (countryBasicInfoRepository.count() > 0) {
//            return;
//        }
//
//        insert(XmlTest.callCountryBasicInfoApi());
//    }
//
//    public CountryBasicInfo findByCountryNm(String countryNm) {
//        return countryBasicInfoRepository.findByCountryNm(countryNm).orElse(null);
//    }
//
//    public void insert(CountryBasicInfoResponseDto countryBasicInfoResponseDto) {
//
//        if (countryBasicInfoResponseDto == null) {
//            return;
//        }
//
//        log.info("[countryBasicInfoDto] {}", countryBasicInfoResponseDto);
//
//        for (CountryBasicInfoDto countryBasicInfoDto : countryBasicInfoResponseDto.getData()) {
//            try {
//                countryBasicInfoRepository.save(CountryBasicInfo.of(countryBasicInfoDto));
//            } catch (Exception e) {
//                log.error("[CountryBasicInfo.insert] ERROR {}", e.getMessage());
//            }
//        }
//
//    }
}
