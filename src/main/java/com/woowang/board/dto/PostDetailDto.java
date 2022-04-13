package com.woowang.board.dto;

import com.woowang.board.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PostDetailDto {

    private String title;
    private String content;
    private String writer;
    private List<CommentDto> comments;
    private LocalDateTime date;

    public PostDetailDto(Post post){
        title = post.getTitle();
        content = post.getContent();
        writer = post.getWriter().getNickname();
        comments = post.getComments().stream().map(CommentDto::new).collect(Collectors.toList());
        date = post.getPostDate();
    }

}
