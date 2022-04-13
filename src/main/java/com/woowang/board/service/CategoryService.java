package com.woowang.board.service;

import com.woowang.board.domain.Category;
import com.woowang.board.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Long createCategory(String title){
        Category category = new Category();
        category.setTitle(title);
        return categoryRepository.save(category);
    }

}
