package com.ncs.service;


import com.ncs.dto.NewsDto;
import com.ncs.dto.TopKeywordDto;
import com.ncs.entity.News;
import com.ncs.entity.TopKeyword;
import com.ncs.repository.TopKeywordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class TopKeywordService {

    private final TopKeywordRepository topKeywordRepository;

    public void saveSearchKeyword(String keyword) {
        TopKeyword topKeyword = topKeywordRepository.findByKeyword(keyword);
        if (topKeyword == null) {
            topKeyword = new TopKeyword(keyword, 1, LocalDateTime.now());
        } else {
            topKeyword.setCount(topKeyword.getCount() + 1);
        }
        topKeywordRepository.save(topKeyword);
    }

    public List<TopKeyword> getTopKeywords() {
        return topKeywordRepository.findTop10ByOrderByCountDesc();
    }
}
