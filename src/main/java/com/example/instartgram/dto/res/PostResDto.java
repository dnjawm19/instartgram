package com.example.instartgram.dto.res;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class PostResDto {
    private Long postId;
    private String content;
    private List<String> imgUrl;

    private Boolean likeCheck;

    public PostResDto(Long postId, String content, List<String> imgUrl, Boolean likeCheck){
        this.postId = postId;
        this.content = content;
        this.imgUrl = imgUrl;
        this.likeCheck = likeCheck;
    }
}
