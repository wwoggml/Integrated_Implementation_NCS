package com.ncs.entity;

import com.ncs.dto.NewsDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.ArrayList;

@Getter
@Setter
@ToString
@Entity
@Table(name = "news")
public class News {

    @Id
    @Column(name = "news_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @Lob
//    @Column(length = 50000)
    private String title;

    private String reporter;

    private String datetime;

    @Lob
//    @Column(length = 50000)
    private String imageURL;


    @Lob
//    @Column(length = 300000)
    private String text;


    public static News createNews (NewsDto newsDtos) {
        News news = new News();

        news.setTitle(newsDtos.getTitle());
        news.setReporter(newsDtos.getReporter());
        news.setUrl(newsDtos.getUrl());
        news.setImageURL(newsDtos.getImageURL().toString());
        news.setDatetime(newsDtos.getDatetime());
        news.setText(newsDtos.getText());

        return news;
    }
}
