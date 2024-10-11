package com.ruan.api;

import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.net.Proxy;
import java.net.InetSocketAddress;

@Service
public class ApiServiceWeather {

    public String getWeather(){
        try{
            String url = "https://api-open-mateo.com/v1/forecast?" +
                    "latitude=35&" +
                    "longitude=139&" +
                    "hourly=temperature";

            //Configuração do proxy
            SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
            Proxy proxy = new Proxy(Proxy.Type.HTTP,
                    new InetSocketAddress("proxy.br.bosch.com", 8080));
            factory.setProxy(proxy);

            //Criando ResTemplate com proxy
            RestTemplate restTemplate = new RestTemplate(factory);

            //Fazendo a requisição
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getBody();
        }catch (Exception e){
            System.out.println("Error: "+e.toString());
            return "error";
        }
    }
}
