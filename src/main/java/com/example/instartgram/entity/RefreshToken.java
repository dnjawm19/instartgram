package com.example.instartgram.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Getter
@Entity
@NoArgsConstructor
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refreshId;
    @NotBlank
    private String refreshToken;
    @NotBlank
    private String accountUserName;

    public RefreshToken(String token, String userName) {
        this.refreshToken = token;
        this.accountUserName = userName;
    }

    public RefreshToken updateToken(String token) {
        this.refreshToken = token;
        return this;
    }

}
