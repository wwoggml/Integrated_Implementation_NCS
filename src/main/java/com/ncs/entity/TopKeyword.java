package com.ncs.entity;


import com.ncs.dto.TopKeywordDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "topkeyword")
public class TopKeyword {
    @Id
    @Column(name = "keyword_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "keyword")
    private String keyword;

    @Column(name = "count")
    private int count;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public TopKeyword() {
    }
    public TopKeyword(String keyword, int count, LocalDateTime createdAt) {
        this.keyword = keyword;
        this.count = count;
        this.createdAt = createdAt;
    }

    public static TopKeyword creatTopKeyword (TopKeywordDto topKeywordDto) {
        TopKeyword topKeyword = new TopKeyword();

        topKeyword.setKeyword(topKeywordDto.getKeyword());
        topKeyword.setCount(1);
        topKeyword.setCreatedAt(LocalDateTime.now());

        return topKeyword;
    }
}
