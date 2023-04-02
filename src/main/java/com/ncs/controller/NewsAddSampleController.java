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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/news")
public class NewsAddSampleController {

    private final NewsService newsService;

    @Autowired
    NewsRepository newsRepository;

    @PostMapping("/add")
    public String SearchURL2(HttpServletRequest httpServletRequest, HttpServletResponse response, Model model) throws IOException {

        String URL = httpServletRequest.getParameter("url");
        CrawlingCategory(URL);

        return "Save Database Successful!";
    }

    public String CrawlingCategory(String URL) {
        Connection conn = Jsoup.connect(URL);
        Elements TestURL = null;
        int index = 0;
        String imageURL = "", category = "";
        NewsDto newsDto = new NewsDto();
        ArrayList<NewsDto> dtoList = new ArrayList<NewsDto>();

        try {
            Document document = conn.get();

            if(URL.contains("mode=LSD"))
                TestURL = document.select(".nav li a");
            else if (URL.contains("mode=LS2D"))
                TestURL = document.select("dl > dt").not(".photo");
            else {
                conn = Jsoup.connect(URL);
                document = conn.get();

                Elements title = document.getElementsByClass("media_end_head_headline");
                Elements author = document.getElementsByClass("byline_s");
                Elements date = document.getElementsByClass("media_end_head_info_datestamp_time");
                Elements imageUrl = document.getElementsByClass("nbd_a _LAZY_LOADING_ERROR_HIDE");
                Elements img_desc = document.getElementsByClass("img_desc");
                Elements text = document.getElementsByClass("go_trans _article_content");

                List<String> imageurl = new ArrayList<>();
                if(imageUrl.size() == 0) imageURL = "";

                for(Element ele : imageUrl) {
                    if(index == 0) imageURL += ele.select("img").attr("abs:data-src");
                    index++;
                }

                if(URL.contains("sid=105"))
                    category = "IT/과학";
                else if(URL.contains("sid=101"))
                    category = "경제";
                else if(URL.contains("sid=103"))
                    category = "생활/문화";

                String dateString = date.text().split(" ")[0] + " " + date.text().split(" ")[1] + " " + date.text().split(" ")[2];


                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd. a hh:mm");
                Date timestamp = dateFormat.parse(dateString);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
                ZonedDateTime dateTime = ZonedDateTime.parse(timestamp.toString(), formatter);
                String iso8601String = dateTime.toOffsetDateTime().toString();



                newsDto.setUrl((String) URL);
                newsDto.setTitle(title.text());
                newsDto.setReporter(author.text());
                newsDto.setImageURL(imageURL);
                newsDto.setCategory(category);
                newsDto.setImg_desc(img_desc.text());
                newsDto.setDatetime(iso8601String);
                newsDto.setText(text.text());

                newsService.saveNews(newsDto);
                return "Save Database Successful!";
            }


            for (Element element : TestURL) {
                URL = element.select("a").attr("abs:href");
                CrawlingCategory(URL);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to save database (ERROR : " + e.getMessage() + ")";
        }
        return "Save Database Successful!";
    }


}
