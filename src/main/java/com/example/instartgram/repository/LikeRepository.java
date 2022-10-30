package com.example.instartgram.repository;

import com.example.instartgram.entity.Like;
import com.example.instartgram.entity.Member;
import com.example.instartgram.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Boolean existsByPostAndMember(Post post, Member member);
    void deleteByPostAndMember(Post post, Member member);
}
