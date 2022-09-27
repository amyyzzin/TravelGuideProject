package com.tistory.amyyzzin.trvl.service;

import com.tistory.amyyzzin.trvl.domain.SafetyList;
import com.tistory.amyyzzin.trvl.dto.SafetyListDto;
import com.tistory.amyyzzin.trvl.dto.SafetyListResponseDto;
import com.tistory.amyyzzin.trvl.exception.OpenApiException;
import com.tistory.amyyzzin.trvl.repository.SafetyListRepository;
import com.tistory.amyyzzin.trvl.util.GenericApiUtil;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SafetyListService {

    private final GenericApiUtil genericApiUtil;

    private final SafetyListRepository safetyListRepository;

    @Value("${open.api.safetyList}")
    String safetyListUrl;

    @PostConstruct
    public void init() throws IOException, InterruptedException {
        if (safetyListRepository.count() > 0) {
            return;
        }

        boolean openApiError = true;

        for (int i = 0; i < 3; i++) {
            try {
                upsert((SafetyListResponseDto) genericApiUtil.callJsonApi(safetyListUrl,
                    SafetyListResponseDto.class, "100"));
                openApiError = false;

                break;
            } catch (Exception e) {
                log.error("[SafetyListService init] ERROR {}", e.getMessage());
                Thread.sleep(2000);
            }
        }

        if (openApiError) {
            throw new OpenApiException();
        }

        Thread.sleep(2000);
    }

    public void upsert(SafetyListResponseDto safetyListResponseDto) {
        if (safetyListResponseDto == null) {
            return;
        }

        log.info("[SafetyListDto] {}", safetyListResponseDto);

        for (SafetyListDto safetyListDto : safetyListResponseDto.getData()) {
            try {
                SafetyList safetyList = SafetyList.of(safetyListDto);
                safetyList.setMainNotice(safetyListDto.getTitle().startsWith("<font"));

                safetyListRepository.save(safetyList);
            } catch (Exception e) {
                log.error("[SafetyList.insert] ERROR {}", e.getMessage());
            }
        }
    }

    public List<SafetyList> getMainSafetyList() {
        return safetyListRepository.findAllByIsMainNoticeIsTrueOrderByWrtDtDesc();
    }

    public List<SafetyList> getSafetyList() {
        return safetyListRepository.findTop3ByIsMainNoticeIsFalseOrderByWrtDtDesc();
    }

    public List<SafetyList> getListAll() {
        return safetyListRepository.findAll();
    }

    public Page<SafetyList> getSafetyPageAll(PageRequest pageRequest) {
        return safetyListRepository.findAllByIsMainNoticeIsFalseOrderByWrtDtDesc(pageRequest);
    }

    public SafetyListDto detail(Long id) {

        Optional<SafetyList> optionalMember = safetyListRepository.findById(id);

        if (!optionalMember.isPresent()) {
            return null;
        }

        SafetyList safetyList = optionalMember.get();

        return SafetyListDto.of(safetyList);
    }

    public List<SafetyList> getCountrySafetyList(String countryIsoAlp2) {
        return safetyListRepository.findTop3ByCountryIsoAlp2OrIsMainNoticeIsFalseAndCountryNmEqualsOrderByWrtDtDesc(countryIsoAlp2, "ALL");
    }
}

