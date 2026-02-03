package com.example.experiment2.data;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class DataBank {
    private static final String DATA_FILENAME = "shopitems.json"; // Changed extension to .json

    public ArrayList<ShopItem> LoadShopItems(Context context) {
        ArrayList<ShopItem> data = new ArrayList<>();
        try {
            FileInputStream fileIn = context.openFileInput(DATA_FILENAME);
            InputStreamReader inputStreamReader = new InputStreamReader(fileIn, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            
            String jsonString = stringBuilder.toString();
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<ShopItem>>() {}.getType();
            data = gson.fromJson(jsonString, listType);
            
            if (data == null) {
                data = new ArrayList<>();
            }

            bufferedReader.close();
            inputStreamReader.close();
            fileIn.close();

            Log.d("DataBank", "Data loaded successfully. Item count: " + data.size());
        } catch (IOException e) {
            Log.e("DataBank", "Error loading data: " + e.getMessage());
            // It's normal to fail if file doesn't exist yet
        } catch (Exception e) {
             Log.e("DataBank", "Error parsing data: " + e.getMessage());
        }
        return data;
    }

    public void SaveShopItems(Context context, ArrayList<ShopItem> shopItems) {
        try {
            Gson gson = new Gson();
            String jsonString = gson.toJson(shopItems);
            
            FileOutputStream fileOut = context.openFileOutput(DATA_FILENAME, Context.MODE_PRIVATE);
            fileOut.write(jsonString.getBytes(StandardCharsets.UTF_8));
            fileOut.close();
            
            Log.d("DataBank", "Data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("DataBank", "Error saving data: " + e.getMessage());
        }
    }
}
