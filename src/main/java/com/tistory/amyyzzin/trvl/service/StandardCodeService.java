package com.tistory.amyyzzin.trvl.service;

import com.tistory.amyyzzin.trvl.domain.StandardCode;
import com.tistory.amyyzzin.trvl.dto.StandardCodeDto;
import com.tistory.amyyzzin.trvl.dto.StandardCodeResponseDto;
import com.tistory.amyyzzin.trvl.repository.StandardCodeRepository;
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
public class StandardCodeService {

    private final GenericApiUtil genericApiUtil;

    private final StandardCodeRepository standardCodeRepository;

    @Value("${open.api.standardCode}")
    String standardCodeUrl;

    @PostConstruct
    public void init() throws IOException, InterruptedException {
        if (standardCodeRepository.count() > 0) {
            return;
        }

        for (int i = 0; i < 3; i++) {
            try {
                insert((StandardCodeResponseDto) genericApiUtil.callJsonApi(standardCodeUrl,
                    StandardCodeResponseDto.class, "500"));
                break;
            } catch (Exception e) {
                log.error("[StandardCodeService init] ERROR {}", e.getMessage());
                Thread.sleep(2000);
            }
        }
        Thread.sleep(2000);
    }

    public StandardCode findByIsoAlp2(String isoAlp2) {
        return standardCodeRepository.findByIsoAlp2(isoAlp2).orElse(null);
    }

    public void insert(StandardCodeResponseDto standardCodeResponseDto) {
        if (standardCodeResponseDto == null) {
            return;
        }

        log.info("[standardCodeResponseDto] {}", standardCodeResponseDto);

        for (StandardCodeDto standardCodeDto : standardCodeResponseDto.getData()) {
            try {
                standardCodeRepository.save(StandardCode.of(standardCodeDto));
            } catch (Exception e) {
                log.error("[StandardCodeService.insert] ERROR {}", e.getMessage());
            }
        }

    }
}
