package com.ruan.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication implements CommandLineRunner {

	@Autowired
	private ApiServiceWeather apiServiceWeather;

	@Autowired
	private FileService fileService;

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		String weatherData = apiServiceWeather.getWeather();
		System.out.println("Dados da API: "+weatherData);

		/*
		//Savar dados no arquivo
		fileService.saveDataToFile(weatherData);
		 */
	}
}
// implements CommandLineRunner : roda a aplicação spring via cli também