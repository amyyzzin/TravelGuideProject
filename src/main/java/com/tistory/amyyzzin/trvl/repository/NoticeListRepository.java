package com.tistory.amyyzzin.trvl.repository;

import com.tistory.amyyzzin.trvl.domain.CountryFlag;
import com.tistory.amyyzzin.trvl.domain.NoticeList;
import java.util.List;
import java.util.Optional;
import javax.swing.text.html.Option;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface NoticeListRepository extends JpaRepository<NoticeList, Long> {

    List<NoticeList> findTop5ByOrderByWrittenDtDesc();
    Page<NoticeList> findALlByOrderByWrittenDtDesc(PageRequest pageRequest);

    Optional<NoticeList> findByListId(String listId);

}
