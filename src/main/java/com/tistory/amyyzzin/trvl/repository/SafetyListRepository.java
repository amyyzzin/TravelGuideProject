package com.tistory.amyyzzin.trvl.repository;

import com.tistory.amyyzzin.trvl.domain.SafetyList;
import com.tistory.amyyzzin.trvl.domain.StandardCode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface SafetyListRepository extends JpaRepository<SafetyList, Long> {

    Optional<SafetyList> findByCountryIsoAlp2(String countryIsoAlp2);

    List<SafetyList> findAllByIsMainNoticeIsTrueOrderByWrtDtDesc();
    List<SafetyList> findTop3ByIsMainNoticeIsFalseOrderByWrtDtDesc();
    Page<SafetyList> findAllByIsMainNoticeIsFalseOrderByWrtDtDesc(PageRequest pageRequest);

    Optional<SafetyList> findById(Long id);

}
