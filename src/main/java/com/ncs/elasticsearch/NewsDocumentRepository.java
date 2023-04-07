package com.ncs.elasticsearch;



import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import org.springframework.data.elasticsearch.annotations.Query;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.util.List;

@EnableElasticsearchRepositories(basePackages = "com.ncs.elasticsearch")
public interface NewsDocumentRepository extends ElasticsearchRepository<NewsDocument, String> {

    @Query("{\"bool\":{\"should\":[{\"match\":{\"title\":\"?0\"}},{\"match\":{\"text\":\"?0\"}}]}}")
    List<NewsDocument> findByTitleOrText(String keyword);


    @Query("{\"bool\":{\"should\":[{\"match\":{\"title\":\"?0\"}},{\"match\":{\"text\":\"?0\"}}]}}")
    Page<NewsDocument> findByTitleOrText(String keyword,Pageable pageable);
}
