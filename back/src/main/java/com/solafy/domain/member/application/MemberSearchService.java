package com.solafy.domain.member.application;

import com.solafy.domain.member.dao.MemberRepository;
import com.solafy.domain.member.dto.MemberResponse;
import com.solafy.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberSearchService {

    private final MemberRepository memberRepository;
    private final MemberResponseService memberResponseService;
    private final SortService sortService;

    public List<MemberResponse> findByAll(){
        final List<Member> mList = memberRepository.findAll();
        return memberResponseService.convertToResponse(mList);
    }

    public List<MemberResponse> getApprovedMemberList(int pageNo, String sort, String type, String keyword){
        List<Member> mList = sortService.sortMember(pageNo,sort,type,keyword);
        List<Member> list = getApprovedOrUnapproved(mList, "approved");
        return memberResponseService.convertToResponse(list);
    }

    public List<MemberResponse> getUnapprovedMemberList(int pageNo, String sort, String type, String keyword){
        List<Member> mList = sortService.sortMember(pageNo,sort,type,keyword);
        List<Member> list = getApprovedOrUnapproved(mList, "unapproved");
        return memberResponseService.convertToResponse(list);
    }

    public List<Member> getApprovedOrUnapproved(List<Member> mList, String role){
        List<Member> list = new ArrayList<>();

        for(Member member : mList){
            if(role.equals("unapproved")){
                if(member.getRole().equals("ROLE_WAIT")){
                    list.add(member);
                }
            }else if(!member.getRole().equals("ROLE_WAIT")){
                list.add(member);
            }
        }

        return list;
    }
}
