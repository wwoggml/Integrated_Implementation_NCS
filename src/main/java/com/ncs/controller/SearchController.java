package com.ncs.controller;

import com.google.gson.*;
import com.ncs.dto.NewsDto;
import com.ncs.elasticsearch.NewsDocument;
import com.ncs.elasticsearch.NewsDocumentRepository;
import com.ncs.entity.News;
import com.ncs.service.NewsService;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Log4j2
//@RestController
@Controller
public class SearchController {

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private NewsDocumentRepository newsDocumentRepository;

    @Autowired
    NewsService newsService;

    @GetMapping("/d")
    public String search(Model model) throws IOException {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();


        List<NewsDto> arrs = new ArrayList<NewsDto>();

        boolQueryBuilder.should(
                QueryBuilders.matchQuery("title", "카카오"));

        boolQueryBuilder.should(
                QueryBuilders.matchQuery("text", "카카오"));

        sourceBuilder.query(boolQueryBuilder);

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.source(sourceBuilder);

        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(client.search(searchRequest, RequestOptions.DEFAULT).toString());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String prettyJsonString = gson.toJson(jsonElement);
        log.info("================result================");
        log.info(prettyJsonString);

        JsonObject jsonObject = jsonElement.getAsJsonObject().get("hits").getAsJsonObject();
        JsonArray jsonArray = (JsonArray) jsonObject.get("hits");

        int size = jsonElement.getAsJsonObject().get("hits").getAsJsonObject().get("total").getAsJsonObject().get("value").getAsInt();




        if(size == 0) {
            String none_result = "검색 결과가 없습니다.";
            model.addAttribute("none_result", none_result);
            return "SearchResult";
        }

        for (int i = 0; i < size; i++) {
            NewsDto newsDto = new NewsDto();
//            log.info("================================");
            newsDto.setText(jsonArray.get(i).getAsJsonObject().get("_source").getAsJsonObject().get("text").toString().replaceAll("\\\"","").replaceAll("\\\\",""));
            newsDto.setCategory(jsonArray.get(i).getAsJsonObject().get("_source").getAsJsonObject().get("category").toString().replaceAll("\\\"",""));
            newsDto.setTitle(jsonArray.get(i).getAsJsonObject().get("_source").getAsJsonObject().get("title").toString().replaceAll("\\\"","").replaceAll("\\\\",""));
            newsDto.setDatetime(jsonArray.get(i).getAsJsonObject().get("_source").getAsJsonObject().get("datetime").toString().replaceAll("\\\"",""));
            newsDto.setUrl(jsonArray.get(i).getAsJsonObject().get("_source").getAsJsonObject().get("url").toString().replaceAll("\\\"",""));
            newsDto.setImageURL(jsonArray.get(i).getAsJsonObject().get("_source").getAsJsonObject().get("imageurl").toString().replaceAll("\\\"",""));
            newsDto.setReporter(jsonArray.get(i).getAsJsonObject().get("_source").getAsJsonObject().get("reporter").toString().replaceAll("\\\"",""));

            arrs.add(newsDto);
        }

        model.addAttribute("arrs", arrs);
        return "SearchResult";
    }


    @GetMapping("/search")
    public String test(HttpServletRequest httpServletRequest, Model model,
                       @RequestParam(defaultValue = "10") int size,
                       @RequestParam(value="page", defaultValue="1") int page) {
        String keyword = httpServletRequest.getParameter("keyword");


        Pageable pageable = PageRequest.of(page - 1, size);
        Page<NewsDocument> news = newsService.searchNews(keyword, pageable);

        if(keyword == null) {
            return "SearchResult";
        }

        if(news.getContent().size() == 0) {
            String none_result = "검색 결과가 없습니다.";
            model.addAttribute("none_result", none_result);
            return "SearchResult";
        }

        model.addAttribute("news", news.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", news.getTotalPages());


        return "SearchResult";
    }
}