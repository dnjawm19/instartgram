package com.example.instartgram.security;

import com.example.instartgram.entity.Member;
import com.example.instartgram.exception.CustomException;
import com.example.instartgram.exception.ErrorCode;
import com.example.instartgram.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Member member = memberRepository.findByUserName(userName).orElseThrow(
                () -> new RuntimeException("NOT FOUNT ACCOUNT")
        );

//        Member member = memberRepository.findByUserName(userName).orElseThrow(
//                () -> new CustomException(ErrorCode.NOT_FOUND_MEMBER)
//        );
//
//        Member member = isPresentMember(userName);
//        if (member == null){
//            throw new CustomException(ErrorCode.NOT_FOUND_MEMBER);
//        }

        UserDetailsImpl userDetails = new UserDetailsImpl();
        userDetails.setAccount(member);

        return userDetails;
    }

    @Transactional(readOnly = true)
    public Member isPresentMember(String userName) {
        Optional<Member> member = memberRepository.findByUserName(userName);
        return member.orElse(null);
    }
}
