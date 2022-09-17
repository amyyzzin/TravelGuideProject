package com.tistory.amyyzzin.trvl.service;

import com.tistory.amyyzzin.trvl.domain.ContactPoint;
import com.tistory.amyyzzin.trvl.domain.EmbassyHomepage;
import com.tistory.amyyzzin.trvl.dto.ContactPointDto;
import com.tistory.amyyzzin.trvl.dto.ContactPointResponseDto;
import com.tistory.amyyzzin.trvl.dto.EmbassyHomepageDto;
import com.tistory.amyyzzin.trvl.dto.EmbassyHomepageResponseDto;
import com.tistory.amyyzzin.trvl.repository.ContactPointRepository;
import com.tistory.amyyzzin.trvl.util.ApiUtil;
import java.io.IOException;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContactPointService {

	private final ApiUtil apiUtil;

	private final ContactPointRepository contactPointRepository;

	@PostConstruct
	public void init() throws IOException {
		if (contactPointRepository.count() > 0) {
			return;
		}

		insert(apiUtil.callContactPointApi());
	}

	public void insert(ContactPointResponseDto contactPointResponseDto) {
		if (contactPointResponseDto == null) {
			return;
		}

		log.info("[contactPointResponseDto] {}", contactPointResponseDto);

		for (ContactPointDto contactPointDto : contactPointResponseDto.getData()) {
			try {
				contactPointRepository.save(ContactPoint.of(contactPointDto));
			} catch (Exception e) {
				log.error("[ContactPoint.insert] ERROR {}", e.getMessage());
			}
		}

	}
}
