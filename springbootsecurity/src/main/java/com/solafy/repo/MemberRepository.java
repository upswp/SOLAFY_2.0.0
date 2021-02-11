package com.solafy.repo;

import com.solafy.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByMemberNo(long memberNo);
    void deleteByMemberNo(long memberNo);
    Member findByEmail(String email);
}