package com.woowang.board.service;

import com.woowang.board.domain.Comment;
import com.woowang.board.domain.Member;
import com.woowang.board.domain.Post;
import com.woowang.board.repository.CommentRepository;
import com.woowang.board.repository.MemberRepository;
import com.woowang.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    /**
     * 댓글 작성
     */
    @Transactional
    public Long write(Long memberId,Long postId,String content){
        Member member = memberRepository.findOne(memberId);
        Post post = postRepository.findOne(postId);

        Comment comment = Comment.createComment(member,post,content);
        return commentRepository.save(comment);
    }

    /**
     * 댓글 수정
     */
    @Transactional
    public Long update(Long commentId,String content){
        Comment comment = commentRepository.findOne(commentId);
        comment.updateComment(content);
        return commentId;
    }

}
