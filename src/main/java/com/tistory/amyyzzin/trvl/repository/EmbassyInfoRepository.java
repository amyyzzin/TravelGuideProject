package com.tistory.amyyzzin.trvl.repository;

import com.tistory.amyyzzin.trvl.domain.CovidSafety;
import com.tistory.amyyzzin.trvl.domain.EmbassyInfo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface EmbassyInfoRepository extends JpaRepository<EmbassyInfo, Long> {

    Optional<EmbassyInfo> findByCountryIsoAlp2(String countryIsoAlp2);

}
