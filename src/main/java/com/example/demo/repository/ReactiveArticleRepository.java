package com.example.demo.repository;

import com.example.demo.model.Article;
import com.example.demo.utils.ArticleGenerator;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@Repository
public class ReactiveArticleRepository implements ArticleRepository{
    @Override
    public Flux<Article> findAll() {
        return Flux.interval(Duration.ofSeconds(1))
                .onBackpressureDrop()
                .map(this::generateArticle)
                .flatMapIterable(x -> x);
    }

    private List<Article> generateArticle(long interval) {
        ArticleGenerator articleGenerator = new ArticleGenerator();

        articleGenerator.extractHTML();

        Article obj = new Article(articleGenerator.randomTitlePost(), articleGenerator.randomAuthor(), articleGenerator.getDate());
        return Arrays.asList(obj);
    }
}
