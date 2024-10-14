package com.challenge.springChallenge.Services;


import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;


@Service
public class FakeDB {

    public static void saveDataToFakeDB(String json){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("fakeDB.json"));
            System.out.println(reader.readLine());



            BufferedWriter writer = new BufferedWriter(new FileWriter("fakeDb.json"));
            writer.write(json);
            writer.close();
            System.out.println("Data saved successfully!");
        }catch (Exception error){
            System.out.println("Error to save in fake database: "+error.toString());
        }
    }
}
