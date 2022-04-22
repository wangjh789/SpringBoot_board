package com.woowang.board.controller;

import com.woowang.board.service.CommentService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/api/v1/posts/{postId}/comments")
    public ResponseEntity<?> writeComment(@PathVariable("postId") Long postId,
                                          @RequestBody @Valid WriteCommentRequest commentRequest){
        try{
            Long memberId = 1L;

            Long commentId = commentService.write(memberId, postId, commentRequest.content);

            List<Long> dtos = new ArrayList<>();
            dtos.add(commentId);
            ResponseDto<Long> responseDto = ResponseDto.<Long>builder().data(dtos).build();
            return ResponseEntity.ok().body(responseDto);
        }catch(Exception e){
            ResponseDto<Long> responseDto = ResponseDto.<Long>builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDto);
        }
    }


//    @PutMapping("/api/v1/comment/{commentId}")
//    public ResponseEntity<?> updateComment(@PathVariable("commentId") Long commentId,
//                                           @RequestBody
//                                           ){
//        try{
//            commentService.
//        }catch (Exception e){
//
//        }
//    }
    @Data
    static class WriteCommentRequest {
        private String content;
    }

}
