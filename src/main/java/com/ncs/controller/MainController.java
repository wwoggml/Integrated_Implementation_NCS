package com.ncs.controller;

import com.ncs.entity.News;
import com.ncs.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    @Autowired
    NewsService newsService;

    @GetMapping("/")
    public String main(Model model) {
        List<News> sid_100_list = newsService.getFiveEntities("정치");
        List<News> sid_101_list = newsService.getFiveEntities("경제");
        List<News> sid_102_list = newsService.getFiveEntities("스포츠");
        List<News> sid_103_list = newsService.getFiveEntities("생활/문화");
        List<News> sid_104_list = newsService.getFiveEntities("IT/과학");

        model.addAttribute("sid_100_list", sid_100_list);
        model.addAttribute("sid_101_list", sid_101_list);
        model.addAttribute("sid_102_list", sid_102_list);
        model.addAttribute("sid_103_list", sid_103_list);
        model.addAttribute("sid_104_list", sid_104_list);
        return "Main";
    }
}
