package com.tistory.amyyzzin.trvl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tistory.amyyzzin.trvl.domain.StandardCode;

@Repository
@Transactional(readOnly = true)
public interface StandardCodeRepository extends JpaRepository<StandardCode, Long> {

	Optional<StandardCode> findByIsoAlp2(String isoAlp2);
	Optional<StandardCode> findByCountryEngNm(String countryEngNm);
}
