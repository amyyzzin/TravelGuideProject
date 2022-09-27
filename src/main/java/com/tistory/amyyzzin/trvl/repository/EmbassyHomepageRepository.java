package com.tistory.amyyzzin.trvl.repository;

import com.tistory.amyyzzin.trvl.domain.EmbassyHomepage;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface EmbassyHomepageRepository extends JpaRepository<EmbassyHomepage, Long> {

    Optional<EmbassyHomepage> findByEmbassyCdAndLangCdEquals(String embassyCd, String langCd);

}
