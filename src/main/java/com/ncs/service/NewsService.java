package com.ncs.service;

import com.ncs.dto.NewsDto;
import com.ncs.entity.News;
import com.ncs.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;

    public Long saveNews(NewsDto newsDto) {
        News news = newsDto.createNews();
        newsRepository.save(news);

        return news.getId();
    }


    public List<NewsDto> getAll() {
        List<News> newsList = newsRepository.findAll();
        List<NewsDto> list = new ArrayList<>();


        for(News news : newsList) {
            NewsDto newsDto = new NewsDto();
            newsDto.setUrl(news.getUrl());
            newsDto.setTitle(news.getTitle());
            newsDto.setReporter(news.getReporter());
            newsDto.setCategory(news.getCategory());
            newsDto.setDatetime(news.getDatetime());
            newsDto.setImageURL(news.getImageURL());
            newsDto.setText(news.getText());


            list.add(newsDto);
        }
        return list;
    }
}


