package com.example.newsapp.controller;

import com.example.newsapp.model.Article;
import com.example.newsapp.service.NewsApiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class NewsController {

    private final NewsApiService newsApiService;

    public NewsController(NewsApiService newsApiService) {
        this.newsApiService = newsApiService;
    }

    @GetMapping("/")
    public String index(
            @RequestParam(value = "category", required = false, defaultValue = "general") String category,
            @RequestParam(value = "query", required = false) String query,
            Model model
    ) {
        List<Article> articles = newsApiService.getHeadlines(category, query);

        model.addAttribute("articles", articles);
        model.addAttribute("selectedCategory", category);
        model.addAttribute("searchTerm", query == null ? "" : query);
        model.addAttribute("apiConfigured", newsApiService.isApiKeyConfigured());

        return "index";
    }
}
