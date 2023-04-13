package com.ncs.controller;


import com.ncs.service.TestService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/test")
    public List<String> test() throws IOException {
        return testService.analyzeText("경기도 제공 해외투자 유치와 청년기회 확대 등을 위해 미국을 방문 중인 김동연 경기도지사가 10일 오후 (현지시간) 미시간대학교 부설 ‘남 한국학센터’ 초청으로 현지에서 강연했다. 남 한국학센터는 고 남상용 장로와 남문숙 여사의 기부로 설립된 곳으로, 2010년 미국에서 처음으로 한국학센터로 명명됐다. 김 지사가 취임 후 외국 대학교에서 강연하기는 이번이 처음으로, ‘유쾌한 반란:21세기 한국을 위한 제안’이라는 주제로 강의했다. 강연장인 뮤지엄 아트 오디토리엄 200석 가까운 좌석이 국내외 유학생과 교직원들로 가득 메워지는 등 1시간여 진행된 강의는 열기로 가득했다. 김동연 지사는 유년기부터 부총리 때까지 인생 역정을 반추한 후 “나 자신에 대해 반란을 일으키라. 사회에 대한 반란을 일으키라”며 ‘유쾌한 반란’을 주문했다. 유쾌한 반란은 김 지사가 2019년 12월 인재 양성과 사회 기여 활동을 위해 설립한 비영리 법인명이기도 하다. 경기도 제공 김 지사는 ‘대한민국 금기깨기’를 거듭 주장하며 유쾌한 반란과 대한민국 금기깨기를 연결 짓기도 했다. 그는 ‘추격경제의 금기 깨기’, ‘세습사회의 금기 깨기’, ‘기득권정치의 금기 깨기’ 등 3가지를 대한민국 금기 깨기로 내세웠다. 그는 세습사회 금기 깨기의 경우 지론인 ‘계층이동 사다리 구축’을, 기득권정치 금기 깨기를 위해서는 ‘국회의원 면책 특권 폐지’등을 예로 들었다. 김동연 지사는 “경기도는 한국서 가장 큰 지자체로 전 인구 4분의 1이 살고 경제·산업의 허브”라며 “경기도가 바뀌면 대한민국이 바뀐다. 도지사를 하는 동안 대한민국을 바꾸는 기반과 모멤텀을 만들겠다”고 말했다. 김 지사는 “미시간대 졸업생으로서 열심히 해서 대한민국을 바꾸겠다. 미시간대의 명예를 드높이겠다”며 “정치적 다른 뜻 없이 사심 없이 잘해보겠다”고 말했다. 미시간대는 김동연 지사가 1990년대 공공정책학 석·박사과정을 수료한 인연이 깊은 곳이다. 김동연 지사는 올해로 12회째인 ‘남상용 어워드’ 시상도 했다. 수상자는 남 한국학센터 활동에도 참여한 올리비아 다니엘로, 학문적 성과와 지역사회 봉사 등 공로로 상을 받았다");
    }
}
