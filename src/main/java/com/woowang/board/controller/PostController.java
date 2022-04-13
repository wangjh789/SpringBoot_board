package com.woowang.board.controller;

import com.woowang.board.dto.PostDetailDto;
import com.woowang.board.dto.PostDto;
import com.woowang.board.service.PostService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/api/v1/post")
    public ResponseEntity<?> write(@RequestBody @Valid WritePostRequestDto writePostDto,
                                   @AuthenticationPrincipal String memberId){
        try{
            Long saved = postService.writePost(
                    Long.valueOf(memberId), writePostDto.getCategoryId(), writePostDto.getTitle(), writePostDto.getContent());

            return ResponseEntity.ok().body(saved);

        }catch(Exception e){
            ResponseDto<Long> responseDto = ResponseDto.
                    <Long>builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDto);
        }
    }
    @GetMapping("/api/v1/post/{id}")
    public ResponseEntity<?> viewDetail(@PathVariable("id") Long id){
        try{
            PostDetailDto postDto = postService.findOneWithComments(id);

            return ResponseEntity.ok().body(postDto);
        }catch(Exception e){
            ResponseDto<PostDetailDto> responseDto = ResponseDto.
                    <PostDetailDto>builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDto);
        }
    }
    @GetMapping("/api/v1/post")
    public ResponseEntity<?> getPosts(
            @RequestParam(value = "offset",defaultValue = "0") int offset,
            @RequestParam(value = "limit",defaultValue = "100") int limit
    ){
        try{
            List<PostDto> posts = postService.findAll(offset, limit);
            ResponseDto<PostDto> responseDto = ResponseDto.<PostDto>builder().data(posts).build();
            return ResponseEntity.ok().body(responseDto);
        }catch(Exception e){
            ResponseDto<PostDto> responseDto = ResponseDto.<PostDto>builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDto);
        }
    }

    @Data
    @AllArgsConstructor
    static class WritePostRequestDto {
        private Long categoryId;
        private String title;
        private String content;
    }
}
