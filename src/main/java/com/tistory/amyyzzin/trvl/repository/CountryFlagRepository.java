package com.tistory.amyyzzin.trvl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tistory.amyyzzin.trvl.domain.CountryFlag;
import com.tistory.amyyzzin.trvl.domain.StandardCode;

@Repository
@Transactional(readOnly = true)
public interface CountryFlagRepository extends JpaRepository<CountryFlag, Long> {

	Optional<CountryFlag> findByIsoAlp2(String isoAlp2);

}
