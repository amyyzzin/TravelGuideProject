package com.tistory.amyyzzin.trvl.repository;

import com.tistory.amyyzzin.trvl.domain.CovidSafety;
import com.tistory.amyyzzin.trvl.domain.SafetyList;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface CovidSafetyRepository extends JpaRepository<CovidSafety, Long> {

    Optional<CovidSafety> findByCountryIsoAlp2(String countryIsoAlp2);

}
