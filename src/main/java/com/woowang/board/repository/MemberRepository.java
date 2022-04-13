package com.woowang.board.repository;

import com.woowang.board.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext
    EntityManager em;

    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    public Member findOne(Long memberId){
       return em.find(Member.class,memberId);
    }
}
