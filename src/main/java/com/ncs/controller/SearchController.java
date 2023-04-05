package com.ncs.controller;

import com.ncs.elasticsearch.NewsDocument;
import com.ncs.elasticsearch.NewsDocumentRepository;
import com.ncs.entity.News;
import com.ncs.service.NewsService;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;



@Log4j2

@Controller
public class SearchController {

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private NewsDocumentRepository newsDocumentRepository;

    @Autowired
    NewsService newsService;


    @GetMapping("/search")
    public String test(HttpServletRequest httpServletRequest, Model model,
                       @RequestParam(defaultValue = "10") int size,
                       @RequestParam(value = "page", defaultValue = "1") int page) throws UnsupportedEncodingException {
        String keyword = httpServletRequest.getParameter("keyword");

        try {
            // URL 디코딩 수행
            keyword = URLDecoder.decode(keyword, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            // 디코딩 실패 시 예외 처리
        }

                log.info("keyword = " + keyword);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<NewsDocument> news = newsService.searchNews(keyword, pageable);

        if (keyword == null) {
            return "SearchResult";
        }

        if (news.getContent().size() == 0) {
            String none_result = "검색 결과가 없습니다.";
            model.addAttribute("none_result", none_result);
            return "SearchResult";
        }

        model.addAttribute("news", news.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", news.getTotalPages());


        return "SearchResult";
    }


    @GetMapping("/Second")
    public String Second(Model model){

        return "asdfgh";
    }

    @GetMapping("/detail")
    public String Detail(HttpServletRequest httpServletRequest, Model model, @RequestParam Long id){
        List<News> newsDetail;
        newsDetail = newsService.getIdNews(id);

        model.addAttribute("newsDetail", newsDetail);
        return "NewsDetailPage";
    }

    @GetMapping("/main")
    public String main() {
        return "SearchMain";
    }

}