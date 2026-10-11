package com.example.newsapp.service;

import com.example.newsapp.model.Article;
import com.example.newsapp.model.NewsApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Service
public class NewsApiService {

    private final RestTemplate restTemplate;
    private final String apiKey;
    private final String baseUrl;

    public NewsApiService(
            RestTemplate restTemplate,
            @Value("${newsapi.api.key:}") String apiKey,
            @Value("${newsapi.api.base-url:https://newsapi.org/v2}") String baseUrl
    ) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
    }

    public boolean isApiKeyConfigured() {
        return apiKey != null && !apiKey.isBlank();
    }

    public List<Article> getHeadlines(String category, String query) {
        if (!isApiKeyConfigured()) {
            return Collections.emptyList();
        }

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(baseUrl + "/top-headlines")
                .queryParam("country", "us")
                .queryParam("pageSize", 20);

        if (category != null && !category.isBlank()) {
            uriBuilder.queryParam("category", category);
        }

        if (query != null && !query.isBlank()) {
            uriBuilder.queryParam("q", query);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", apiKey);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<NewsApiResponse> response = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                entity,
                NewsApiResponse.class
        );

        if (response.getBody() == null || response.getBody().getArticles() == null) {
            return Collections.emptyList();
        }

        return response.getBody().getArticles();
    }
}
