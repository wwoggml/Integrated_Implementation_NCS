package com.ncs.repository;

import com.ncs.dto.NewsDto;
import com.ncs.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NewsRepository extends JpaRepository<News, Long> {

    NewsDto findByTitle(String title);
}

