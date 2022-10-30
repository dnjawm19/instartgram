package com.example.instartgram.entity;

import com.example.instartgram.dto.req.MemberReqDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Member extends Timestamped{

    @Id
    @Column(name = "memberid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String password;

    public Member(MemberReqDto memberReqDto) {
        this.userName = memberReqDto.getUserName();
        this.nickName = memberReqDto.getNickName();
        this.password = memberReqDto.getPassword();
    }

    public boolean validatePassword(PasswordEncoder passwordEncoder, String password) {
        return passwordEncoder.matches(password, this.password);
    }
}
