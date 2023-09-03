package com.noticeserver.SpringSecurity.Repository;

import com.noticeserver.SpringSecurity.Domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository  extends JpaRepository<Member, String> {
}
