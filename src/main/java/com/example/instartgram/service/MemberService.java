package com.example.instartgram.service;

import com.example.instartgram.dto.GlobalResDto;
import com.example.instartgram.dto.req.LoginReqDto;
import com.example.instartgram.dto.req.MemberReqDto;
import com.example.instartgram.dto.res.LoginResDto;
import com.example.instartgram.entity.Member;
import com.example.instartgram.entity.RefreshToken;
import com.example.instartgram.exception.CustomException;
import com.example.instartgram.exception.ErrorCode;
import com.example.instartgram.jwt.JwtUtil;
import com.example.instartgram.jwt.TokenDto;
import com.example.instartgram.repository.MemberRepository;
import com.example.instartgram.repository.RefreshTokenRepository;
import com.example.instartgram.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;

    public GlobalResDto<?> signup(MemberReqDto memberReqDto) {
        if(null!=isPresentMember(memberReqDto.getUserName())){
            throw new CustomException(ErrorCode.DUPLICATED_NICKNAME);
        }

        memberReqDto.setEncodePassword(passwordEncoder.encode(memberReqDto.getPassword()));
        Member member = new Member(memberReqDto);

        memberRepository.save(member);
        return GlobalResDto.success(null);
    }

    public GlobalResDto<LoginResDto> login(LoginReqDto loginReqDto, HttpServletResponse response) {
        Member member = isPresentMember(loginReqDto.getUserName());
        if (null == member) {
            throw new CustomException(ErrorCode.NOT_FOUND_MEMBER);
        }
        if (!member.validatePassword(passwordEncoder, loginReqDto.getPassword())) {
            throw new CustomException(ErrorCode.WRONG_PASSWORD);
        }

        TokenDto tokenDto = jwtUtil.createAllToken(loginReqDto.getUserName());

        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByAccountUserName(loginReqDto.getUserName());

        if (refreshToken.isPresent()) {
            refreshTokenRepository.save(refreshToken.get().updateToken(tokenDto.getRefreshToken()));
        } else {
            RefreshToken newToken = new RefreshToken(tokenDto.getRefreshToken(), loginReqDto.getUserName());
            refreshTokenRepository.save(newToken);
        }

        setHeader(response, tokenDto);

        return GlobalResDto.success(new LoginResDto(member.getUserName(), member.getNickName()));
    }

    @Transactional
    public GlobalResDto<?> deleMember(UserDetailsImpl userDetails, LoginReqDto loginReqDto) {

        Member member = isPresentMember(userDetails.getAccount().getUserName());
        if (null == member) {
            throw new CustomException(ErrorCode.NOT_FOUND_MEMBER);
        }

        if(!member.validatePassword(passwordEncoder,loginReqDto.getPassword())){
            throw new CustomException(ErrorCode.WRONG_PASSWORD);
        }

        memberRepository.deleteById(member.getMemberId());
        return GlobalResDto.success(null);
    }

    @Transactional(readOnly = true)
    public Member isPresentMember(String userName) {
        Optional<Member> member = memberRepository.findByUserName(userName);
        return member.orElse(null);
    }

    private void setHeader(HttpServletResponse response, TokenDto tokenDto) {
        response.addHeader(JwtUtil.ACCESS_TOKEN, tokenDto.getAccessToken());
        response.addHeader(JwtUtil.REFRESH_TOKEN, tokenDto.getRefreshToken());
    }

}
