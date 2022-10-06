package com.tistory.amyyzzin.trvl.service;

import com.tistory.amyyzzin.trvl.constant.IsoConstant;
import com.tistory.amyyzzin.trvl.domain.AccidentList;
import com.tistory.amyyzzin.trvl.dto.AccidentListDto;
import com.tistory.amyyzzin.trvl.dto.AccidentListResponseDto;
import com.tistory.amyyzzin.trvl.repository.AccidentListRepository;
import com.tistory.amyyzzin.trvl.util.XmlApiUtil;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccidentListService extends AbstractService {

    private final XmlApiUtil xmlApiUtil;

    private final AccidentListRepository accidentListRepository;

    @Override
    public void upsert() throws IOException {

        if (accidentListRepository.count() > 0) {
            return;
        }

        AccidentListResponseDto accidentListResponseDto =
            (AccidentListResponseDto) xmlApiUtil.callAccidentListApi();

        for (AccidentListDto accidentListDto : accidentListResponseDto.getData()) {
            try {
                accidentListDto.setIso2Code(
                    IsoConstant.convertCountryEngNm2Iso2(accidentListDto.getEname()));
                accidentListRepository.save(AccidentList.of(accidentListDto));
            } catch (Exception e) {
                log.error("[AccidentList.insert] ERROR {}", e.getMessage());
            }
        }
    }

    public AccidentList findByIso2Code(String iso2Code) {
        return accidentListRepository.findByIso2Code(iso2Code).orElse(null);
    }
}
