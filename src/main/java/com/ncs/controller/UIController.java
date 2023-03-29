package com.ncs.controller;

import com.ncs.dto.NewsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/ui/")
public class UIController {
    @GetMapping("/result")
    public String SearchResult(Model model)
    {
        List<NewsDto> arr = new ArrayList<NewsDto>();
        NewsDto newsDto = new NewsDto();
        for(int i = 0; i<10; i++) {
            newsDto.setUrl("https://n.news.naver.com/mnews/article/015/0004826255?sid=105");
            newsDto.setTitle("엔케이맥스, NK세포치료제 파킨슨병 치료목적 첫 투약 개시");
            newsDto.setImageURL("https://imgnews.pstatic.net/image/023/2023/03/29/0003754614_001_20230329113001066.jpg?type=w647");
            newsDto.setReporter("박인혁 기자 hyuk@hankyung.com");
            newsDto.setDatetime("2023.03.29. 오전 11:30");
            newsDto.setText("ITITITITITITITITITITITITITITITITITITITITITITITITITITITITITITITITITITIT");
            newsDto.setCategory(" IT/과학");

            arr.add(newsDto);
        }

        model.addAttribute("arr", arr);
        return "SearchResult";
    }
}
