package com.ncs.controller;


import com.ncs.dto.NewsDto;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;

    @Autowired
    NewsRepository newsRepository;

    @GetMapping("/add")
    public void SearchURL2(Model model) {


        String URL = "https://news.naver.com/main/list.naver?mode=LS2D&mid=shm&sid1=100&sid2=264";
        NewsDto newsDto = new NewsDto();
        ArrayList<NewsDto> dtoList = new ArrayList<NewsDto>();

        Connection conn = Jsoup.connect(URL);
        String url = "";

        try {
            Document document = conn.get();
            Elements urlTest = document.getElementsByClass("photo");

            log.info(urlTest);

            for (Element element : urlTest) {
                ArrayList imageurl = new ArrayList<>();
                url = element.select("a").attr("abs:href");
                conn = Jsoup.connect(url);

                document = conn.get();

                Elements title = document.getElementsByClass("media_end_head_headline");
                Elements author = document.getElementsByClass("byline_s");
                Elements date = document.getElementsByClass("media_end_head_info_datestamp_time");
                Elements imageUrl = document.getElementsByClass("nbd_a _LAZY_LOADING_ERROR_HIDE");
                Elements text = document.getElementsByClass("go_trans _article_content");


                for(Element ele : imageUrl) {
                    imageurl.add(ele.select("img").attr("abs:data-src"));
                }


                newsDto.setUrl((String) URL);
                newsDto.setTitle(title.text());
                newsDto.setReporter(author.text());
                newsDto.setImageURL(imageurl);
                newsDto.setDatetime(date.text());
                newsDto.setText(text.text());

                newsService.saveNews(newsDto);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
