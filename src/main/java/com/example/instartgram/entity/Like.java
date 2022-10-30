package com.example.instartgram.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "Likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "postId")
    private Post post;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "memberId")
    private Member member;

    public Like(Post post, Member member){
        this.post = post;
        this.member = member;
    }
}
