package com.ncs.controller;

import com.ncs.dto.NewsDto;
import com.ncs.elasticsearch.NewsDocument;
import com.ncs.elasticsearch.NewsDocumentRepository;
import com.ncs.entity.News;
import com.ncs.repository.NewsRepository;
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
    private NewsRepository newsRepository;
    @Autowired
    NewsService newsService;

    @GetMapping("/search")
    public String test(HttpServletRequest httpServletRequest, Model model,
                       @RequestParam(defaultValue = "10") int size,
                       @RequestParam(defaultValue = "1") int sort,
                       @RequestParam(value = "page", defaultValue = "1") int page) throws UnsupportedEncodingException {
        String keyword = httpServletRequest.getParameter("keyword");

        try {
            // URL 디코딩 수행
            keyword = URLEncoder.encode(keyword, "UTF-8");
            keyword = keyword.replaceAll("%08", "");
            keyword = URLDecoder.decode(keyword, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }


        Pageable pageable = PageRequest.of(page - 1, size);
//        Page<NewsDocument> news = newsService.searchNews(keyword, pageable);
//        Page<NewsDocument> news = newsService.searchNewsByTitleOrText(keyword, pageable);

        Page<News> news = null;

        if(sort == 1) {
            news = newsService.getNews(keyword, pageable);
        }else if(sort == 2) {
            news = newsService.getSortDesc(keyword, pageable);
        }else {
            news = newsService.getSortAsc(keyword, pageable);
        }

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

        List<News> itCategory = newsService.getTwoEntities("IT/과학");
        List<News> cultureCategory = newsService.getTwoEntities("생활/문화");
        List<News> economyCategory = newsService.getTwoEntities("경제");

        model.addAttribute("itCategory", itCategory);
        model.addAttribute("economyCategory", economyCategory);
        model.addAttribute("cultureCategory", cultureCategory);
        model.addAttribute("newsDetail", newsDetail);
        return "NewsDetailPage";
    }

    @GetMapping("/main")
    public String searchMain() {
        return "SearchMain";
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

}