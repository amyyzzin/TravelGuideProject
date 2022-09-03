package com.tistory.amyyzzin.trvl.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tistory.amyyzzin.trvl.domain.Regulation;

@Repository
@Transactional(readOnly = true)
public interface RegulationRepository extends JpaRepository<Regulation, Long> {

	Page<Regulation> findAllByOrderByCountryNm(Pageable pageable);
}
