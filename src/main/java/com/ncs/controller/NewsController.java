package com.ncs.controller;


import com.ncs.dto.NewsDto;
import com.ncs.entity.News;
import com.ncs.repository.NewsRepository;
import com.ncs.service.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;

    @Autowired
    NewsRepository newsRepository;

    @GetMapping("/search")
    public String SearchURL() {
        return "CrawlingSearch";
    }

    @GetMapping("/add")
    public String SearchURL2(Model model) {


        String URL = "https://news.naver.com/main/list.naver?mode=LS2D&mid=shm&sid1=105&sid2=228";

        String category = "";

        NewsDto newsDto = new NewsDto();
        ArrayList<NewsDto> dtoList = new ArrayList<NewsDto>();

        Connection conn = Jsoup.connect(URL);
        String imageURL = "";
        int index = 0;

        try {
            Document document = conn.get();
//            Elements urlTest = document.getElementsByClass("photo");
            Elements urlTest = document.select("dl > dt").not(".photo");


                document.select("dt");
            for (Element element : urlTest) {
                index = 0;
                imageURL = "";
                List<String> imageurl = new ArrayList<>();
                URL = element.select("a").attr("abs:href");
                conn = Jsoup.connect(URL);

                document = conn.get();

                Elements title = document.getElementsByClass("media_end_head_headline");
                Elements author = document.getElementsByClass("byline_s");
                Elements date = document.getElementsByClass("media_end_head_info_datestamp_time");
                Elements imageUrl = document.getElementsByClass("nbd_a _LAZY_LOADING_ERROR_HIDE");
                Elements text = document.getElementsByClass("go_trans _article_content");


                if(imageUrl.size() == 0) imageURL = "";
                for(Element ele : imageUrl) {
                    if(index == 0) imageURL += ele.select("img").attr("abs:data-src");
                    else imageURL += ", " + ele.select("img").attr("abs:data-src");
                    index++;
                }


                if(URL.contains("sid=105"))
                    category = "IT/과학";
                else if(URL.contains("sid=101"))
                    category = "경제";
                else if(URL.contains("sid=103"))
                    category = "생활/문화";

                newsDto.setUrl((String) URL);
                newsDto.setTitle(title.text());
                newsDto.setReporter(author.text());
                newsDto.setImageURL(imageURL);
                newsDto.setCategory(category);
                newsDto.setDatetime(date.text());
                newsDto.setText(text.text());

                newsService.saveNews(newsDto);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to save database (ERROR : " + e.getMessage() + ")";
        }
        return "Save Database Successful!";
    }

}
