package com.ncs.service;

import com.ncs.dto.MemberDocument;
import com.ncs.dto.MemberResponse;
import com.ncs.dto.MemberSaveAllRequest;
import com.ncs.dto.SearchCondition;
import com.ncs.entity.Member;
import com.ncs.repository.MemberRepository;
import com.ncs.repository.MemberSearchQueryRepository;
import com.ncs.repository.MemberSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
@Component
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberSearchRepository memberSearchRepository;
    @Autowired
    private MemberSearchQueryRepository memberSearchQueryRepository;

    @Transactional
    public void saveAllMember(MemberSaveAllRequest memberSaveAllRequest) {
        List<Member> memberList =
                memberSaveAllRequest.getMemberSaveRequestList().stream().map(Member::from).collect(Collectors.toList());
        memberRepository.saveAll(memberList);
    }

    @Transactional
    public void saveAllMemberDocuments() {
        List<MemberDocument> memberDocumentList
                = memberRepository.findAll().stream().map(MemberDocument::from).collect(Collectors.toList());
        memberSearchRepository.saveAll(memberDocumentList);
    }


    public List<MemberResponse> findByNickname(String nickname, Pageable pageable) {
        return memberSearchRepository.findByNickname(nickname, pageable)
                .stream()
                .map(MemberResponse::from)
                .collect(Collectors.toList());
    }

    public List<MemberResponse> findByAge(int age){
        return memberSearchRepository.findByAge(age)
                .stream()
                .map(MemberResponse::from)
                .collect(Collectors.toList());
    }

    public List<MemberResponse> searchByCondition(SearchCondition searchCondition, Pageable pageable) {
        return memberSearchQueryRepository.findByCondition(searchCondition, pageable)
                .stream()
                .map(MemberResponse::from)
                .collect(Collectors.toList());
    }

    public List<MemberResponse> findByStartWithNickname(String nickname, Pageable pageable) {
        return memberSearchQueryRepository.findByStartWithNickname(nickname, pageable)
                .stream()
                .map(MemberResponse::from)
                .collect(Collectors.toList());
    }

    public List<MemberResponse> findByMatchesDescription(String description, Pageable pageable) {
        return memberSearchQueryRepository.findByMatchesDescription(description, pageable)
                .stream()
                .map(MemberResponse::from)
                .collect(Collectors.toList());
    }

    public List<MemberResponse> findByContainsDescription(String description, Pageable pageable) {
        return memberSearchQueryRepository.findByContainsDescription(description, pageable)
                .stream()
                .map(MemberResponse::from)
                .collect(Collectors.toList());
    }


}