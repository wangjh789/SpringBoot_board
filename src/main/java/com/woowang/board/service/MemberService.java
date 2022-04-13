package com.woowang.board.service;

import com.woowang.board.domain.Member;
import com.woowang.board.dto.MemberDetailDto;
import com.woowang.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    @Transactional
    public Long join(String nickname){
        checkDuplicateNickname(nickname);
        Member member = Member.createMember(nickname);
        return memberRepository.save(member);
    }
    /**
     * 닉네임 변경
     */
    @Transactional
    public Long updateNick(Long memberId,String newNick){
        checkDuplicateNickname(newNick);
        Member member = memberRepository.findOne(memberId);
        member.updateNick(newNick);
        return memberId;
    }
    private void checkDuplicateNickname(String nickname) {
        List<Member> nick = memberRepository.findOneByNick(nickname);
        if(!nick.isEmpty()){
            throw new IllegalStateException("이미 존재하는 닉네임 입니다.");
        }
    }
    /**
     * 전체 회원조회
     */
    public List<Member> findAll(){
        return memberRepository.findAll();
    }
    /**
     * 단건 조회
     */
    public MemberDetailDto findOne(Long memberId){
        Member member = memberRepository.findOne(memberId);
        return new MemberDetailDto(member);
    }

}
