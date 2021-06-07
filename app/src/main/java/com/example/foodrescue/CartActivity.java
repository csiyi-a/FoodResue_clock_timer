package com.example.foodrescue;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.greendao.db.bean.Food;
import com.greendao.db.helper.FoodHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.multitype.MultiTypeAdapter;

public class CartActivity extends AppCompatActivity {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public static List<Food> foodList = new ArrayList<>();

    List<Cart> mItems = new ArrayList<>();
    MultiTypeAdapter mAdapter = new MultiTypeAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);
        mAdapter.register(Cart.class, new CartViewBinder());

        mAdapter.setItems(mItems);


    }

    @Override
    protected void onResume() {
        super.onResume();

        initList();
    }


    private void initList() {

        Map<String, Integer> map = new HashMap<>();

        for (Food food : foodList) {

            if (map.containsKey(food.getTitle())) {

                Integer num = map.get(food.getTitle());
                map.remove(food.getTitle());
                num++;
                map.put(food.getTitle(), num);

            } else {
                map.put(food.getTitle(), 1);
            }

        }


        mItems.clear();

        for (String key : map.keySet()) {

            Cart cart = new Cart();
            cart.setName(key);
            cart.setCount(map.get(key));
            cart.setPrice(cart.getCount() * 100);

            mItems.add(cart);
        }

        mAdapter.notifyDataSetChanged();

    }

    @OnClick(R.id.btn_pay)
    public void onClick() {

        showSheetDialog();
    }


    BottomSheetDialog bottomSheetDialog;

    private void showSheetDialog() {

        View view = View.inflate(CartActivity.this, R.layout.layout_pay, null);

        TextView quantity = view.findViewById(R.id.quantity);
        TextView price = view.findViewById(R.id.price);
        Button btn_pay = view.findViewById(R.id.btn_pay);

        int num=0;

        for (Cart cart : mItems) {

            num +=cart.getCount();

        }


        quantity.setText("Quantity: " + num);
        price.setText("Price: " + (num*100) + "$");

        btn_pay.setOnClickListener(v -> {
            Toast.makeText(CartActivity.this, "Pay success!", Toast.LENGTH_SHORT).show();
            bottomSheetDialog.dismiss();
        });

        bottomSheetDialog = new BottomSheetDialog(CartActivity.this);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();

    }

}