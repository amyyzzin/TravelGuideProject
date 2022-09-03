package com.tistory.amyyzzin.trvl.service;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tistory.amyyzzin.trvl.domain.Regulation;
import com.tistory.amyyzzin.trvl.dto.RegulationDto;
import com.tistory.amyyzzin.trvl.dto.RegulationResponseDto;
import com.tistory.amyyzzin.trvl.repository.RegulationRepository;
import com.tistory.amyyzzin.trvl.util.ApiUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegulationService {

	private final ApiUtil apiUtil;

	private final RegulationRepository regulationRepository;

	@PostConstruct
	public void init() throws IOException {
		insert(apiUtil.callRegulationApi());
	}

	public Page<Regulation> getRegulations(Pageable pageable) {
		return regulationRepository.findAllByOrderByCountryNm(pageable);
	}
	
	public void insert(RegulationResponseDto regulationResponseDto) {
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
}
