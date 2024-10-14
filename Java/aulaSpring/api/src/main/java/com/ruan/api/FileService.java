package com.ruan.api;

import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class FileService {

    public void saveDataToFile(String data){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt"));
            writer.write(data);
            writer.close();
            System.out.println("Data saved successfully!");
        }catch (IOException error){
            System.out.println("Error to save datas: "+error.toString());
        }
    }
}
