package com.example.instartgram.dto.res;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostResDto {
    private Long postId;
    private String content;
    private String imgUrl;

    public PostResDto(Long postId, String content, String imgUrl){
        this.postId = postId;
        this.content = content;
        this.imgUrl = imgUrl;
    }
}
