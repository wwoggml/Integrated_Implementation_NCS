package com.ncs.service;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.AnalyzeRequest;
import org.elasticsearch.client.indices.AnalyzeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {
    @Autowired
    private RestHighLevelClient client;

    public List<String> analyzeText(String text) throws IOException {
        AnalyzeRequest request = AnalyzeRequest.withIndexAnalyzer("news_test", "nori", text);
        AnalyzeResponse response = client.indices().analyze(request, RequestOptions.DEFAULT);
        List<String> tokens;
        tokens = new ArrayList<>();
        response.getTokens().forEach(token -> tokens.add(token.getTerm()));
        return tokens;
    }
}

