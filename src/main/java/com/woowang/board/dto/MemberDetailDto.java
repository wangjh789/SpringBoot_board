package com.woowang.board.dto;

import com.woowang.board.domain.Member;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class MemberDetailDto {

    private String nickname;
    private List<PostDto> posts;
    private List<CommentDto> comments;

    public MemberDetailDto(Member member){
        nickname = member.getNickname();
        posts = member.getPosts().stream()
                .map(PostDto::new).collect(Collectors.toList());
        comments = member.getComments().stream()
                .map(CommentDto::new).collect(Collectors.toList());
    }

}
