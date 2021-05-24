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
public class MemberResponseService {

    // Member 리스트를 MemberResponse 리스트로 전환 (password token을 감추기 위함)
    public List<MemberResponse> convertToResponse(List<Member> mList){
        List<MemberResponse> list = new ArrayList<>();
        for(Member member : mList){
            MemberResponse dto = new MemberResponse(member);
            list.add(dto);
        }
        return list;
    }
}
