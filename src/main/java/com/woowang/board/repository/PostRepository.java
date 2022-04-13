package com.woowang.board.repository;

import com.woowang.board.domain.Post;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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

    public List<Post> findOneWithCategory(Long postId){
        return em.createQuery(
                "select p from Post p " +
                        "join fetch p.category c " +
                        "join fetch p.writer w "+
                        "where p.id = :postId"
                ,Post.class)
                .setParameter("postId",postId)
                .getResultList();
    }
    public List<Post> findAllWithCategory(int offset, int limit){
        return em.createQuery(
                        "select p from Post p " +
                                "join fetch p.category c " +
                                "join fetch p.writer w "
                        ,Post.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }


}
