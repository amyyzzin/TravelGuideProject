package com.tistory.amyyzzin.trvl.service;

import com.tistory.amyyzzin.trvl.constant.IsoConstant;
import com.tistory.amyyzzin.trvl.domain.TravelWarning;
import com.tistory.amyyzzin.trvl.dto.TravelWarningDto;
import com.tistory.amyyzzin.trvl.dto.TravelWarningResponseDto;
import com.tistory.amyyzzin.trvl.repository.TravelWarningRepository;
import com.tistory.amyyzzin.trvl.util.XmlApiUtil;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TravelWarningService extends AbstractService {

    private final XmlApiUtil xmlApiUtil;

    private final TravelWarningRepository travelWarningRepository;

    @Override
    public void upsert() throws IOException {

        if (travelWarningRepository.count() > 0) {
            return;
        }

        TravelWarningResponseDto travelWarningResponseDto =
            (TravelWarningResponseDto) xmlApiUtil.callTravelWarningApi();

        for (TravelWarningDto travelWarningDto : travelWarningResponseDto.getData()) {
            try {
                travelWarningDto.setIso2Code(
                    IsoConstant.convertCountryEngNm2Iso2(travelWarningDto.getCountryEnName()));
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
