package com.example.demo;

import java.io.IOException;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scraping_allPost {
	
    public static final String url = "https://jarroba.com/page/%s/";
    public static final int maxPages = 20;
	
    public static void main (String args[]) {
		
        for (int i=1; i<maxPages; i++){
			
            String urlPage = String.format(url, i);
            System.out.println("Comprobando entradas de: "+urlPage);
			
            if (getStatusConnectionCode(urlPage) == 200) {
				
                Document document = getHtmlDocument(urlPage);
				
                Elements entradas = document.select("div.col-md-4.col-xs-12").not("div.col-md-offset-2.col-md-4.col-xs-12");
				
                for (Element elem : entradas) {
                    String titulo = elem.getElementsByClass("tituloPost").text();
                    String autor = elem.getElementsByClass("autor").toString();
                    String fecha = elem.getElementsByClass("fecha").text();
					
                    System.out.println(titulo+"\n"+autor+"\n"+fecha+"\n");
                }
		
            }else{
                System.out.println("El Status Code no es OK es: "+getStatusConnectionCode(urlPage));
                break;
            }
        }
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