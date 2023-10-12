package com.example.experiment2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ShopitemDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopitem_details);
        EditText editTextItemName = findViewById(R.id.edittext_item_name);
        EditText editTextItemPrice = findViewById(R.id.edittext_item_price);
        Button buttonOk = findViewById(R.id.button_item_details);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                EditText editTextItemName = findViewById(R.id.edittext_item_name);
                EditText editTextItemPrice = findViewById(R.id.edittext_item_price);
                intent.putExtra("name",editTextItemName.getText().toString());
                intent.putExtra("price",editTextItemPrice.getText().toString());
                setResult(Activity.RESULT_OK,intent);
                ShopitemDetailsActivity.this.finish();//关闭Activity
            }
        });
    }
}