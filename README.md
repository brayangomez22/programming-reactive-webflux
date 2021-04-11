# CHALLENGE - APLICANDO PROGRAMACIÓN REACTIVA CON WEBFLUX #

## Summary ##

En este reto deberiamos usar Spring Boot Reactive con Webflux, aplicando los conceptos de la programación funciona y reactiva, llevando a cabo cada principio de forma correcta, se pretende demostrar el uso de la herramientas aprendidas de la programación reactiva con el uso de la librería de reactor core y spring boot.

## Use Case/Problem ##

En esta aplicación se deberá utilizar la siguientes herramientas de forma correcta :

 *  Spring Boot
 *  WebFlux
 *  Gradle

Ejecute esto usando el envoltorio gradle incluido en el repositorio.

~~~~~~~~~~
./gradlew bootRun
~~~~~~~~~~

  


Esto iniciará la aplicación en el puerto 8080.

  


**PROBLEMA:**

Se tiene el test **src/test/java/com/example/ReactorTests.java** con algunos operadores funcionales lo que se busca es crear algunos servicios funcionales donde se puedan aplicar estos operadores dentro de un servicio web de Spring Boot, se debe usar la herramienta https://jsoup.org/ para extraer de una pagina web contenido en HTML y lo que se espera es transforma ese contenido HTML en servicios web reactivos.

  


Un ejemplo de ello es:

  


~~~~~~~~~~
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


Document getHTMLByURL(String url){
    try {
        return Jsoup.connect(url).get();
    } catch (IOException e){
        return null;
    }
}

var doc = getHTMLByURL(
    "https://www.sofka.com.co/es"
);
var html = doc.select(
    "#why-us > div.page-section-content.vc_row-fluid.mk-grid > div.mk-padding-wrapper > div > div:nth-child(5)"
).first();

var elements = html.select(".mk-caption-title");
System.out.println("¿POR QUÉ ELEGIRNOS?");
for(Element title : elements){
    System.out.println("- "+title.text());
}
~~~~~~~~~~

  


Para cumplir el problema se debe considerar las siguientes directrices:

  


 *  Aplicar los siguientes operadores en un ejemplo:
 *  zip
 *  map
 *  concatWith
 *  zipWith
 *  toIterable
 *  fromIterable
 *  just
 *  empty
 *  flatMap
 *  switchIfEmpty
 *  Crear minimo 7 servicios web reactivo que expongan los contenidos de HTML en un formato JSON (explorar el documento y extraer fragmentos de el).
 *  Tener una cobertura al 100%

  


Recuerde que en el archivo de los tests va a encontrar ejemplo de algunos operadores basicos, esto les puede ayudar para aplicar el modelo diseñado por ustedes.

  


***IMPORTANTE: para este reto se recomienda hacer una exploración previa de la herramienta de jsoup***

## Evaluation criteria ##

| Criteria                                                                                  | Percentage |
| ----------------------------------------------------------------------------------------- | ---------- |
| Aplica los operadores descritos en el caso/problema                                       | 30.0 %     |
| Tiene cobertura del 100%                                                                  | 30.0 %     |
| Crea los servicios web que permitan extraer contenido de una pagina web de forma reactiva | 40.0 %     |