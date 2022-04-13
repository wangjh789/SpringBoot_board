package com.woowang.board.dto;

import com.woowang.board.domain.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDto {
    private String title;
    private String writer;
    private LocalDateTime date;

    public PostDto(Post post){
        title = post.getTitle();
        writer = post.getWriter().getNickname();
        date = post.getPostDate();
    }
}
