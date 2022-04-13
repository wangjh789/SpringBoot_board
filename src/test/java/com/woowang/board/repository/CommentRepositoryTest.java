package com.woowang.board.repository;

import com.woowang.board.domain.Comment;
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
public class CommentRepositoryTest {

    @Autowired CommentRepository commentRepository;
    @Autowired PostRepository postRepository;
    @Autowired MemberRepository memberRepository;

    @Transactional
//    @Rollback(value = false)
    @Test
    public void 댓글_작성() throws Exception {
        //given
        //유저 생성
        Member member = Member.createMember("kim1");
        Long memberId = memberRepository.save(member);
        Member findMember = memberRepository.findOne(memberId);
        //게시글 생성
        Post post = Post.createPost(member,"title","content");
        Long postId = postRepository.save(post);
        Post findPost = postRepository.findOne(postId);

        //when
        //댓글 생성
        Comment comment = Comment.createComment(findMember,findPost,"comment");
        Long commentId = commentRepository.save(comment);
        Comment findComment = commentRepository.findOne(commentId);

        //then
        assertEquals(findComment.getContent(),"comment"); // 내용이 잘 들어가 있는지
        assertEquals(findComment.getWriter().getNickname(), findMember.getNickname()); //유저 닉네임이 같은지
        assertEquals(findComment.getPost().getId(),findPost.getId()); //게시글 번호가 같은지
    }

}