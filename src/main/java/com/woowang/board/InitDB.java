package com.woowang.board;

import com.woowang.board.domain.Member;
import com.woowang.board.service.CategoryService;
import com.woowang.board.service.CommentService;
import com.woowang.board.service.MemberService;
import com.woowang.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    void init(){
        initService.initDB1();
    }

    @Component
    @RequiredArgsConstructor
    @Transactional
    static class InitService {

        private final MemberService memberService;
        private final CategoryService categoryService;
        private final PostService postService;
        private final CommentService commentService;

        public void initDB1(){
            Long joinId1 = memberService.join("userA","email1","password");
            Long joinId2 = memberService.join("userB","email2","password");
            Long joinId3 = memberService.join("userC","email3","password");
            Long joinId4 = memberService.join("userD","email4","password");
            Long joinId5 = memberService.join("userE","email5","password");
            Long categoryA = categoryService.createCategory("A");
            Long categoryB = categoryService.createCategory("B");

            Long post1 = postService.writePost(joinId1, categoryA, "title1", "content1");
            Long post2 = postService.writePost(joinId2, categoryB, "title2", "content2");
            Long post3 = postService.writePost(joinId2, categoryB, "title3", "content3");
            Long post4 = postService.writePost(joinId2, categoryA, "title4", "content4");

            Long comment1 = commentService.write(joinId1,post1,"comment1");
            Long comment2 = commentService.write(joinId1,post1,"comment2");
            Long comment3 = commentService.write(joinId2,post1,"comment3");
            Long comment4 = commentService.write(joinId2,post1,"comment4");
            Long comment5 = commentService.write(joinId3,post1,"comment5");
            Long comment6 = commentService.write(joinId4,post1,"comment6");
            Long comment7 = commentService.write(joinId5,post1,"comment7");





        }


    }
}
