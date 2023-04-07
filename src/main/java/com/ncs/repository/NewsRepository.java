package com.ncs.repository;

import com.ncs.dto.NewsDto;
import com.ncs.entity.News;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@EnableJpaRepositories(basePackages = "com.ncs.repository")
public interface NewsRepository extends JpaRepository<News, Long> {

    NewsDto findByTitle(String title);

    List<News> findTop2ByCategory(String category);

    @Query("SELECT n FROM News n WHERE (n.title LIKE %:keyword% OR n.text LIKE %:keyword%) ORDER BY CAST(n.datetime AS timestamp) DESC ")
    List<News> findByKeywordOrderByDatetimeDesc(@Param("keyword") String keyword);

}


