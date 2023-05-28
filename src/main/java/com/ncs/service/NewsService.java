package com.ncs.service;

import com.ncs.dto.NewsDto;
import com.ncs.elasticsearch.NewsDocument;
import com.ncs.elasticsearch.NewsDocumentRepository;
import com.ncs.entity.News;
import com.ncs.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;

    private final NewsDocumentRepository newsDocumentRepository;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

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

    public Page<NewsDocument> searchNews(String keyword, Pageable pageable) {
        log.info(newsDocumentRepository.findByTitleOrText(keyword, pageable));
        return newsDocumentRepository.findByTitleOrText(keyword, pageable);
    }

    public Page<News> searchCategory(String category, Pageable pageable){
        return newsRepository.findByCategory(category, pageable);
    }

    public List<News> getIdNews(Long id) {
        Optional<News> newsList = newsRepository.findById(id);
        List<News> list = new ArrayList<>();
        if (newsList.isPresent()) {
            list.add(newsList.get());
        }

        return list;
    }

    public List<News> getOneEntities(String category) {
        List<News> resultList = newsRepository.findTop1ByCategory(category);
        return resultList;
    }

    public List<News> getFiveEntities(String category) {
        List<News> resultList = newsRepository.findTop5ByCategory(category);
        return resultList;
    }

    public Page<News> getNews(String keyword, Pageable pageable) {
        return newsRepository.findByKeyword(keyword, pageable);
    }
    public Page<News> getSortDesc(String keyword, Pageable pageable) {
        return newsRepository.findByKeywordOrderByDatetimeDesc(keyword, pageable);
    }

    public Page<News> getSortAsc(String keyword, Pageable pageable) {
        return newsRepository.findByKeywordOrderByDatetimeAsc(keyword, pageable);
    }

    public Page<News> getCategorySortDesc(String category, Pageable pageable) {
        return newsRepository.findByCategoryOrderByDatetimeDesc(category, pageable);
    }

    public Page<News> getCategorySortAsc(String category, Pageable pageable) {
        return newsRepository.findByCategoryOrderByDatetimeAsc(category, pageable);
    }
}




