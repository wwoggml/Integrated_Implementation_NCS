package com.ncs.service;

import com.ncs.dto.NewsDto;
import com.ncs.entity.News;
import com.ncs.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}


