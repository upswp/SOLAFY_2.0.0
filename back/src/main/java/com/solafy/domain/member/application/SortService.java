package com.solafy.domain.member.application;

import com.solafy.domain.member.dao.MemberRepository;
import com.solafy.domain.member.dto.MemberDto;
import com.solafy.domain.member.dto.MemberResponse;
import com.solafy.domain.member.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SortService {

    @Autowired
    private MemberRepository memberRepository;

    // Member 리스트를 MemberDto 리스트로 전환 (password token을 감추기 위함)
//    public List<MemberDto> convertMemberToDto(List<Member> list){
//
//        List<MemberDto> newList = new ArrayList<>();
//        for(int i = 0; i < list.size(); i++){
//            MemberDto memberDto = new MemberDto(list.get(i));
//            newList.add(memberDto);
//        }
//        return newList;
//    }

    // Member 리스트를 MemberResponse 리스트로 전환 (password token을 감추기 위함)
    public List<MemberResponse> convertMemberToDto(List<Member> list){

        List<MemberResponse> newList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            MemberResponse dto = new MemberResponse(list.get(i));
            newList.add(dto);
        }
        return newList;
    }

    // 페이지 번호, 검색 키워드, 회원 권한에 따른 목록 검색
    public List<Member> sortMember(int pageNo, String sort, String type, String keyword, String role){

        if(pageNo < 1) pageNo = 1;
        String sortColumn = "", sortType = "";
        if(sort == null || sort.equals("")) {
            sortColumn = "memberNo";
            sortType = "asc";
        }
        else{
            int len = sort.length();
            sortColumn = sort.substring(0,len-4);
            sortType = sort.substring(len-3,len-1);

            if(sortColumn.equals("이름")) sortColumn = "name";
            else if(sortColumn.equals("회원번호")) sortColumn = "memberNo";
            else if(sortColumn.equals("기수")) sortColumn = "ssafy";
            else if(sortColumn.equals("가입일자")) sortColumn = "createDate";
            else sortColumn = "memberNo";

            if(sortType.equals("내림")) sortType = "desc";
            else sortType = "asc";
        }
        if(type == null || type.equals("")) type = "name";
        else if(type.equals("이름")) type = "name";
        else if(type.equals("아이디")) type = "email";
        else if(type.equals("기수")) type = "ssafy";
        else type = "name";
        if(keyword == null) keyword = "";

        Pageable pageable = null;
        if(sortType.equals("asc")) pageable = PageRequest.of(pageNo, 10, Sort.by(sortColumn).ascending());
        else pageable = PageRequest.of(pageNo, 10, Sort.by(sortColumn).descending());

        List<Member> mlist = new ArrayList<>();
        if(keyword.equals("")) {
            if(sortType.equals("asc")) mlist = memberRepository.findAll(Sort.by(sortColumn).ascending());
            else mlist = memberRepository.findAll(Sort.by(sortColumn).descending());
        }
        else if(type.equals("기수")) mlist = memberRepository.findBySsafy(Long.parseLong(keyword), pageable);
        else if(type.equals("아이디")) mlist = memberRepository.findByEmailContainingIgnoreCase(keyword, pageable);
        else mlist = memberRepository.findByNameContainingIgnoreCase(keyword, pageable);

        List<Member> list = new ArrayList<>();
        for(int i = 0; i < mlist.size(); i++){
            Member memberObj = mlist.get(i);
            if(role.equals("unapproved")){
                if(memberObj.getRole().equals("ROLE_WAIT")){
                    list.add(memberObj);
                }
            }else if(!memberObj.getRole().equals("ROLE_WAIT")){
                list.add(memberObj);
            }
        }

        return list;
    }
}
