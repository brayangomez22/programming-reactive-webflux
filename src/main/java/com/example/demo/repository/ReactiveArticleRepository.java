package com.example.demo.repository;

import com.example.demo.model.Article;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

public class ReactiveArticleRepository implements ArticleRepository{
    @Override
    public Flux<Article> findAll() {
        return Flux.interval(Duration.ofSeconds(1))
                .onBackpressureDrop()
                .map(this::generateArticle)
                .flatMapIterable(x -> x);
    }

    private List<Article> generateArticle(long interval) {

        Article obj = new Article()

    }
}
