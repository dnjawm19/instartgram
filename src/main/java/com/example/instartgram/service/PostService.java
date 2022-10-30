package com.example.instartgram.service;

import com.example.instartgram.dto.res.PostResDto;
import com.example.instartgram.entity.Member;
import com.example.instartgram.entity.Post;
import com.example.instartgram.repository.MemberRepository;
import com.example.instartgram.repository.PostRepository;
import com.example.instartgram.s3.AmazonS3ResourceStorage;
import com.example.instartgram.s3.MultipartUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final AmazonS3ResourceStorage amazonS3ResourceStorage;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public String createPost(Long memberId, String content, List<MultipartFile> multipartFile) {
        Member member = isPresentMember(memberId);

        List<String> paths = new ArrayList<>();

        multipartFile.forEach(file -> {
            String path = MultipartUtil.createPath(MultipartUtil.createFileId(), MultipartUtil.getFormat(file.getContentType()));
            paths.add(path);
            amazonS3ResourceStorage.store(path, file);
        });

//        String path = MultipartUtil.createPath(MultipartUtil.createFileId(), MultipartUtil.getFormat(multipartFile.getContentType()));

        Post post = new Post(member, content, paths);
        postRepository.save(post);

        return "성공";
    }

    @Transactional
    public List<PostResDto> getAllGamePost(){
        List<Post> posts = postRepository.findAll();

        List<PostResDto> postResDtos = new ArrayList<>();

        for(Post post:posts){
            PostResDto postResDto = new PostResDto(post.getPostId(), post.getContent(), amazonS3ResourceStorage.getimg(post.getPath()));
            postResDtos.add(postResDto);
        }

        return postResDtos;
    }

    public Member isPresentMember(Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        return member.orElse(null);
    }
}
