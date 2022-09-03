package com.tistory.amyyzzin.trvl.service;

import java.io.IOException;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tistory.amyyzzin.trvl.domain.RegulationVO;
import com.tistory.amyyzzin.trvl.dto.Regulation;
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

	public Page<RegulationVO> getRegulations(Pageable pageable) {
		return regulationRepository.findAllByOrderByCountryNm(pageable);
	}
	
	public void insert(RegulationResponseDto regulationResponseDto) {
		for (Regulation regulation : regulationResponseDto.getData()) {
			try {
				RegulationVO regulationVO = regulationRepository.findById(Long.valueOf(regulation.getId())).orElse(null);

				if (regulationVO == null) {
					regulationRepository.save(RegulationVO.of(regulation));
				} else {
					updateRegulationVO(regulation, regulationVO);
					regulationRepository.save(regulationVO);
				}
			} catch (Exception e) {
				log.error("[RegulationService.insert] ERROR {}", e.getMessage());
			}
		}

	}

	private void updateRegulationVO(Regulation regulation, RegulationVO regulationVO) {
		regulationVO.setCountryEngNm(regulation.getCountryEngNm());
		regulationVO.setCountryNm(regulation.getCountryNm());
		regulationVO.setCountryIsoAlp2(regulation.getCountryIsoAlp2());
		regulationVO.setHaveYn(regulation.getHaveYn());
		regulationVO.setGnrlPsptVisaYn(regulation.getGnrlPsptVisaYn());
		regulationVO.setGnrlPsptVisaCn(regulation.getGnrlPsptVisaCn());
		regulationVO.setOfclpsptVisaYn(regulation.getOfclpsptVisaYn());
		regulationVO.setOfclpsptVisaCn(regulation.getOfclpsptVisaCn());
		regulationVO.setDplmtPsptVisaYn(regulation.getDplmtPsptVisaYn());
		regulationVO.setDplmtPsptVisaCn(regulation.getDplmtPsptVisaCn());
		regulationVO.setNvisaEntryEvdcCn(regulation.getNvisaEntryEvdcCn());
		regulationVO.setRemark(regulation.getRemark());
	}
}
