package com.tistory.amyyzzin.trvl.service;

import com.tistory.amyyzzin.trvl.domain.ContactPoint;
import com.tistory.amyyzzin.trvl.domain.TravelAlarm;
import com.tistory.amyyzzin.trvl.dto.ContactPointDto;
import com.tistory.amyyzzin.trvl.dto.ContactPointResponseDto;
import com.tistory.amyyzzin.trvl.dto.TravelAlarmDto;
import com.tistory.amyyzzin.trvl.dto.TravelAlarmResponseDto;
import com.tistory.amyyzzin.trvl.repository.TravelAlarmRepository;
import com.tistory.amyyzzin.trvl.util.ApiUtil;
import java.io.IOException;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TravelAlarmService {

	private final ApiUtil apiUtil;

	private final TravelAlarmRepository travelAlarmRepository;

	@PostConstruct
	public void init() throws IOException {
		if (travelAlarmRepository.count() > 0) {
			return;
		}

		insert(apiUtil.callTravelAlarmApi());
	}

	public void insert(TravelAlarmResponseDto travelAlarmResponseDto) {
		if (travelAlarmResponseDto == null) {
			return;
		}

		log.info("[travelAlarmResponseDto] {}", travelAlarmResponseDto);

		for (TravelAlarmDto travelAlarmDto : travelAlarmResponseDto.getData()) {
			try {
				travelAlarmRepository.save(TravelAlarm.of(travelAlarmDto));
			} catch (Exception e) {
				log.error("[TravelAlarm.insert] ERROR {}", e.getMessage());
			}
		}

	}
}
