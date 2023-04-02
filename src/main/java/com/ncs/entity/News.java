package com.ncs.entity;

import com.ncs.dto.NewsDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "url")
    private String url;

    @Column(name = "category")
    private String category;

    @Lob
    @Column(name = "title")
    private String title;

    @Column(name = "reporter")
    private String reporter;

    @Column(name = "datetime")
    private String datetime;

//    @ElementCollection
//    @Column(name = "imageurl")
//    private List<String> imageURL;

    @Lob
    @Column(name = "imageurl")
    private String imageURL;

    @Lob
    @Column(name = "img_desc")
    private String img_desc;



    @Lob
    @Column(name = "text")
    private String text;


    public static News createNews (NewsDto newsDtos) {
        News news = new News();

        news.setTitle(newsDtos.getTitle());
        news.setReporter(newsDtos.getReporter());
        news.setUrl(newsDtos.getUrl());
        news.setCategory(newsDtos.getCategory());
        news.setImageURL(newsDtos.getImageURL());
        news.setImg_desc(news.getImg_desc());
        news.setDatetime(newsDtos.getDatetime());
        news.setText(newsDtos.getText());

        return news;
    }
}
