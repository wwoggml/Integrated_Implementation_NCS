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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@Log4j2
public class NewsController {

    private final NewsService newsService;

    @Autowired
    NewsRepository newsRepository;

    @GetMapping("/craw2")
    public void SearchURL2() {


        String URL = "https://news.naver.com/main/list.naver?mode=LS2D&mid=shm&sid1=100&sid2=264";
        NewsDto newsDto = new NewsDto();
        ArrayList<NewsDto> dtoList = new ArrayList<NewsDto>();

        Connection conn = Jsoup.connect(URL);
        String url = "";

        try {
            Document document = conn.get();
            Elements urlTest = document.select("dt");

            for (Element element : urlTest) {
                ArrayList imageurl = new ArrayList<>();
//                arrs.add(element.select("a").attr("abs:href"));
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
                dtoList.add(newsDto);

                newsService.saveNews(newsDto);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

//        model.addAttribute("dtoList", dtoList);
//        return "testCraw";
    }




    public void GetNews(ArrayList<String> urlList) {
        NewsDto newsDto = new NewsDto();
        ArrayList<NewsDto> dtoList = new ArrayList<NewsDto>();


        for(String URL : urlList) {
            Connection conn = Jsoup.connect(URL);
            ArrayList imageurl = new ArrayList<>();
            try {
                Document document = conn.get();

                Elements title = document.getElementsByClass("media_end_head_headline");
                Elements author = document.getElementsByClass("byline_s");
                Elements date = document.getElementsByClass("media_end_head_info_datestamp_time");
                Elements imageUrl = document.getElementsByClass("nbd_a _LAZY_LOADING_ERROR_HIDE");
                Elements text = document.getElementsByClass("go_trans _article_content");


                for(Element element : imageUrl) {
                    imageurl.add(element.select("img").attr("abs:data-src"));
                }


                newsDto.setUrl((String) URL);
                newsDto.setTitle(title.text());
                newsDto.setReporter(author.text());
                newsDto.setImageURL(imageurl);
                newsDto.setDatetime(date.text());
                newsDto.setText(text.text());
                dtoList.add(newsDto);

            } catch (IOException e) {
                e.printStackTrace();
            }
//
//            log.info("================================================");
//            log.info(newsDto.getUrl());
//            log.info(newsDto.getTitle());
//            log.info(newsDto.getAuthor());
//            log.info(newsDto.getDate());
//            log.info(newsDto.getImageURL());
//            log.info(newsDto.getText());
        }


        log.info("================");
        log.info(dtoList);
        log.info("================");


//        model.addAttribute("dtoList", dtoList);
//        return "CrawlingFor";
//        return "testCraw.html";
    }
}
