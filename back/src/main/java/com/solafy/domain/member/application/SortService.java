package com.solafy.domain.member.application;

import com.solafy.domain.member.dao.MemberRepository;
import com.solafy.domain.member.dto.MemberDto;
import com.solafy.domain.member.dto.MemberResponse;
import com.solafy.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SortService {

    private final MemberRepository memberRepository;

    // 페이지 번호, 검색 키워드, 회원 권한에 따른 목록 검색
    public List<Member> sortMember(int pageNo, String sort, String type, String keyword){
        if(pageNo < 1) pageNo = 1;
        final String sortColumn = getSortColumn(sort);
        final String sortType = getSortType(sort);
        if(keyword == null) keyword = "";

        Pageable pageable = null;
        if(sortType.equals("asc")) pageable = PageRequest.of(pageNo, 10, Sort.by(sortColumn).ascending());
        else pageable = PageRequest.of(pageNo, 10, Sort.by(sortColumn).descending());

        if(keyword.equals("")) {
            if(sortType.equals("asc")) return memberRepository.findAll(Sort.by(sortColumn).ascending());
            else return memberRepository.findAll(Sort.by(sortColumn).descending());
        }
        else if(getType(type).equals("기수")) return memberRepository.findBySsafy(Long.parseLong(keyword), pageable);
        else if(getType(type).equals("아이디")) return memberRepository.findByEmailContainingIgnoreCase(keyword, pageable);
        else return memberRepository.findByNameContainingIgnoreCase(keyword, pageable);
    }

    // 회원번호(오름) => 회원번호 => memberNo
    public String getSortColumn(String sort){
        if(sort == null || sort.equals("")) {
            return "memberNo";
        }

        int len = sort.length();
        String sortColumn = sort.substring(0,len-4);

        if(sortColumn.equals("이름")) return "name";
        else if(sortColumn.equals("기수")) return "ssafy";
        else if(sortColumn.equals("가입일자")) return "createDate";
        else return "memberNo";
    }

    // 회원번호(내림) => 내림 => desc
    public String getSortType(String sort){
        if(sort == null || sort.equals("")) {
            return "asc";
        }

        int len = sort.length();
        String sortType = sort.substring(len-3,len-1);

        if(sortType.equals("내림")) return "desc";
        else return "asc";
    }

    // 회원번호 => memberNo
    public String getType(String type){
        if(type == null || type.equals("")) return "name";
        else if(type.equals("이름")) return "name";
        else if(type.equals("아이디")) return "email";
        else if(type.equals("기수")) return "ssafy";
        else return "name";
    }
}
