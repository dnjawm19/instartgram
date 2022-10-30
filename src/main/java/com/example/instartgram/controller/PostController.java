package com.example.instartgram.controller;

import com.example.instartgram.dto.GlobalResDto;
import com.example.instartgram.dto.res.PostResDto;
import com.example.instartgram.security.UserDetailsImpl;
import com.example.instartgram.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    @PostMapping("")
    public String generateGamePost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                   @RequestPart("content") String content,
                                   @RequestPart("file") List<MultipartFile> multipartFile) {
        Long memberId = userDetails.getAccount().getMemberId();
        return postService.createPost(memberId,content,multipartFile);
    }

    @GetMapping("")
    public List<PostResDto> getAllGamePostTrue() throws ParseException {
        return postService.getAllGamePost();
    }
}
