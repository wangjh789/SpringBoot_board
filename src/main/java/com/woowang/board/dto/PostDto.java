package com.woowang.board.dto;

import com.woowang.board.domain.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDto {
    private String category;
    private String title;
    private String writer;
    private LocalDateTime date;

    public PostDto(Post post){
        category = post.getCategory().getTitle();
        title = post.getTitle();
        writer = post.getWriter().getNickname();
        date = post.getPostDate();
    }
}
