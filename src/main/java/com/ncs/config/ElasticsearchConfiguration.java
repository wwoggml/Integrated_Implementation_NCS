package com.ncs.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

@Configuration
public class ElasticsearchConfiguration {

    @Bean
    public RestHighLevelClient client() {
        RestClientBuilder builder = RestClient.builder(
                new HttpHost("localhost", 9200, "http"),
                new HttpHost("192.168.0.5",9200,"http"));

        RestHighLevelClient client = new RestHighLevelClient(builder);
        return client;
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchRestTemplate(client());
    }
}
