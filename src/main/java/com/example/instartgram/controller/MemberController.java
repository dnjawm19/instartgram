package com.example.instartgram.controller;


import com.example.instartgram.dto.GlobalResDto;
import com.example.instartgram.dto.req.LoginReqDto;
import com.example.instartgram.dto.req.MemberReqDto;
import com.example.instartgram.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //회원가입
    @PostMapping("/signup")
    public GlobalResDto<?> signup(@RequestBody @Valid MemberReqDto memberReqDto){
        return memberService.signup(memberReqDto);
    }

    //로그인
    @PostMapping("/login")
    public GlobalResDto<?> login(@RequestBody @Valid LoginReqDto loginReqDto, HttpServletResponse response){
        return memberService.login(loginReqDto,response);
    }

    //로그인한상태에서 탈퇴 가능 userid,pw 필요
    //게시물과 참가신청을 하지 않은사람만 탈퇴 가능
//    @DeleteMapping("/byemember")
//    public GlobalResDto<?> deleMember(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody LoginReqDto loginReqDto){
//        return memberService.deleMember(userDetails,loginReqDto);
//    }
}
