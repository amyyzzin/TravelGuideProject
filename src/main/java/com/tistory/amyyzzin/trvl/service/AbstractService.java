package com.tistory.amyyzzin.trvl.service;

import com.tistory.amyyzzin.trvl.exception.OpenApiException;
import java.io.IOException;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public abstract class AbstractService {

    @PostConstruct
    public void init() throws IOException, InterruptedException {
        boolean openApiError = true;

        for (int i = 0; i < 3; i++) {
            try {
                upsert();
                openApiError = false;

                break;
            } catch (Exception e) {
                log.error("[init] ERROR {}", e.getMessage());
                Thread.sleep(2000);
            }
        }

        if (openApiError) {
            throw new OpenApiException();
        }

        Thread.sleep(2000);
    }

    public abstract void upsert() throws IOException;
}
