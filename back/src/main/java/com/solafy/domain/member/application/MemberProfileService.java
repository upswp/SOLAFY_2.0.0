package com.solafy.domain.member.application;

import com.solafy.domain.member.dao.MemberFindDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberProfileService {

    private final MemberFindDao memberFindDao;
}
