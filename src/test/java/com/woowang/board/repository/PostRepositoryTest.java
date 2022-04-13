package com.woowang.board.repository;

import com.woowang.board.domain.Category;
import com.woowang.board.domain.Member;
import com.woowang.board.domain.Post;
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
public class PostRepositoryTest {

    @Autowired PostRepository postRepository;
    @Autowired MemberRepository memberRepository;
    @Autowired CategoryRepository categoryRepository;

    @Test
    @Transactional
//    @Rollback(value = false)
    public void 게시글_생성() throws Exception {
        //given
        Member member = Member.createMember("kim1");
        Long memberId = memberRepository.save(member);

        Category category = new Category();
        category.setTitle("Free");
        categoryRepository.save(category);



        Post post = Post.createPost(member,category,"title","content");
        //when
        Long postId = postRepository.save(post);
        Post findOne = postRepository.findOne(postId);

        //then
        assertEquals(findOne.getContent(),"content");
        assertEquals(findOne.getCategory().getTitle(),"Free");
        assertEquals(findOne.getTitle(),"title");
        assertEquals(findOne.getWriter().getId(),memberId);
    }



}