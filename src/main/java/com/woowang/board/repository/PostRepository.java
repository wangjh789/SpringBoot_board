package com.woowang.board.repository;

import com.woowang.board.domain.Post;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class PostRepository {

    @PersistenceContext
    EntityManager em;

    public Long save(Post post){
        em.persist(post);
        return post.getId();
    }

    public Post findOne(Long postId){
        return em.find(Post.class,postId);
    }

}
