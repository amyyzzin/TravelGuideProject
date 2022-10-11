package com.tistory.amyyzzin.trvl.service;

import com.tistory.amyyzzin.trvl.domain.ContactPoint;
import com.tistory.amyyzzin.trvl.domain.Regulation;
import com.tistory.amyyzzin.trvl.dto.RegulationDto;
import com.tistory.amyyzzin.trvl.dto.RegulationResponseDto;
import com.tistory.amyyzzin.trvl.exception.OpenApiException;
import com.tistory.amyyzzin.trvl.repository.RegulationRepository;
import com.tistory.amyyzzin.trvl.util.GenericApiUtil;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegulationService extends AbstractService {

    private final GenericApiUtil genericApiUtil;

    private final RegulationRepository regulationRepository;

    @Value("${open.api.regulation}")
    String regulationUrl;

    @Override
    @Scheduled(cron = "${scheduler.get.API}")
    public void upsert() throws IOException {

        if (regulationRepository.count() > 0) {
            return;
        }

        RegulationResponseDto regulationResponseDto =
            (RegulationResponseDto) genericApiUtil.callJsonApi(regulationUrl,
                    RegulationResponseDto.class, "500");

        for (RegulationDto regulationDto : regulationResponseDto.getData()) {
            try {
                Regulation regulation = regulationRepository.findById(Long.valueOf(
                    regulationDto.getId())).orElse(null);

                if (regulation == null) {
                    regulationRepository.save(Regulation.of(regulationDto));
                } else {
                    updateRegulationVO(regulationDto, regulation);
                    regulationRepository.save(regulation);
                }
            } catch (Exception e) {
                log.error("[RegulationService.insert] ERROR {}", e.getMessage());
            }
        }
    }

    private void updateRegulationVO(RegulationDto regulationDto, Regulation regulation) {
        regulation.setCountryEngNm(regulationDto.getCountryEngNm());
        regulation.setCountryNm(regulationDto.getCountryNm());
        regulation.setCountryIsoAlp2(regulationDto.getCountryIsoAlp2());
        regulation.setHaveYn(regulationDto.getHaveYn());
        regulation.setGnrlPsptVisaYn(regulationDto.getGnrlPsptVisaYn());
        regulation.setGnrlPsptVisaCn(regulationDto.getGnrlPsptVisaCn());
        regulation.setOfclpsptVisaYn(regulationDto.getOfclpsptVisaYn());
        regulation.setOfclpsptVisaCn(regulationDto.getOfclpsptVisaCn());
        regulation.setDplmtPsptVisaYn(regulationDto.getDplmtPsptVisaYn());
        regulation.setDplmtPsptVisaCn(regulationDto.getDplmtPsptVisaCn());
        regulation.setNvisaEntryEvdcCn(regulationDto.getNvisaEntryEvdcCn());
        regulation.setRemark(regulationDto.getRemark());
    }

    public Regulation findByIso2Code(String countryIsoAlp2) {
        return regulationRepository.findByCountryIsoAlp2(countryIsoAlp2).orElse(null);
    }
}
