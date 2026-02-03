package com.example.experiment2;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.experiment2.data.ShopItem;

import java.util.ArrayList;

public class ShopItemAdapter extends RecyclerView.Adapter<ShopItemAdapter.ViewHolder> {

    private final ArrayList<ShopItem> shopItemArrayList;

    public ShopItemAdapter(ArrayList<ShopItem> shopItems) {
        this.shopItemArrayList = shopItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.shop_item_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        ShopItem item = shopItemArrayList.get(position);
        viewHolder.getTextViewName().setText(item.getName());
        viewHolder.getTextViewPrice().setText(String.valueOf(item.getPrice()));
        viewHolder.getImageViewItem().setImageResource(item.getImageResouceid());
    }

    @Override
    public int getItemCount() {
        return shopItemArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        private final TextView textViewName;
        private final TextView textViewPrice;
        private final ImageView imageViewItem;

        public ViewHolder(View shopItemView) {
            super(shopItemView);
            textViewName = shopItemView.findViewById(R.id.textView_item_name);
            textViewPrice = shopItemView.findViewById(R.id.textView_item_price);
            imageViewItem = shopItemView.findViewById(R.id.imageView_item);
            shopItemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("具体操作");
            // Add items: groupId, itemId, order, title
            menu.add(0, 0, this.getAdapterPosition(), "添加");
            menu.add(0, 1, this.getAdapterPosition(), "删除");
            menu.add(0, 2, this.getAdapterPosition(), "修改");
        }

        public TextView getTextViewName() {
            return textViewName;
        }

        public TextView getTextViewPrice() {
            return textViewPrice;
        }

        public ImageView getImageViewItem() {
            return imageViewItem;
        }
    }
}
