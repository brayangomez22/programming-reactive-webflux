package com.example.demo.repository;

import com.example.demo.model.Article;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ArticleRepository {

    Flux<Article> findAll();
    Flux<Article> findPerPage();
    Flux<Article> findOne();

    Flux<Object> findModified();
    Flux<String> findZip();
    Flux<String> findZipWith();

    Mono<Object> findDefault();

}
