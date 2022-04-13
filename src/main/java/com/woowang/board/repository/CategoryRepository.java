package com.woowang.board.repository;

import com.woowang.board.domain.Category;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CategoryRepository {

    @PersistenceContext
    EntityManager em;

    public Long save(Category category){
        em.persist(category);
        return category.getId();
    }

    public Category findOne(Long categoryId){
        return em.find(Category.class,categoryId);
    }

    public List<Category> findByTitle(String title){
        return em.createQuery("select c from Category c where c.title= :title",Category.class)
                .setParameter("title",title)
                .getResultList();
    }
}
