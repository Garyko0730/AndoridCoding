package com.example.experiment2;

import static android.app.ProgressDialog.show;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.experiment2.data.ShopItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recycleview);

        //实验6RecyclerView

        RecyclerView recyclerView = findViewById(R.id.recycler_view);// 创建布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //定义一个Arraylist
        ArrayList<ShopItem> shopItems= new ArrayList<>();
        for (int iloop = 0; iloop < 20; iloop++) {
            shopItems.add(new ShopItem("信息安全数学基础（第2版）",59,R.drawable.book_1));
            shopItems.add(new ShopItem("软件项目管理案例教程（第4版）",60, R.drawable.book_2));
            shopItems.add(new ShopItem("创新工程实践",65, R.drawable.book_no_name));
        }
        //数组是固定的，不方便插入数据
        String []itemNames = new String[]{"商品1","商品2","商品3"};//数据

        ShopItemAdapter shopItemAdapter = new ShopItemAdapter(shopItems);//接收一个数组
        recyclerView.setAdapter(shopItemAdapter);
    }

    public class ShopItemAdapter extends RecyclerView.Adapter<ShopItemAdapter.ViewHolder> {

        private ArrayList<ShopItem> shopItemArrayList;

        /**
         * Provide a reference to the type of views that you are using
         * (custom ViewHolder).
         */
        public class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView textViewName;
            private final TextView textViewPrice;
            private final ImageView imageViewItem;
            public ViewHolder(View view) {
                super(view);
                // Define click listener for the ViewHolder's View

                //id并不是唯一，记得加view.,要不然找出来的是一个数组
                textViewName = view.findViewById(R.id.textView_item_name);
                textViewPrice = view.findViewById(R.id.textView_item_price);
                imageViewItem =view.findViewById(R.id.imageView_item);
            }

            public TextView getTextViewName() {
                return textViewName;
            }
            public TextView getTextViewPrice() {
                return textViewPrice;
            }
            public ImageView getIamgeViewItem() {
                return imageViewItem;
            }
        }
        public ShopItemAdapter(ArrayList<ShopItem> shopItems) {
            shopItemArrayList = shopItems;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            // Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(viewGroup.getContext())//布局渲染器
                    .inflate(R.layout.shop_item_row, viewGroup, false);

            return new ViewHolder(view);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {

            // Get element from your dataset at this position and replace the
            // contents of the view with that element
            viewHolder.getTextViewName().setText(shopItemArrayList.get(position).getName());//绑定name
            viewHolder.getTextViewPrice().setText(shopItemArrayList.get(position).getPrice()+"");//绑定价格
            viewHolder.getIamgeViewItem().setImageResource(shopItemArrayList.get(position).getImageResouceid());
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return shopItemArrayList.size();
        }
    }

}
        //实验6RecyclerView


//交换文字

//        Button button=findViewById(R.id.button_change);
//        button.setText(R.string.button_1);
//        button.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                Button clickedButton = (Button) view;
//                TextView text_hello = findViewById(R.id.text_View_1);
//                TextView text_jnu = findViewById(R.id.text_View_2);
//
//                CharSequence text1 = text_hello.getText();
//                CharSequence text2 = text_jnu.getText();
//                text_jnu.setText(text1);
//                text_hello.setText(text2);
//
//                Toast.makeText(MainActivity.this,"交换成功", Toast.LENGTH_SHORT).show();
//                showDialog(MainActivity.this, "", "交换成功啦！");
//                };
//            });
//        };
//        private void showDialog(MainActivity context, String title, String message) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setTitle(title)
//                .setMessage(message)
//                .setPositiveButton("我知道了", new DialogInterface.OnClickListener()
//                {
//                    public void onClick(DialogInterface dialog, int which)
//                    {
//                        // 在确定按钮点击时执行的逻辑
//                    }
//                })
//
//                //不需要取消按钮，这里先注释掉了
////                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
////                    public void onClick(DialogInterface dialog, int which) {
////                        // 在取消按钮点击时执行的逻辑
////                    }
////                })
//                .show();

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
//    }
//}
