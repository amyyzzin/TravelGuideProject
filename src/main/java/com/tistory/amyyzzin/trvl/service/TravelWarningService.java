package com.tistory.amyyzzin.trvl.service;

import com.tistory.amyyzzin.trvl.constant.IsoConstant;
import com.tistory.amyyzzin.trvl.domain.AccidentList;
import com.tistory.amyyzzin.trvl.domain.TravelWarning;
import com.tistory.amyyzzin.trvl.dto.AccidentListDto;
import com.tistory.amyyzzin.trvl.dto.AccidentListResponseDto;
import com.tistory.amyyzzin.trvl.dto.TravelWarningDto;
import com.tistory.amyyzzin.trvl.dto.TravelWarningResponseDto;
import com.tistory.amyyzzin.trvl.exception.OpenApiException;
import com.tistory.amyyzzin.trvl.repository.TravelWarningRepository;
import com.tistory.amyyzzin.trvl.util.XmlApiUtil;
import java.io.IOException;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TravelWarningService {

    private final XmlApiUtil xmlApiUtil;

    private final TravelWarningRepository travelWarningRepository;

    @PostConstruct
    public void init() throws IOException, InterruptedException {
        if (travelWarningRepository.count() > 0) {
            return;
        }

        boolean openApiError = true;

        /**
         * 세번 시도
         */
        for (int i = 0; i < 3; i++) {
            try {
                upsert(xmlApiUtil.callTravelWarningApi());
                openApiError = false;

                break;
            } catch (Exception e) {
                log.error("[TravelWarningService init] ERROR {}", e.getMessage());
                Thread.sleep(2000);
            }
        }

        if (openApiError) {
            throw new OpenApiException();
        }

        Thread.sleep(2000);
    }

    public void upsert(TravelWarningResponseDto responseDto) {

        if (responseDto == null) {
            return;
        }

        log.info("[TravelWarningDto] {}", responseDto);

        for (TravelWarningDto travelWarningDto : responseDto.getData()) {
            try {
                travelWarningDto.setIso2Code(IsoConstant.convertCountryEngNm2Iso2(travelWarningDto.getCountryEnName()));
                travelWarningRepository.save(TravelWarning.of(travelWarningDto));
            } catch (Exception e) {
                log.error("[TravelWarning.insert] ERROR {}", e.getMessage());
            }
        }
    }

    public TravelWarning findByIso2Code(String iso2Code) {
        return travelWarningRepository.findByIso2Code(iso2Code).orElse(null);
    }
}
