package com.example.experiment2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main); //使用layout
        TextView textViewfirst=findViewById(R.id.text_view_hello_world);
        textViewfirst.setText("你好 Android");

        //交换文字
        Button button=findViewById(R.id.button_change);
        button.setText(R.string.button_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button clickedButton = (Button) view;
                TextView text_hello = findViewById(R.id.text_View_1);
                TextView text_jnu = findViewById(R.id.text_View_2);

                CharSequence text1 = text_hello.getText();
                CharSequence text2 = text_jnu.getText();
                text_jnu.setText(text1);
                text_hello.setText(text2);

                Toast.makeText(this,"交换成功",Toast.LENGTH_LONG.show());
            }
        });
//交换文字

        //相对布局
        /*RelativeLayout relativeLayout = new RelativeLayout(this);
        RelativeLayout.LayoutParams params =  new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);  //设置布局中的控件置顶显示
        params.addRule(RelativeLayout.CENTER_HORIZONTAL); //设置布局中的控件居中显示
        params.leftMargin = 100;  // X轴位置，单位为像素
        params.topMargin = 900;   // Y轴位置，单位为像素

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM); //设置imageview控件布局

        TextView textView = new TextView(this);                       //创建TextView控件
        ImageView imageView = new ImageView(this);                    //创建 ImageView 控件

        textView.setText(R.string.Hello_World);                     //设置TextView的文字内容

        textView.setTextColor(Color.GREEN);                                  //设置TextView的文字颜色
        textView.setTextSize(18);                                                //设置TextView的文字大小
        imageView.setImageResource(R.drawable.my_image);
        relativeLayout.addView(textView, params);                  //添加TextView对象和TextView的布局属性
        relativeLayout.addView(imageView,layoutParams);
        setContentView(relativeLayout);                                  //设置在Activity中显示RelativeLayout*/
        //相对布局
    }
}