package com.tistory.amyyzzin.trvl.repository;

import com.tistory.amyyzzin.trvl.domain.EmbassyHomepage;
import com.tistory.amyyzzin.trvl.domain.TravelAlarm;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface TravelAlarmRepository extends JpaRepository<TravelAlarm, Long> {

    Optional<TravelAlarm> findByCountryIsoAlp2(String countryIsoAlp2);

}
