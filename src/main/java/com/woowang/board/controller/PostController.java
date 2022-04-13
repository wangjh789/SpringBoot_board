package com.woowang.board.controller;

import com.woowang.board.dto.PostDetailDto;
import com.woowang.board.service.PostService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/api/v1/post")
    public ResponseEntity<?> write(@RequestBody @Valid WritePostRequestDto writePostDto){
        try{
            Long memberId = 0L;

            Long saved = postService.writePost(
                    memberId, writePostDto.getCategoryId(), writePostDto.getTitle(), writePostDto.getContent());
            List<Long> dtos = new ArrayList<>();
            dtos.add(saved);

            ResponseDto<Long> responseDto = ResponseDto.
                    <Long>builder().data(dtos).build();
            return ResponseEntity.ok().body(responseDto);

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
            List<PostDetailDto> dtos = new ArrayList<>();
            dtos.add(postDto);

            ResponseDto<PostDetailDto> responseDto = ResponseDto.
                    <PostDetailDto>builder().data(dtos).build();
            return ResponseEntity.ok().body(responseDto);
        }catch(Exception e){
            ResponseDto<PostDetailDto> responseDto = ResponseDto.
                    <PostDetailDto>builder().error(e.getMessage()).build();
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