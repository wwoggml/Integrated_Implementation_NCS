package com.ncs.controller;

import com.ncs.dto.NewsDto;
import com.ncs.repository.NewsRepository;
import com.ncs.service.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/news")
public class ResultController {
    private final NewsService newsService;

    @Autowired
    NewsRepository newsRepository;

    @GetMapping("/search")
    public String SearchURL() {
        return "CrawlingSearch";
    }
    @GetMapping("/result")
    public String NewsResult(Model model) {
        List<NewsDto> newsDtoListlist = newsService.getAll();
        List<String> imageURL = new ArrayList<>();
        ArrayList list = new ArrayList<>();



        for(int i = 0; i<newsDtoListlist.size(); i++) {
            newsDtoListlist.get(i).setImageURL(newsDtoListlist.get(i).getImageURL().split(",")[0]);
        }


        model.addAttribute("newsDtoListlist", newsDtoListlist);

        return "testCraw";

    }
}
