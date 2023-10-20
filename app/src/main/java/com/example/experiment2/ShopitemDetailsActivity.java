package com.example.experiment2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ShopitemDetailsActivity extends AppCompatActivity {

    private int position=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopitem_details);

        Intent intent = getIntent();
        if(null != intent){
            String name = intent.getStringExtra("name");

            if(null != name)//如果不为空说明有数据从主窗口传进来
            {
                Double price = intent.getDoubleExtra("price",0);
                position = intent.getIntExtra("position",-1);
                EditText editTextItemName = findViewById(R.id.edittext_item_name);
                editTextItemName.setText(name);
                EditText editTextItemPrice = findViewById(R.id.edittext_item_price);
                editTextItemPrice.setText(price.toString());
            }
        }

        Button buttonOk = findViewById(R.id.button_item_details);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                EditText editTextItemName = findViewById(R.id.edittext_item_name);
                EditText editTextItemPrice = findViewById(R.id.edittext_item_price);
                intent.putExtra("name",editTextItemName.getText().toString());
                intent.putExtra("price",editTextItemPrice.getText().toString());
                intent.putExtra("position",position);
                setResult(Activity.RESULT_OK,intent);
                ShopitemDetailsActivity.this.finish();//关闭Activity
            }
        });
    }
}