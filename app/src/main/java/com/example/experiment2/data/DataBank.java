package com.example.experiment2.data;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DataBank {
    final String DATA_FILENAME = "shopitems.date";//文件名不用传来传去

    public ArrayList<ShopItem> LoadShopItems(Context context) {
        ArrayList<ShopItem> data = new ArrayList<>();
        try {
            // 创建文件输入流
            FileInputStream fileIn = context.openFileInput(DATA_FILENAME);

            // 创建对象输入流
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            // 从文件中读取对象
            data = (ArrayList<ShopItem>) objectIn.readObject();
            objectIn.close();
            fileIn.close();

            Log.d("Serialization", "Data loaded successfully.item count" + data.size());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            // 处理异常
        }
        return data;
    }

    public void SaveShopItems(Context context, ArrayList<ShopItem> shopItems) {
        try {
            // 创建文件输出流
            FileOutputStream fileOut = context.openFileOutput(DATA_FILENAME, Context.MODE_PRIVATE);

            // 创建对象输出流
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            // 将对象写入文件
            objectOut.writeObject(shopItems);

            // 关闭流
            objectOut.close();
            fileOut.close();

            // 数据已成功保存到文件
        } catch (IOException e) {
            e.printStackTrace();
            // 处理异常
        }
        }
    }
