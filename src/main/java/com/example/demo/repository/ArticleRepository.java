package com.example.demo.repository;

import com.example.demo.model.Article;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ArticleRepository {

    Flux<Article> findAll();
    Flux<Article> findPerPage();
    Flux<Article> findOne();

    Flux<Object> findModified();
    Flux<String> findMergeZip();
    Flux<String> findMergeZipWith();

    Mono<Object> findDefault();

}
