package com.ncs.controller;

import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;


@Controller
@RequiredArgsConstructor
public class MiningController {

    @GetMapping("/mining")
    public String MiningTest(){
        return "Mining";
    }


    @PostMapping("/mining")
    @ResponseBody
    public String[] getURL(String URL) throws IOException {
        Document doc = Jsoup.connect(URL).get();
        String[] splited =doc.text().split(" ");
        System.out.println(splited);
        return splited;
    }
}
