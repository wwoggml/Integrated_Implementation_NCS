package com.ncs.dto;


import com.ncs.entity.News;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import org.modelmapper.ModelMapper;


@Data
@Getter
@Setter
@AllArgsConstructor
public class NewsDto {

    private String url;
    private String title;
    private String reporter;
    private String datetime;
    private List<String> imageURL;
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
