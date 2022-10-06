package com.tistory.amyyzzin.trvl.service;

import com.tistory.amyyzzin.trvl.domain.SafetyList;
import com.tistory.amyyzzin.trvl.dto.SafetyListDto;
import com.tistory.amyyzzin.trvl.dto.SafetyListResponseDto;
import com.tistory.amyyzzin.trvl.repository.SafetyListRepository;
import com.tistory.amyyzzin.trvl.util.GenericApiUtil;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SafetyListService extends AbstractService {

    private final GenericApiUtil genericApiUtil;

    private final SafetyListRepository safetyListRepository;

    @Value("${open.api.safetyList}")
    String safetyListUrl;

    @Override
    @Transactional
    public void upsert() throws IOException {

//        safetyListRepository.deleteAllInBatch();

        if (safetyListRepository.count() > 0) {
            return;
        }

        SafetyListResponseDto safetyListResponseDto =
            (SafetyListResponseDto) genericApiUtil.callJsonApi(safetyListUrl,
                SafetyListResponseDto.class, "200");

        for (SafetyListDto safetyListDto : safetyListResponseDto.getData()) {
            try {
                boolean isMainNotice = safetyListDto.getTitle().startsWith("<font");
                SafetyList safetyList = SafetyList.of(safetyListDto);
                safetyList.setMainNotice(isMainNotice);

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
        return safetyListRepository.findTop3ByCountryIsoAlp2OrIsMainNoticeIsFalseAndCountryNmEqualsOrderByWrtDtDesc(
            countryIsoAlp2, "ALL");
    }
}

