package com.tistory.amyyzzin.trvl.repository;

import com.tistory.amyyzzin.trvl.domain.AccidentList;
import com.tistory.amyyzzin.trvl.domain.CountryBasicInfo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface AccidentListRepository extends JpaRepository<AccidentList, String> {

    Optional<AccidentList> findByIso2Code(String iso2Code);

}
