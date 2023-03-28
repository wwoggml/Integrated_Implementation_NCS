package com.ncs.controller;


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
public class CrawlingForController {

    @GetMapping("/crawfor")
    public String CrawlingFor() {
        return "CrawlingForSearch";
    }

    @PostMapping("/crawfor")
    public String CrawlingFor(HttpServletRequest httpServletRequest, HttpServletResponse response, Model model) throws IOException {

        String URL = httpServletRequest.getParameter("url");

        Connection conn = Jsoup.connect(URL);
        ArrayList arrs = new ArrayList();

        try {
            Document document = conn.get();
            Elements urlTest = document.select("dt");

            for (Element element : urlTest) {
                arrs.add(element.select("a").attr("abs:href"));

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        model.addAttribute("arrs",arrs);
        return "CrawlingFor";
    }

}
