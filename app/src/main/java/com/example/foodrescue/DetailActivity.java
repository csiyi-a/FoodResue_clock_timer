package com.example.foodrescue;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.greendao.db.bean.Food;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.addBtn)
    ImageView addBtn;
    @BindView(R.id.edit_title)
    EditText editTitle;
    @BindView(R.id.edit_description)
    EditText editDescription;
    @BindView(R.id.edit_date)
    EditText editDate;
    @BindView(R.id.edit_quantity)
    EditText editQuantity;
    @BindView(R.id.edit_location)
    EditText editLocation;


    private Food food;

    public static void enter(Context context, Food food) {

        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("food", food);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        food = (Food) getIntent().getSerializableExtra("food");


        GlideEngine.createGlideEngine().loadImage(DetailActivity.this, food.getImgUrl(), addBtn);
        editTitle.setText(food.getTitle());
        editDescription.setText(food.getDesc());
        editDate.setText(food.getCreateDate());
        editQuantity.setText(food.getQuantity());
        editLocation.setText(food.getLocation());

    }


    @OnClick({R.id.btn_buy, R.id.btn_cart})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cart:

                CartActivity.foodList.add(food);
                Toast.makeText(DetailActivity.this,"Add success!",Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_buy:
                showSheetDialog();
                break;
        }
    }

    BottomSheetDialog bottomSheetDialog;
    private void showSheetDialog() {

        View view = View.inflate(DetailActivity.this, R.layout.layout_pay, null);

        TextView quantity = view.findViewById(R.id.quantity);
        TextView price = view.findViewById(R.id.price);
        Button btn_pay = view.findViewById(R.id.btn_pay);

        quantity.setText("Quantity: " + 1);
        price.setText("Price: " + 100+"$");

        btn_pay.setOnClickListener(v -> {
            Toast.makeText(DetailActivity.this,"Pay success!",Toast.LENGTH_SHORT).show();
            bottomSheetDialog.dismiss();
        });

        bottomSheetDialog = new BottomSheetDialog(DetailActivity.this);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();

    }


}