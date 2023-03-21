package com.ncs.controller;

import com.ncs.dto.NewsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
@Log4j2
public class CrawlingController {

    @GetMapping("/craw")
    public String SearchURL() {
        return "CrawlingSearch";
    }
    @PostMapping("/craw")
    public String SearchNews(HttpServletRequest httpServletRequest, HttpServletResponse response, Model model) throws IOException {

        String URL = httpServletRequest.getParameter("url");

        Connection conn = Jsoup.connect(URL);
        ArrayList arr = new ArrayList();

        String htmlText = null;
        try {
            Document document = conn.get();
            Elements imageUrlElements = document.getElementsByClass("go_trans _article_content");

            for (Element element : imageUrlElements) {
//                arr.add(element.attr("abs:data-src"));
                arr.add(element);
            }
            htmlText = arr.toString().substring(1, arr.toString().length() - 1);

        } catch (IOException e) {
            e.printStackTrace();
        }

        String changeText = htmlText.replace("data-src", "src");

        model.addAttribute("htmlText",changeText);
        return "Crawling";
    }

    @GetMapping("/craw2")
    public String SearchURL2(Model model) {


        String URL = "https://news.naver.com/main/list.naver?mode=LS2D&mid=shm&sid1=105&sid2=731";
        NewsDto newsDto = new NewsDto();
        ArrayList<NewsDto> dtoList = new ArrayList<NewsDto>();

        Connection conn = Jsoup.connect(URL);
//        ArrayList<String> arrs = new ArrayList<String>();
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
                newsDto.setAuthor(author.text());
                newsDto.setImageURL(imageurl);
                newsDto.setDate(date.text());
                newsDto.setText(text.text());
                dtoList.add(newsDto);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

//        GetNews(arrs, model);
        model.addAttribute("dtoList", dtoList);
//        return "CrawlingFor";
        return "testCraw";
    }




    public String GetNews(ArrayList<String> urlList, Model model) {
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
                newsDto.setAuthor(author.text());
                newsDto.setImageURL(imageurl);
                newsDto.setDate(date.text());
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


        model.addAttribute("dtoList", dtoList);
//        return "CrawlingFor";
        return "testCraw.html";
    }



}
