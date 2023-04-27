package com.ncs.controller;

import com.ncs.entity.News;
import com.ncs.service.NewsService;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Log4j2
@Controller
public class CategoryController {

    @Autowired
    NewsService newsService;

    @GetMapping("/category")
    public String Category(HttpServletRequest httpServletRequest, Model model,
                           @RequestParam(defaultValue = "10") int size,
                           @RequestParam(defaultValue = "101") int sid,
                           @RequestParam(value = "page", defaultValue = "1") int page) {

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<News> list = null;

        if(sid == 100) list = newsService.searchCategory("정치", pageable);
        else if(sid == 101) list = newsService.searchCategory("경제", pageable);
        else if(sid == 102) list = newsService.searchCategory("스포츠", pageable);
        else if(sid == 103) list = newsService.searchCategory("생활/문화", pageable);
        else if(sid == 104) list = newsService.searchCategory("IT/과학", pageable);

        model.addAttribute("list",list);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", list.getTotalPages());

        return "SearchCategory1";
    }

    @GetMapping("/economy")
    public String Economy(HttpServletRequest httpServletRequest, Model model,
                          @RequestParam(defaultValue = "10") int size,
                          @RequestParam(value = "page", defaultValue = "1") int page) {

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<News> list = newsService.searchCategory("경제", pageable);

        model.addAttribute("list",list);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", list.getTotalPages());

        return "SearchCategory1";
    }

    @GetMapping("/life_culture")
    public String Life_culture(HttpServletRequest httpServletRequest, Model model,
                               @RequestParam(defaultValue = "10") int size,
                               @RequestParam(value = "page", defaultValue = "1") int page) {

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<News> list = newsService.searchCategory("생활/문화", pageable);

        model.addAttribute("list",list);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", list.getTotalPages());

        return "SearchCategory2";
    }

    @GetMapping("/it_science")
    public String It_Science(HttpServletRequest httpServletRequest, Model model,
                             @RequestParam(defaultValue = "10") int size,
                             @RequestParam(value = "page", defaultValue = "1") int page) {

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<News> list = newsService.searchCategory("IT/과학", pageable);

        model.addAttribute("list", list);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", list.getTotalPages());

        return "SearchCategory3";
    }

    @GetMapping("/category/keyword")
    public String CategoryKeyword(@RequestParam(defaultValue = "100") int sid, Model model) throws IOException {
        RestClientBuilder builder = RestClient.builder(
                new HttpHost("localhost", 9200, "http"));

        RestHighLevelClient client = new RestHighLevelClient(builder);


        SearchRequest searchRequest = new SearchRequest("news_test");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        String category = "";
        if (sid == 100) category = "정치";
        else if (sid == 101) category = "경제";
        else if (sid == 102) category = "스포츠";
        else if (sid == 103) category = "생활/문화";
        else if (sid == 104) category = "IT/과학";

        searchSourceBuilder.query(QueryBuilders.matchQuery("category", category));

        // 집계 쿼리
        searchSourceBuilder.aggregation(
                AggregationBuilders.terms("top_words")
                        .field("text")
                        .size(100)
                        .order(BucketOrder.count(false))
        );

        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);


        // 검색 결과 처리
        long totalHits = searchResponse.getHits().getTotalHits().value;
        JSONArray jsonArray = new JSONArray();

        // 집계 결과 처리
        Terms topWords = searchResponse.getAggregations().get("top_words");
        for (Terms.Bucket bucket : topWords.getBuckets()) {
            String word = bucket.getKeyAsString();
            long count = bucket.getDocCount();

            JSONObject jsonItem = new JSONObject();
            jsonItem.put("x", word);
            jsonItem.put("value", count);
            jsonArray.add(jsonItem);
        }

        model.addAttribute("jsonArray", jsonArray.toString());
        return "CategoryKeyword";
    }
}
