package com.example.instartgram.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @Column(name = "postId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "memberId")
    @JsonIgnore
    private Member member;

    private String content;

    @ElementCollection
    private List<String> path;

    @OneToMany(mappedBy = "post")
    List<Like> like = new ArrayList<>();

    public Post(Member member, String content, List<String> path) {
        this.member = member;
        this.content = content;
        this.path = path;
    }
}
