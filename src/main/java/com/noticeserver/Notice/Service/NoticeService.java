package com.noticeserver.Notice.Service;

import com.noticeserver.Notice.DTO.NoticeModifyDTO;
import com.noticeserver.Notice.DTO.NoticeWriteDTO;
import com.noticeserver.Notice.Domain.Notice;
import com.noticeserver.Notice.Repository.NoticeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class NoticeService {
    private NoticeRepository noticeRepository;

    @Autowired
    public NoticeService(NoticeRepository noticeRepository){
        this.noticeRepository = noticeRepository;
    }

    // 공지사항 추가
    public void addNotice(String email, NoticeWriteDTO noticeWriteDTO){
        Notice newNotice = new Notice();

        newNotice.setUserID(email);
        newNotice.setTitle(noticeWriteDTO.getTitle());
        newNotice.setContent(noticeWriteDTO.getContent());
        newNotice.setWrite_date(LocalDate.now());

        noticeRepository.save(newNotice);
    }

    // 공지사항 수정
    public void updateNotice(NoticeModifyDTO noticeModifyDTO){
        Notice newNotice = new Notice();

        newNotice.setId(noticeModifyDTO.getId());
        newNotice.setUserID(noticeRepository.findById(noticeModifyDTO.getId()).getUserID());
        newNotice.setTitle(noticeModifyDTO.getTitle());
        newNotice.setContent(noticeModifyDTO.getContent());
        newNotice.setWrite_date(noticeRepository.findById(noticeModifyDTO.getId()).getWrite_date());

        noticeRepository.save(newNotice);
    }

    // 공지사항 전체 조회
    public List<Notice> allNotice(){
        return noticeRepository.findAllByOrderByIdDesc();
    }

    // 공지사항 상세 내용 조회
    public Notice noticeContent(int id){
        return noticeRepository.findById(id);
    }

    // 공지사항 제목 검색
    public List<Notice> titleNotice(String title){
        return noticeRepository.findAllByTitleContainingOrderByIdDesc(title);
    }

    // 공지사항 작성자 검색
    public List<Notice> writerNotice(String id){
        return noticeRepository.findAllByUserIDOrderByIdDesc(id);
    }

    // 공지사항 학제
    public void deleteNotice(int id) { noticeRepository.deleteById(id);}
}
