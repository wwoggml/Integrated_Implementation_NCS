package com.ncs.dto;


import com.ncs.entity.News;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Array;
import java.util.List;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import javax.persistence.Lob;


@Data
@Getter
@Setter
@AllArgsConstructor
public class NewsDto {

    private String url;
    private String title;
    private String reporter;
    private String category;
    private String datetime;
    private String imageURL;
    private String img_desc;

    private String text;


    public static ModelMapper modelMapper = new ModelMapper();

    public NewsDto() {

    }

    public News createNews() {
        return modelMapper.map(this, News.class);
    }
//    public static NewsDto of(News news) {
//        return modelMapper.map(news, NewsDto.class);
//    }

}
