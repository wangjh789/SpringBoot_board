package com.woowang.board.repository;

import com.woowang.board.domain.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void testMember() throws Exception {
        //given
        Member member = Member.createMember("user1");
        Long savedId = memberRepository.save(member);

        //when
        Member findMember = memberRepository.findOne(savedId);

        //then
        assertEquals(findMember.getUsername(),"user1");
    }

}