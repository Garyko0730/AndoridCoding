package com.example.experiment2;

import static org.junit.Assert.*;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.example.experiment2.data.DataBank;
import com.example.experiment2.data.ShopItem;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class DataBankTest {
    DataBank dataSaverBackup;
    ArrayList<ShopItem> shopItemsBackup;
    @Before
    public void setUp() throws Exception {
        dataSaverBackup=new DataBank();
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        shopItemsBackup=dataSaverBackup.LoadShopItems(targetContext);

    }

    @After
    public void tearDown() throws Exception {
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        dataSaverBackup.SaveShopItems(targetContext,shopItemsBackup);
    }

    @Test
    public void loadShopItems() {
    }

    @Test
    public void saveAndLoadShopItems() {
        DataBank dataSaver=new DataBank();
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        ArrayList<ShopItem> shopItems=new ArrayList<>();
        ShopItem shopItem=new ShopItem("测试",56.7, R.drawable.book_1);
        shopItems.add(shopItem);
        shopItem=new ShopItem("正常",12.3, R.drawable.book_2);
        shopItems.add(shopItem);
        dataSaver.SaveShopItems(targetContext,shopItems);

        DataBank dataLoader=new DataBank();
        ArrayList<ShopItem> shopItemsRead=dataLoader.LoadShopItems(targetContext);
        assertNotSame(shopItems,shopItemsRead);
        assertEquals(shopItems.size(),shopItemsRead.size());
        for(int index=0;index<shopItems.size();++index)
        {
            assertNotSame(shopItems.get(index),shopItemsRead.get(index));
            assertEquals(shopItems.get(index).getName(),shopItemsRead.get(index).getName());
            assertEquals(shopItems.get(index).getPrice(),shopItemsRead.get(index).getPrice(),1e-2);
            assertEquals(shopItems.get(index).getImageResouceid(),shopItemsRead.get(index).getImageResouceid());
        }
    }
}