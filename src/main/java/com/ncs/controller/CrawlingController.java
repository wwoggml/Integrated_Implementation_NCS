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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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




}
