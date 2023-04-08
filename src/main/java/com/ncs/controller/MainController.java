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
    public String main() {
        return "RNSEMain";
    }

}
