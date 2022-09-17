package com.tistory.amyyzzin.trvl.service;

import com.tistory.amyyzzin.trvl.domain.OverseasArrival;
import com.tistory.amyyzzin.trvl.domain.SafetyList;
import com.tistory.amyyzzin.trvl.dto.OverseasArrivalDto;
import com.tistory.amyyzzin.trvl.dto.OverseasArrivalResponseDto;
import com.tistory.amyyzzin.trvl.dto.SafetyListDto;
import com.tistory.amyyzzin.trvl.dto.SafetyListResponseDto;
import com.tistory.amyyzzin.trvl.repository.OverseasArrivalRepository;
import com.tistory.amyyzzin.trvl.util.ApiUtil;
import java.io.IOException;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OverseasArrivalService {

	private final ApiUtil apiUtil;

	private final OverseasArrivalRepository overseasArrivalRepository;

	@PostConstruct
	public void init() throws IOException {
		if (overseasArrivalRepository.count() > 0) {
			return;
		}

		insert(apiUtil.callOverseasArrivalApi());
	}

	public void insert(OverseasArrivalResponseDto overseasArrivalResponseDto) {
		if (overseasArrivalResponseDto == null) {
			return;
		}

		log.info("[overseasArrivalDto] {}", overseasArrivalResponseDto);

		for (OverseasArrivalDto overseasArrivalDto : overseasArrivalResponseDto.getData()) {
			try {
				overseasArrivalRepository.save(OverseasArrival.of(overseasArrivalDto));
			} catch (Exception e) {
				log.error("[OverseasArrival.insert] ERROR {}", e.getMessage());
			}
		}

	}
}