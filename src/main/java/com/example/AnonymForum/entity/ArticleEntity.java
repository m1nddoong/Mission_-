package com.example.AnonymForum.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name="article")
public class ArticleEntity implements Comparable<ArticleEntity>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private Long password;

    // 여러개의 article(N) : board(1) 의 관계
    @ManyToOne
    @JoinColumn(name = "board_id")  // 외래 키(Foreign Key)를 지정합니다.
    private BoardEntitiy board;

    // 게시판과 댓글의 양방향 관계 매핑
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<CommentEntity> comments = new ArrayList<>();
    // 게시글 관련 메서드 및 필드

    @Override
    public int compareTo(ArticleEntity other) {
        // id를 기준으로 내림차순 정렬
        return other.getId().compareTo(this.getId());
    }
}
