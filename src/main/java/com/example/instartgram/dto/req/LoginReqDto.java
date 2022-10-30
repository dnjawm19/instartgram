package com.example.instartgram.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class LoginReqDto {

    @NotBlank
    private String userName;
    @NotBlank
    private String password;

    public LoginReqDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

}