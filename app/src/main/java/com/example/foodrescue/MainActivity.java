package com.example.foodrescue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.greendao.db.bean.User;
import com.greendao.db.helper.UserHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User user = new User();
        user.setName("king");
        user.setPhone("123435457");
        user.setAddress("sdfgdfg");
        user.setPassword(12345);

//        UserHelper.save(user);

        System.out.println(UserHelper.find());

    }
}