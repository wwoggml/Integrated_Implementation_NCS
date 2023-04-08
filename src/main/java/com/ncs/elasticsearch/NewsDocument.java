package com.ncs.elasticsearch;

import com.ncs.dto.NewsDto;
import com.ncs.entity.News;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Document(indexName = "news_test")
public class NewsDocument {

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

    @Lob
    @Column(name = "imageurl")
    private String imageurl;

    @Lob
    @Column(name = "img_desc")
    private String img_desc;



    @Lob
    @Column(name = "text")
    private String text;


    public NewsDocument toDocument() {
        NewsDocument doc = new NewsDocument();
        doc.setTitle(title);
        doc.setText(text);
        doc.setImageurl(imageurl);
        doc.setReporter(reporter);
        doc.setUrl(url);
        doc.setImg_desc(img_desc);
        doc.setDatetime(datetime);
        doc.setCategory(category);
        return doc;
    }
}
