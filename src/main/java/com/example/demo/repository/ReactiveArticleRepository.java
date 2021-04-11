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
    public Flux<Article> findOne() {
        return Flux.interval(Duration.ofSeconds(1))
                .take(1)
                .map(this::generateArticle)
                .flatMapIterable(x -> x);
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
    public Flux<String> findZip() {
        List<Article> articles1 = new ArrayList<>();
        articles1.add(new Article("El Brayan", "Brayan", "12-02-23"));
        articles1.add(new Article("El Brayan se gana un palo", "Brayan", "12-02-23"));

        List<Article> articles2 = new ArrayList<>();
        articles2.add(new Article("El Jacobo", "Brayan", "12-02-23"));
        articles2.add(new Article("El Jacobo se gana un palo", "Brayan", "12-02-23"));

        Flux<Article> fx1 = Flux.fromIterable(articles1);
        Flux<Article> fx2 = Flux.fromIterable(articles2);

        return Flux.zip(fx1, fx2, (a1, a2) -> String.format("Flux1: %s, Flux2: %s", a1, a2));
    }

    @Override
    public Flux<String> findZipWith() {
        List<Article> articles1 = new ArrayList<>();
        articles1.add(new Article("El Brayan se volvio millonario", "Brayan Gómez", "11-02-23"));
        articles1.add(new Article("El Brayan se gana un palo", "Brayan", "11-02-123"));

        List<Article> articles2 = new ArrayList<>();
        articles2.add(new Article("El Jacobo tiene covid", "Brayan", "12-02-23"));
        articles2.add(new Article("El Jacobo se perdio", "Brayan", "12-02-23"));

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
