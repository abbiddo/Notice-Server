package com.noticeserver.Notice.Repository;

import com.noticeserver.Notice.Domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    void deleteById(int id);
    Notice findById(int id);
    List<Notice> findAllByOrderByIdDesc();
    List<Notice> findAllByUserIDOrderByIdDesc(String id);
    List<Notice> findAllByTitleContainingOrderByIdDesc(String title);

}
