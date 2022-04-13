package com.woowang.board.dto;

import com.woowang.board.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {

    private String writer;
    private String content;
    private LocalDateTime date;

    CommentDto(Comment comment){
        this.writer = comment.getWriter().getNickname();
        this.content = comment.getContent();
        this.date = comment.getCommentDate();
    }
}
