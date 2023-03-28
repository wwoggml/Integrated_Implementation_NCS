package com.ncs.controller;

import com.ncs.service.NaverApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Log4j2
public class NaverApiController {

    NaverApiService naverApiService;

    @GetMapping("/api")
    public String main(Model model) {
        return "NaverApiSearch";
    }

    @PostMapping("/api")
    public String SearchNews(HttpServletRequest httpServletRequest, Model model) throws ParseException {
        String clientId = "zwvvcSwJFxCgtvbJTnvm";
        String clientSecret = "qqkxIC0KAZ";
        String query = httpServletRequest.getParameter("keyword");

        try {
            query = URLEncoder.encode(query, "UTF-8");
        }catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }


        String apiURL = "https://openapi.naver.com/v1/search/news.json?query=" + query;

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = naverApiService.get(apiURL, requestHeaders);
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(responseBody);


        log.info("===============JSONObject===============");
        log.info(jsonObject);

        Long total = (Long) jsonObject.get("total");
        String lastBuildDate = (String) jsonObject.get("lastBuildDate");
        Long display = (Long) jsonObject.get("display");
        Long start = (Long) jsonObject.get("start");
        JSONArray items = (JSONArray) jsonObject.get("items");

        log.info("total = " + total);
        log.info("lastBuildDate = " + lastBuildDate);
        log.info("display = " + display);
        log.info("start = " + start);
        log.info("items = " + items);

        model.addAttribute("total", total);
        model.addAttribute("lastBuildDate", lastBuildDate);
        model.addAttribute("display", display);
        model.addAttribute("start", start);
        model.addAttribute("items", items);

        return "NaverApi";
    }



}
