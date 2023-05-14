package com.ncs.repository;

import com.ncs.entity.TopKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories(basePackages = "com.ncs.repository")
public interface TopKeywordRepository extends JpaRepository<TopKeyword, Long> {
    TopKeyword findByKeyword(String keyword);
    List<TopKeyword> findTop10ByOrderByCountDesc(); //상위 10개
}
