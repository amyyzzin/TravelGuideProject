package com.tistory.amyyzzin.trvl.repository;

import com.tistory.amyyzzin.trvl.domain.CountryInfo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface CountryInfoRepository extends JpaRepository<CountryInfo, Long> {

    Optional<CountryInfo> findFirstByIsoAlp2(String isoAlp2);

}
