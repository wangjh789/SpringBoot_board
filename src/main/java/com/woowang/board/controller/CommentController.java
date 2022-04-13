package com.woowang.board.controller;

import com.woowang.board.service.CommentService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/api/v1/post/{postId}/comment")
    public ResponseEntity<?> writeComment(@PathVariable("postId") Long postId,
                                          @AuthenticationPrincipal String memberId,
                                          @RequestBody @Valid WriteCommentRequest commentRequest){
        try{
            Long commentId = commentService.write(Long.valueOf(memberId), postId, commentRequest.content);

            return ResponseEntity.ok().body(commentId);
        }catch(Exception e){
            ResponseDto<Long> responseDto = ResponseDto.<Long>builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDto);
        }
    }

    @PutMapping("/api/v1/comment/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable("commentId") Long commentId,
                                           @RequestBody @Valid WriteCommentRequest commentRequest,
                                           @AuthenticationPrincipal String memberId
                                           ){
        try{
            Long update = commentService.update(Long.valueOf(memberId), commentId, commentRequest.getContent());
            return ResponseEntity.ok().body(update);
        }catch (Exception e){
            ResponseDto<Long> dto = ResponseDto.<Long>builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(dto);
        }
    }
    @Data
    static class WriteCommentRequest {
        private String content;
    }

}
