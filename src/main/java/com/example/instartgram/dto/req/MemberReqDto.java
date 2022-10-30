package com.example.instartgram.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberReqDto {

    private String userName;
    private String nickName;
    private String password;

    public void setEncodePassword(String encodePassword) {
        this.password = encodePassword;
    }
}