package com.woowang.board.service;

import com.woowang.board.domain.Category;
import com.woowang.board.domain.Member;
import com.woowang.board.domain.Post;
import com.woowang.board.dto.PostDetailDto;
import com.woowang.board.dto.PostDto;
import com.woowang.board.repository.CategoryRepository;
import com.woowang.board.repository.MemberRepository;
import com.woowang.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
        return postRepository.save(post);
    }

    /**
     * 게시글 단건 조회
     */
    public PostDetailDto findOneWithComments(Long postId){
        Post findOne = postRepository.findOneWithCategory(postId).get(0);
        return new PostDetailDto(findOne);
    }

    /**
     * 게시글 전체 조회
     */
    public List<PostDto> findAll(int offset,int limit){
        List<Post> posts = postRepository.findAllWithCategory(offset, limit);
        return posts.stream().map(PostDto::new).collect(Collectors.toList());
    }

}
