package com.woowang.board.service;

import com.woowang.board.domain.Category;
import com.woowang.board.domain.Member;
import com.woowang.board.domain.Post;
import com.woowang.board.dto.PostDetailDto;
import com.woowang.board.repository.CategoryRepository;
import com.woowang.board.repository.MemberRepository;
import com.woowang.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

    /**
     * 게시글 작성
     */
    @Transactional
    public Long writePost(Long memberId,Long categoryId,String title,String content){
        Member member = memberRepository.findOne(memberId);
        Category category = categoryRepository.findOne(categoryId);

        Post post = Post.createPost(member,category,title,content);
        Long savedId = postRepository.save(post);
        return savedId;
    }

    /**
     * 게시글 단건 조회
     */
    public PostDetailDto findOneWithComments(Long postId){
        Post findOne = postRepository.findOne(postId);
        return new PostDetailDto(findOne);
    }


}
