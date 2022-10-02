package com.tistory.amyyzzin.trvl.service;

import com.tistory.amyyzzin.trvl.domain.EmbassyHomepage;
import com.tistory.amyyzzin.trvl.dto.EmbassyHomepageDto;
import com.tistory.amyyzzin.trvl.dto.EmbassyHomepageResponseDto;
import com.tistory.amyyzzin.trvl.repository.EmbassyHomepageRepository;
import com.tistory.amyyzzin.trvl.util.GenericApiUtil;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmbassyHomepageService extends AbstractService {

    private final GenericApiUtil genericApiUtil;

    private final EmbassyHomepageRepository embassyHomepageRepository;

    @Value("${open.api.embassyHomepage}")
    String embassyHomepageUrl;

    @Override
    public void upsert() throws IOException {

        if (embassyHomepageRepository.count() > 0) {
            return;
        }
        EmbassyHomepageResponseDto embassyHomepageResponseDto =
            (EmbassyHomepageResponseDto) genericApiUtil.callJsonApi(embassyHomepageUrl,
                EmbassyHomepageResponseDto.class, "500");

        log.info("[embassyHomepageDto] {}", embassyHomepageResponseDto);

        for (EmbassyHomepageDto embassyHomepageDto : embassyHomepageResponseDto.getData()) {
            try {
                embassyHomepageRepository.save(EmbassyHomepage.of(embassyHomepageDto));
            } catch (Exception e) {
                log.error("[EmbassyHomepage.insert] ERROR {}", e.getMessage());
            }
        }
    }

    public EmbassyHomepage getEmbassyHomepage(String embassyCd) {
        for (int id = 10; id <= 30; id += 10) {
            EmbassyHomepage embassyHomepage = embassyHomepageRepository.findByEmbassyCdAndLangCdEquals(
                embassyCd, String.valueOf(id)).orElse(null);

            if (embassyHomepage != null) {
                return embassyHomepage;
            }
        }

        return EmbassyHomepage.builder()
            .homepageUrl("-")
            .build();
    }

}
