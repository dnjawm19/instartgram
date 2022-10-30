package com.example.instartgram.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND.value(),"M001", "유저가 존재하지 않습니다."),
    DUPLICATED_NICKNAME(HttpStatus.BAD_REQUEST.value(),"M002","아이디를 사용하는 유저가 존재합니다."),
    WRONG_PASSWORD(HttpStatus.BAD_REQUEST.value(),"M003","비밀번호가 틀렸습니다."),

    NOT_FOUND_POST(HttpStatus.NOT_FOUND.value(),"P001","게시물이 존재하지 않습니다."),
    NO_PERMISSION_CHANGE(HttpStatus.UNAUTHORIZED.value(), "P002","자신이 작성한 게시물만 수정가능합니다."),
    NO_PERMISSION_DELETE(HttpStatus.UNAUTHORIZED.value(), "P003","자신이 작성한 게시물만 삭제가능합니다.");


    private final int status;
    private final String code;
    private final String message;
}
