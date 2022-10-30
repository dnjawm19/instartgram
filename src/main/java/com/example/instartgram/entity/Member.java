package com.example.instartgram.entity;

import com.example.instartgram.dto.req.MemberReqDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Member extends Timestamped{

    @Id
    @Column(name = "memberId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "member")
    List<Post> post = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    List<Like> like = new ArrayList<>();

    public Member(MemberReqDto memberReqDto) {
        this.userName = memberReqDto.getUserName();
        this.nickName = memberReqDto.getNickName();
        this.password = memberReqDto.getPassword();
    }

    public boolean validatePassword(PasswordEncoder passwordEncoder, String password) {
        return passwordEncoder.matches(password, this.password);
    }
}
