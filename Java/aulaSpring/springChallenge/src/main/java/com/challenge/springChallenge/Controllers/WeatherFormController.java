package com.challenge.springChallenge.Controllers;


import com.challenge.springChallenge.ExternalAPI.WeatherAPI;
import com.challenge.springChallenge.Models.*;
import com.challenge.springChallenge.Services.FakeDB;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Controller
public class WeatherFormController {

    @Autowired
    private WeatherAPI weatherApi;

    @GetMapping("/form")
    public String showForm(Model model){
        model.addAttribute("weatherObject", new WeatherForm());
        return "WeatherForm";
    }

    @PostMapping("/form/submit")
    public String submitWeatherForm(@ModelAttribute WeatherForm weatherForm, Model model){
        WeatherResponse weatherResponse = weatherApi.getWeather(weatherForm.getLatitude(), weatherForm.getLongitude());

        if (weatherResponse.isSuccess()) {

            //Extract some data
            JsonObject jsonObject = JsonParser.parseString(weatherResponse.getData()).getAsJsonObject();
            JsonObject hourly = jsonObject.getAsJsonObject("hourly");

            JsonArray times = hourly.getAsJsonArray("time");
            JsonArray temperatures = hourly.getAsJsonArray("temperature");

            // Block to save in fake database
            try {
                Gson gson = new Gson();
                String[] timesArray = gson.fromJson(times, String[].class);
                double[] temperaturesArray = gson.fromJson(temperatures, double[].class);

                //Date and Hour
                LocalDateTime dateNow = LocalDateTime.now();
                DateTimeFormatter formatDay = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                DateTimeFormatter formatHour = DateTimeFormatter.ofPattern("HH:mm:ss");

                String day = dateNow.format(formatDay);
                String hour = dateNow.format(formatHour);

                //Save in fake db
                SearchData datas = new SearchData(
                        weatherForm.getLongitude(),
                        weatherForm.getLatitude(),
                        day,
                        hour,
                        timesArray,
                        temperaturesArray
                );

                String json = gson.toJson(datas);

                FakeDB.saveDataToFakeDB(json);

            }catch (Exception error){
                System.out.println("Error to convert datas: "+error.toString());
            }

            //Send to user
            model.addAttribute("times", times);
            model.addAttribute("temperatures", temperatures);
            model.addAttribute("longitude", weatherForm.getLongitude());
            model.addAttribute("latitude", weatherForm.getLatitude());
            model.addAttribute("weatherObject", new WeatherForm());

        } else {
            model.addAttribute("error", weatherResponse.getMessage());
        }

        return "WeatherForm";
    }
}