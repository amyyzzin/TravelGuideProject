package com.tistory.amyyzzin.trvl.repository;

import com.tistory.amyyzzin.trvl.domain.CountryBasicInfo;
import com.tistory.amyyzzin.trvl.domain.CountryInfo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface CountryBasicInfoRepository extends JpaRepository<CountryBasicInfo, String> {

    Optional<CountryBasicInfo> findByIso2Code(String iso2Code);

}
