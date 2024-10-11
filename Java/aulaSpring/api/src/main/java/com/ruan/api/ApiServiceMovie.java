package com.ruan.api;

import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


//Conexões com Java puro
public class ApiServiceMovie {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in).useDelimiter(" ");
        System.out.println("Digite o nome do seu filme separado com + e sem letras maúiculas");
        String filme = URLEncoder.encode(scanner.nextLine(), StandardCharsets.UTF_8);
        String endereco = "http://www.omdbapi.com/?t="+filme+"&apikey=64153a75";

        try{
            // Configurando o cliente HTTP
            HttpClient client = HttpClient.newBuilder()
                    .proxy(ProxySelector.of(
                            new InetSocketAddress("proxy.br.bosch.com", 8080)))
                    .build();

            // Criando a requisição HTTP / a apikey é gerada pelo link: https://www.omdbapi.com/apikey.aspx
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endereco))
                    .build();

            //Eviando a requisição e obtendo a resposta
            HttpResponse<String> respose = client.send(request, HttpResponse.BodyHandlers.ofString());

            //Imprimindo resposta da requisição
            System.out.println("Resposta: "+respose);
            System.out.println("Contúdo: \n"+respose.body());

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
