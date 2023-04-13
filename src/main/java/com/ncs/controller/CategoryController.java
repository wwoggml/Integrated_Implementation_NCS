package com.ncs.controller;

import com.ncs.entity.News;
import com.ncs.service.NewsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Log4j2
@Controller
public class CategoryController {

    @Autowired
    NewsService newsService;

    @GetMapping("/category")
    public String Category(HttpServletRequest httpServletRequest, Model model,
                           @RequestParam(defaultValue = "10") int size,
                           @RequestParam(defaultValue = "101") int sid,
                           @RequestParam(value = "page", defaultValue = "1") int page) {

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<News> list = null;

        if(sid == 100) list = newsService.searchCategory("정치", pageable);
        else if(sid == 101) list = newsService.searchCategory("경제", pageable);
        else if(sid == 102) list = newsService.searchCategory("스포츠", pageable);
        else if(sid == 103) list = newsService.searchCategory("생활/문화", pageable);
        else if(sid == 104) list = newsService.searchCategory("IT/과학", pageable);

        model.addAttribute("list",list);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", list.getTotalPages());

        return "SearchCategory1";
    }

    @GetMapping("/economy")
    public String Economy(HttpServletRequest httpServletRequest, Model model,
                          @RequestParam(defaultValue = "10") int size,
                          @RequestParam(value = "page", defaultValue = "1") int page) {

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<News> list = newsService.searchCategory("경제", pageable);

        model.addAttribute("list",list);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", list.getTotalPages());

        return "SearchCategory1";
    }

    @GetMapping("/life_culture")
    public String Life_culture(HttpServletRequest httpServletRequest, Model model,
                               @RequestParam(defaultValue = "10") int size,
                               @RequestParam(value = "page", defaultValue = "1") int page) {

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<News> list = newsService.searchCategory("생활/문화", pageable);

        model.addAttribute("list",list);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", list.getTotalPages());

        return "SearchCategory2";
    }

    @GetMapping("/it_science")
    public String It_Science(HttpServletRequest httpServletRequest, Model model,
                             @RequestParam(defaultValue = "10") int size,
                             @RequestParam(value = "page", defaultValue = "1") int page) {

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<News> list = newsService.searchCategory("IT/과학", pageable);

        model.addAttribute("list", list);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", list.getTotalPages());

        return "SearchCategory3";
    }
}
