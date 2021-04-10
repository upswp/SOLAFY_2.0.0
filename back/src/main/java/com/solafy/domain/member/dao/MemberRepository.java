package com.solafy.domain.member.dao;

import com.solafy.domain.member.entity.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByMemberNo(Long memberNo);
    void deleteByMemberNo(Long memberNo);
    Member findByEmail(String email);

    boolean existsByEmail(String email); // 해당 이메일이 등록되었는지 확인

    List<Member> findByNameContainingIgnoreCase(String name, Pageable pageable); // name = %이름%
    List<Member> findByEmailContainingIgnoreCase(String email, Pageable pageable); // email = %이메일%
    List<Member> findBySsafy(Long ssafy, Pageable pageable); // ssafy = 기수
}