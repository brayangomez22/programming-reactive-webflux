package com.example.demo.repository;

import com.example.demo.model.Article;
import reactor.core.publisher.Flux;

public interface ArticleRepository {

    Flux<Article> findAll();

}
