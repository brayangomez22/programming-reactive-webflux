package com.example.demo.utils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ArticleGenerator {

    public static final String url = "https://jarroba.com/page/%s/";
    public static final int maxPages = 20;
    private static String urlPage;



    private static final List<String> URL =
            Arrays.asList(
                    urlPage = String.format(url, 1),
                    urlPage = String.format(url, 2),
                    urlPage = String.format(url, 3),
                    urlPage = String.format(url, 4),
                    urlPage = String.format(url, 5)
            );

    private void


    Document document = getHtmlDocument(urlPage);
    Elements entradas = document.select("div.col-md-4.col-xs-12").not("div.col-md-offset-2.col-md-4.col-xs-12");




    public static int getStatusConnectionCode(String url) {
        Connection.Response response = null;
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
