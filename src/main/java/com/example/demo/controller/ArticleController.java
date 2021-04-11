package com.example.demo.controller;

import com.example.demo.model.Article;
import com.example.demo.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping(path = "/article/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Article> fedd() {
        return this.articleRepository.findAll();
    }

    @GetMapping(path = "/article/perPage", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Article> feddTwo() {
        return this.articleRepository.findPerPage();
    }

    @GetMapping(path = "/article/one", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Article> feddThree() {
        return this.articleRepository.findOne();
    }

    @GetMapping(path = "/article/modified", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Object> feddFour() {
        return this.articleRepository.findModified();
    }

    @GetMapping(path = "/article/mergeZip", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> feddFive() {
        return this.articleRepository.findMergeZip();
    }

    @GetMapping(path = "/article/mergeZipWith", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> feddSix() {
        return this.articleRepository.findMergeZipWith();
    }

    @GetMapping(path = "/article/default", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<Object> feddSeven() {
        return this.articleRepository.findDefault();
    }

}
