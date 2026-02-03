package com.example.experiment2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.experiment2.data.DataBank;
import com.example.experiment2.data.ShopItem;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShoppingListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShoppingListFragment extends Fragment {

    private ArrayList<ShopItem> shopItems = new ArrayList<>();
    private ShopItemAdapter shopItemAdapter;
    private ActivityResultLauncher<Intent> addItemLauncher;
    private ActivityResultLauncher<Intent> updateItemLauncher;

    private static final int MENU_ITEM_ADD = 0;
    private static final int MENU_ITEM_DELETE = 1;
    private static final int MENU_ITEM_UPDATE = 2;

    public ShoppingListFragment() {
        // Required empty public constructor
    }

    public static ShoppingListFragment newInstance() {
        ShoppingListFragment fragment = new ShoppingListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Initialize Launchers here
        addItemLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        Intent data = result.getData();
                        String name = data.getStringExtra("name");
                        String priceText = data.getStringExtra("price");

                        double price = Double.parseDouble(priceText);
                        shopItems.add(new ShopItem(name, price, R.drawable.book_no_name));
                        shopItemAdapter.notifyItemInserted(shopItems.size() - 1);
                        new DataBank().SaveShopItems(requireActivity(), shopItems);
                    }
                }
        );

        updateItemLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        Intent data = result.getData();
                        int position = data.getIntExtra("position", 0);
                        String name = data.getStringExtra("name");
                        String priceText = data.getStringExtra("price");

                        double price = Double.parseDouble(priceText);
                        ShopItem shopItem = shopItems.get(position);
                        shopItem.setPrice(price);
                        shopItem.setName(name);

                        shopItemAdapter.notifyItemChanged(position);
                        new DataBank().SaveShopItems(requireActivity(), shopItems);
                    }
                }
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_shopping_list, container, false);

        RecyclerView mainRecyclerView = rootView.findViewById(R.id.recycler_view);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));

        shopItems = new DataBank().LoadShopItems(requireActivity());
        if (shopItems.isEmpty()) {
            shopItems.add(new ShopItem("信息安全数学基础（第2版）", 59, R.drawable.book_1));
        }

        shopItemAdapter = new ShopItemAdapter(shopItems);
        mainRecyclerView.setAdapter(shopItemAdapter);

        // Register for context menu is usually for Views, but here items handle it themselves via Adapter
        // registerForContextMenu(mainRecyclerView); 

        return rootView;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // Warning: This method in Fragment is tricky. 
        // Ideally Context Menu logic should be handled via a listener passed to Adapter.
        // But keeping logic similar to original for now.
        
        // Note: item.getOrder() was used as position.
        int position = item.getOrder(); 

        switch (item.getItemId()) {
            case MENU_ITEM_ADD:
                Intent intent = new Intent(requireActivity(), BookitemDetailsActivity.class);
                addItemLauncher.launch(intent);
                return true;
            case MENU_ITEM_DELETE:
                new AlertDialog.Builder(requireActivity())
                        .setTitle("确认删除")
                        .setMessage("确定要删除吗？")
                        .setPositiveButton("是", (dialog, which) -> {
                            shopItems.remove(position);
                            shopItemAdapter.notifyItemRemoved(position);
                            new DataBank().SaveShopItems(requireActivity(), shopItems);
                        })
                        .setNegativeButton("否", null)
                        .show();
                return true;
            case MENU_ITEM_UPDATE:
                Intent intentUpdate = new Intent(requireActivity(), BookitemDetailsActivity.class);
                ShopItem shopItem = shopItems.get(position);
                intentUpdate.putExtra("name", shopItem.getName());
                intentUpdate.putExtra("price", shopItem.getPrice());
                intentUpdate.putExtra("position", position);
                updateItemLauncher.launch(intentUpdate);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
