package com.ncs.controller;

import com.ncs.entity.TopKeyword;
import com.ncs.repository.TopKeywordRepository;
import com.ncs.service.TopKeywordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
public class TopKeywordController {

    private final TopKeywordService topKeywordService;

    @Autowired
    TopKeywordRepository topKeywordRepository;

    @GetMapping("/topkeywords")
    @ResponseBody
    public List<TopKeyword> getTopKeywordsAjax() {
        return topKeywordService.getTopKeywords();
    }
}
