package com.ruan.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeatherController {

    @Autowired
    private ApiServiceWeather apiServiceWeather;

    @GetMapping("/weather")
    public String getWeather(Model model){
        //Consome a api para obter o clima
        String weatherData = apiServiceWeather.getWeather();
        model.addAttribute("weatherData", weatherData);
        return "weatherPage";
    }
}
