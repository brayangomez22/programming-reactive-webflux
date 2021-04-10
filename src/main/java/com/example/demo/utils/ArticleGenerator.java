package com.example.demo.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.example.demo.model.Article;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ArticleGenerator {

    private final Random RANDOM = new Random(System.currentTimeMillis());

    public final String url = "https://jarroba.com/page/%s/";
    public final int maxPages = 20;

    private final List<String> tituloPost = new ArrayList<>();
    private final List<String> autor = new ArrayList<>();
    private final List<String> fecha = new ArrayList<>();
	
    public void extractHTML() {

        for (int i=1; i<maxPages; i++){
            String urlPage = String.format(url, i);

            if (getStatusConnectionCode(urlPage) == 200) {

                Document document = getHtmlDocument(urlPage);
                Elements entradas = document.select("div.col-md-4.col-xs-12").not("div.col-md-offset-2.col-md-4.col-xs-12");

                for (Element elem : entradas) {
                    tituloPost.add(elem.getElementsByClass("tituloPost").text());
                    autor.add(elem.getElementsByClass("autor").text());
                    fecha.add(elem.getElementsByClass("fecha").text());
                }

            }else{
                System.out.println("El Status Code no es OK es: "+getStatusConnectionCode(urlPage));
                break;
            }
        }
    }

    public void extractHTMLTwo(Integer page) {
        String urlPage = String.format(url, page);

        if (getStatusConnectionCode(urlPage) == 200) {
            Document document = getHtmlDocument(urlPage);
            Elements entradas = document.select("div.col-md-4.col-xs-12").not("div.col-md-offset-2.col-md-4.col-xs-12");

            for (Element elem : entradas) {
                tituloPost.add(elem.getElementsByClass("tituloPost").text());
                autor.add(elem.getElementsByClass("autor").text());
                fecha.add(elem.getElementsByClass("fecha").text());
            }
        }else{
            System.out.println("El Status Code no es OK es: "+getStatusConnectionCode(urlPage));
        }
    }

    public ArticleGenerator() {
    }

    public String randomTitlePost() {
        return tituloPost.get(RANDOM.nextInt(tituloPost.size()));
    }

    public String randomAuthor() {
        return autor.get(RANDOM.nextInt(autor.size()));
    }

    public String getDate() {
        return fecha.get(RANDOM.nextInt(fecha.size()));
    }

    public static int getStatusConnectionCode(String url) {
        Response response = null;
        try {
            response = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).ignoreHttpErrors(true).execute();
        } catch (IOException ex) {
            System.out.println("Excepción al obtener el Status Code: " + ex.getMessage());
        }
        return response.statusCode();
    }

    public static Document getHtmlDocument(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get();
        } catch (IOException ex) {
            System.out.println("Excepción al obtener el HTML de la página" + ex.getMessage());
        }
        return doc;
    }
}