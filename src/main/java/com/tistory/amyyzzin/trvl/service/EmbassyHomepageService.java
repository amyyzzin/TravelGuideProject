package com.tistory.amyyzzin.trvl.service;

import com.tistory.amyyzzin.trvl.domain.EmbassyHomepage;
import com.tistory.amyyzzin.trvl.dto.EmbassyHomepageDto;
import com.tistory.amyyzzin.trvl.dto.EmbassyHomepageResponseDto;
import com.tistory.amyyzzin.trvl.repository.EmbassyHomepageRepository;
import com.tistory.amyyzzin.trvl.util.GenericApiUtil;
import java.io.IOException;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmbassyHomepageService {

    private final GenericApiUtil genericApiUtil;

    private final EmbassyHomepageRepository embassyHomepageRepository;

    @Value("${open.api.embassyHomepage}")
    String embassyHomepageUrl;

    @PostConstruct
    public void init() throws IOException {
        if (embassyHomepageRepository.count() > 0) {
            return;
        }

        insert((EmbassyHomepageResponseDto) genericApiUtil.callJsonApi(embassyHomepageUrl,
            EmbassyHomepageResponseDto.class));
    }

    public void insert(EmbassyHomepageResponseDto embassyHomepageResponseDto) {
        if (embassyHomepageResponseDto == null) {
            return;
        }

        log.info("[embassyHomepageDto] {}", embassyHomepageResponseDto);

        for (EmbassyHomepageDto embassyHomepageDto : embassyHomepageResponseDto.getData()) {
            try {
                embassyHomepageRepository.save(EmbassyHomepage.of(embassyHomepageDto));
            } catch (Exception e) {
                log.error("[EmbassyHomepage.insert] ERROR {}", e.getMessage());
            }
        }

    }
}
