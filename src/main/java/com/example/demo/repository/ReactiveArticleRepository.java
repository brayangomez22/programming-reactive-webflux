package com.example.demo.repository;

import com.example.demo.model.Article;
import com.example.demo.utils.ArticleGenerator;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
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

    @Override
    public Flux<Article> findPerPage() {
            return Flux.interval(Duration.ofSeconds(1))
                    .take(9)
                    .onBackpressureDrop()
                    .map(this::generateArticleTwo)
                    .flatMapIterable(x -> x);
    }

    @Override
    public Mono<Article> findOne() {
        ArticleGenerator articleGenerator = new ArticleGenerator();
        articleGenerator.extractHTML();

        return Mono.just(new Article(articleGenerator.randomTitlePost(), articleGenerator.randomAuthor(), articleGenerator.getDate()));
    }

    @Override
    public Flux<Object> findModified() {
        return Flux.interval(Duration.ofSeconds(1))
                .take(1)
                .onBackpressureDrop()
                .map(this::generateArticle)
                .map(articles -> Mono.just(articles.get(1).getTitle().concat(" El Brayan")));
    }

    @Override
    public Flux<Article> findModifiedFlatMap() {
        List<Article> articles1 = new ArrayList<>();
        ArticleGenerator articleGenerator = new ArticleGenerator();
        articleGenerator.extractHTML();

        for (int i = 0; i < 3; i ++) {
            articles1.add(new Article(articleGenerator.randomTitlePost(), articleGenerator.randomAuthor(), articleGenerator.getDate()));
        }

        return Flux.fromIterable(articles1)
                .flatMap(article -> {
                    article.setTitle(article.getTitle() + " Brayan");
                    return Mono.just(article);
                });
    }

    @Override
    public Flux<String> findMergeZip() {
        List<Article> articles1 = new ArrayList<>();
        List<Article> articles2 = new ArrayList<>();

        ArticleGenerator articleGenerator = new ArticleGenerator();
        articleGenerator.extractHTML();

        for (int i = 0; i < 3; i ++) {
            articles1.add(new Article(articleGenerator.randomTitlePost(), articleGenerator.randomAuthor(), articleGenerator.getDate()));
        }

        for (int i = 0; i < 3; i ++) {
            articles2.add(new Article(articleGenerator.randomTitlePost(), articleGenerator.randomAuthor(), articleGenerator.getDate()));
        }

        Flux<Article> fx1 = Flux.fromIterable(articles1);
        Flux<Article> fx2 = Flux.fromIterable(articles2);

        return Flux.zip(fx1, fx2, (a1, a2) -> String.format("Flux1: %s, Flux2: %s", a1, a2));
    }

    @Override
    public Flux<String> findMergeZipWith() {
        List<Article> articles1 = new ArrayList<>();
        List<Article> articles2 = new ArrayList<>();

        ArticleGenerator articleGenerator = new ArticleGenerator();
        articleGenerator.extractHTML();

        for (int i = 0; i < 3; i ++) {
            articles1.add(new Article(articleGenerator.randomTitlePost(), articleGenerator.randomAuthor(), articleGenerator.getDate()));
        }

        for (int i = 0; i < 3; i ++) {
            articles2.add(new Article(articleGenerator.randomTitlePost(), articleGenerator.randomAuthor(), articleGenerator.getDate()));
        }

        Flux<Article> fx1 = Flux.fromIterable(articles1);
        Flux<Article> fx2 = Flux.fromIterable(articles2);

        return fx1.zipWith(fx2, (a1, a2) -> String.format("Flux1: %s, Flux2: %s", a1, a2));
    }

    @Override
    public Mono<Object> findDefault() {
        Article article = new Article("El Brayan ahora es rico", " Brayan", " 12-09-20");
        return Mono.empty()
                .defaultIfEmpty(article.getTitle().concat(article.getAuthor()).concat(article.getDate()));
    }

    @Override
    public Flux<Article> findConcat() {
        List<Article> articles1 = new ArrayList<>();
        List<Article> articles2 = new ArrayList<>();

        ArticleGenerator articleGenerator = new ArticleGenerator();
        articleGenerator.extractHTML();

        for (int i = 0; i < 3; i ++) {
            articles1.add(new Article(articleGenerator.randomTitlePost(), articleGenerator.randomAuthor(), articleGenerator.getDate()));
        }
        for (int i = 0; i < 3; i ++) {
            articles2.add(new Article(articleGenerator.randomTitlePost(), articleGenerator.randomAuthor(), articleGenerator.getDate()));
        }

        Flux<Article> fx1 = Flux.fromIterable(articles1);
        return fx1.concatWith(Flux.fromIterable(articles2)).switchIfEmpty(Flux.empty());
    }


    private List<Article> generateArticle(long interval) {
        ArticleGenerator articleGenerator = new ArticleGenerator();
        articleGenerator.extractHTML();

        Article obj = new Article(articleGenerator.randomTitlePost(), articleGenerator.randomAuthor(), articleGenerator.getDate());
        return Arrays.asList(obj);
    }

    private List<Article> generateArticleTwo(long interval) {
        ArticleGenerator articleGenerator = new ArticleGenerator();
        Integer page = 2;
        articleGenerator.extractHTMLTwo(page);

        Article obj = new Article(articleGenerator.randomTitlePost(), articleGenerator.randomAuthor(), articleGenerator.getDate());
        return Arrays.asList(obj);
    }

}
