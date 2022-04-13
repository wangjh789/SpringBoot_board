package com.woowang.board.controller;

import com.woowang.board.dto.MemberDetailDto;
import com.woowang.board.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/v1/member")
    public ResponseEntity<?> join(@RequestBody @Valid JoinMemberRequestDto requestDto){
        try{
            Long joinId = memberService.join(requestDto.nickname);
            List<Long> dtos = new ArrayList<>();
            dtos.add(joinId);

            ResponseDto<Long> responseDto = ResponseDto.<Long>builder().data(dtos).build();
            return ResponseEntity.ok().body(responseDto);
        }catch(Exception e){
            ResponseDto<Long> responseDto = ResponseDto.
                    <Long>builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDto);
        }
    }
    @GetMapping("/api/v1/member/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") Long id){
        try{
            MemberDetailDto memberDto = memberService.findOne(id);
            List<MemberDetailDto> dtos = new ArrayList<>();
            dtos.add(memberDto);

            ResponseDto<MemberDetailDto> responseDto = ResponseDto.<MemberDetailDto>builder().data(dtos).build();
            return ResponseEntity.ok().body(responseDto);
        }catch(Exception e){
            ResponseDto<MemberDetailDto> responseDto = ResponseDto.
                    <MemberDetailDto>builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDto);
        }
    }

    @PutMapping("/api/v1/member/{id}")
    public ResponseEntity<?> updateNick(@PathVariable("id") Long memberId,
                                        @RequestBody @Valid UpdateNickRequestDto requestDto){
        try{
            Long id = memberService.updateNick(memberId, requestDto.getNickname());
            List<Long> dtos = new ArrayList<>();
            dtos.add(id);

            ResponseDto<Long> responseDto = ResponseDto.<Long>builder().data(dtos).build();
            return ResponseEntity.ok().body(responseDto);

        }catch(Exception e){
            ResponseDto<Long> responseDto = ResponseDto.
                    <Long>builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDto);
        }
    }


    @Data
    static class JoinMemberRequestDto {
        private String nickname;
    }
    @Data
    static class UpdateNickRequestDto{
        private String nickname;
    }

}
