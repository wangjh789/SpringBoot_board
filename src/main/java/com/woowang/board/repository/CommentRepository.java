package com.woowang.board.repository;

import com.woowang.board.domain.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CommentRepository {

    @PersistenceContext
    EntityManager em;

    public Long save(Comment comment){
        em.persist(comment);
        return comment.getId();
    }

    public Comment findOne(Long commentId){
        return em.find(Comment.class,commentId);
    }
}
