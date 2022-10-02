package com.tistory.amyyzzin.trvl.service;

import com.tistory.amyyzzin.trvl.domain.EmbassyInfo;
import com.tistory.amyyzzin.trvl.dto.EmbassyInfoDto;
import com.tistory.amyyzzin.trvl.dto.EmbassyInfoResponseDto;
import com.tistory.amyyzzin.trvl.exception.OpenApiException;
import com.tistory.amyyzzin.trvl.repository.EmbassyInfoRepository;
import com.tistory.amyyzzin.trvl.util.GenericApiUtil;
import java.io.IOException;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ExtendedBeanInfoFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmbassyInfoService extends AbstractService {

    private final GenericApiUtil genericApiUtil;

    private final EmbassyInfoRepository embassyInfoRepository;

    @Value("${open.api.embassyInfo}")
    String embassyInfoUrl;

    @Override
    public void upsert() throws IOException {

        if (embassyInfoRepository.count() > 0) {
            return;
        }

        EmbassyInfoResponseDto embassyInfoResponseDto =
            (EmbassyInfoResponseDto) genericApiUtil.callJsonApi(embassyInfoUrl,
                EmbassyInfoResponseDto.class, "500");

        log.info("[embassyInfoDto] {}", embassyInfoResponseDto);

        for (EmbassyInfoDto embassyInfoDto : embassyInfoResponseDto.getData()) {
            try {
                embassyInfoRepository.save(EmbassyInfo.of(embassyInfoDto));
            } catch (Exception e) {
                log.error("[OverseasArrival.insert] ERROR {}", e.getMessage());
            }
        }
    }
}
