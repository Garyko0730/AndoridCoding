package com.example.experiment2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ShopitemDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopitem_details);

        Button buttonOk = findViewById(R.id.button_item_details);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("key","Some result date ");
                setResult(Activity.RESULT_OK,intent);
                ShopitemDetailsActivity.this.finish();//关闭Activity
            }
        });
    }
}