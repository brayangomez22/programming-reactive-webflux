package com.example.demo;

import com.example.demo.controller.ArticleController;
import com.example.demo.repository.ReactiveArticleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = ArticleController.class)
public class ArticleControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @SpyBean
    private ReactiveArticleRepository reactiveArticleRepository;

    @Test
    public void getAllArticles() {
        Assertions.assertThrows(IllegalStateException.class, ()->{
            webTestClient.get()
                    .uri("/article/all")
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody()
                    .consumeWith(course->{
                        byte[] body = course.getResponseBody();
                        Assertions.assertNotEquals(null, body);
                    });
        });
    }

    @Test
    public void getArticlesPerPage() {
        Assertions.assertThrows(IllegalStateException.class, ()->{
            webTestClient.get()
                    .uri("/article/perPage")
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody()
                    .consumeWith(article->{
                        byte[] body = article.getResponseBody();
                        Assertions.assertNotEquals(null, body);
                    });
        });
    }

    @Test
    public void getOneArticle() {
        Assertions.assertThrows(IllegalStateException.class, ()->{
            webTestClient.get()
                    .uri("/article/one")
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody()
                    .consumeWith(article->{
                        byte[] body = article.getResponseBody();
                        Assertions.assertNotEquals(null, body);
                    });
        });
    }

    @Test
    public void getModifiedArticle() {
        Assertions.assertThrows(IllegalStateException.class, ()->{
            webTestClient.get()
                    .uri("/article/modified")
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody()
                    .consumeWith(article->{
                        byte[] body = article.getResponseBody();
                        Assertions.assertNotEquals(null, body);
                    });
        });
    }

    @Test
    public void getModifiedArticleWithFlatMap() {
        Assertions.assertThrows(IllegalStateException.class, ()->{
            webTestClient.get()
                    .uri("/article/modifiedFlatMap")
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody()
                    .consumeWith(article->{
                        byte[] body = article.getResponseBody();
                        Assertions.assertNotEquals(null, body);
                    });
        });
    }

    @Test
    public void getMergeArticlesZip() {
        Assertions.assertThrows(IllegalStateException.class, ()->{
            webTestClient.get()
                    .uri("/article/mergeZip")
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody()
                    .consumeWith(article->{
                        byte[] body = article.getResponseBody();
                        Assertions.assertNotEquals(null, body);
                    });
        });
    }

    @Test
    public void getMergeArticlesZipWith() {
        Assertions.assertThrows(IllegalStateException.class, ()->{
            webTestClient.get()
                    .uri("/article/mergeZipWith")
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody()
                    .consumeWith(article->{
                        byte[] body = article.getResponseBody();
                        Assertions.assertNotEquals(null, body);
                    });
        });
    }

    @Test
    public void getDefaultArticle() {
        webTestClient.get()
            .uri("/article/default")
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .consumeWith(article->{
                byte[] body = article.getResponseBody();
                Assertions.assertNotEquals(null, body);
            });
    }

    @Test
    public void getConcatArticles() {
        Assertions.assertThrows(IllegalStateException.class, ()->{
            webTestClient.get()
                    .uri("/article/concat")
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody()
                    .consumeWith(article->{
                        byte[] body = article.getResponseBody();
                        Assertions.assertNotEquals(null, body);
                    });
        });
    }
}
