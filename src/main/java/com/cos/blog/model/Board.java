package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이터
    private String content;

    @ColumnDefault("0")
    private int count; // 조회수

    // fetch 기본 : EAGER
    @ManyToOne(fetch = FetchType.EAGER) // Many = Board, User = One, 한 명의 유저는 여러 개의 게시글을 작성 가능
    @JoinColumn(name = "userId")
    private User user; // DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.

    // fetch 기본 : LAZY
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // 하나의 게시글은 여러 개의 답변을 가질 수 있다.
    private List<Reply> reply; //mappedBy 연관관계의 주인이 아니다. fk컬럼이 안만들어짐

    @CreationTimestamp
    private Timestamp createDate;
}
