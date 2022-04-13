package com.woowang.board.controller;

import com.woowang.board.domain.Member;
import com.woowang.board.dto.MemberDetailDto;
import com.woowang.board.security.TokenProvider;
import com.woowang.board.service.MemberService;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final TokenProvider tokenProvider;

    @PostMapping("/api/v1/member/join")
    public ResponseEntity<?> join(@RequestBody @Valid JoinMemberRequestDto requestDto){
        try{
            Long joinId = memberService.join(requestDto.nickname,requestDto.email, requestDto.password);

            return ResponseEntity.ok().body(joinId);
        }catch(Exception e){
            ResponseDto<Long> responseDto = ResponseDto.
                    <Long>builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDto);
        }
    }
    @PostMapping("/api/v1/member/signin")
    public ResponseEntity<?> authenticate(@RequestBody @Valid MemberController.SignInRequestDto memberDto){
        List<Member> members = memberService.getByCredentials(memberDto.email, memberDto.password);
        if(members.isEmpty()){
            return ResponseEntity.badRequest().body("signin failed");
        }else{
            Member member= members.get(0);
            final String token = tokenProvider.create(member);
            final SignInResponseDto responseDto = new SignInResponseDto();
            responseDto.email = member.getEmail();
            responseDto.nickname = member.getNickname();
            responseDto.token = token;
            return ResponseEntity.ok().body(responseDto);
        }

    }

    @GetMapping("/api/v1/member/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") Long id){
        try{
            MemberDetailDto memberDto = memberService.findOne(id);

            return ResponseEntity.ok().body(memberDto);
        }catch(Exception e){
            ResponseDto<MemberDetailDto> responseDto = ResponseDto.
                    <MemberDetailDto>builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDto);
        }
    }

    @PutMapping("/api/v1/member/nickname")
    public ResponseEntity<?> updateNick(@AuthenticationPrincipal String memberId,
                                        @RequestBody @Valid UpdateNickRequestDto requestDto){
        try{
            Long id = memberService.updateNick(Long.valueOf(memberId), requestDto.getNickname());

            return ResponseEntity.ok().body(id);

        }catch(Exception e){
            ResponseDto<Long> responseDto = ResponseDto.
                    <Long>builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDto);
        }
    }


    @Data
    static class JoinMemberRequestDto {
        private String nickname;
        private String email;
        private String password;
    }
    @Data
    static class SignInRequestDto {
        private String email;
        private String password;
    }
    @Data
    static class SignInResponseDto{
        private String email;
        private String nickname;
        private String token;
    }

    @Data
    static class UpdateNickRequestDto{
        private String nickname;
    }

}
