package com.tistory.amyyzzin.trvl.service;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.tistory.amyyzzin.trvl.domain.StandardCode;
import com.tistory.amyyzzin.trvl.dto.StandardCodeDto;
import com.tistory.amyyzzin.trvl.dto.StandardCodeResponseDto;
import com.tistory.amyyzzin.trvl.repository.StandardCodeRepository;
import com.tistory.amyyzzin.trvl.util.ApiUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class StandardCodeService {

	private final ApiUtil apiUtil;

	private final StandardCodeRepository standardCodeRepository;

	@PostConstruct
	public void init() throws IOException {
		if (standardCodeRepository.count() > 0) {
			return;
		}

		insert(apiUtil.callStandardCodeApi());
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
