package com.woowang.board.repository;

import com.woowang.board.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    EntityManager em;

    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m",Member.class)
                .getResultList();
    }

    public Member findOne(Long memberId){
       return em.find(Member.class,memberId);
    }

    public List<Member> findOneByNick(String nick){
        return em.createQuery(
                "select m from Member m where m.nickname = :nick"
                ,Member.class)
                .setParameter("nick",nick)
                .getResultList();
    }

    public List<Member> findOneByEmail(String email) {
        return em.createQuery(
                        "select m from Member m where m.email = :email"
                        ,Member.class)
                .setParameter("email",email)
                .getResultList();
    }

    public List<Member> findByEmailAndPassword(String email, String password) {
        return em.createQuery("select m from Member m " +
                "where m.email = :email and m.password = :password",Member.class)
                .setParameter("email",email)
                .setParameter("password",password)
                .getResultList();
    }
}
