package com.solafy.repo;

import com.solafy.entity.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByMemberNo(long memberNo);
    void deleteByMemberNo(long memberNo);
    Member findByEmail(String email);

    List<Member> findByNameContainingIgnoreCase(String name, Pageable pageable); // name = %이름%
    List<Member> findByEmailContainingIgnoreCase(String email, Pageable pageable); // email = %이메일%
    List<Member> findBySsafy(long ssafy, Pageable pageable); // ssafy = 기수
}