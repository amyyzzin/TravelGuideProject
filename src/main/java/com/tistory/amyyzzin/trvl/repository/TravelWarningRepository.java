package com.tistory.amyyzzin.trvl.repository;

import com.tistory.amyyzzin.trvl.domain.TravelWarning;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface TravelWarningRepository extends JpaRepository<TravelWarning, Long> {

    Optional<TravelWarning> findByIso2Code(String iso2Code);

}
