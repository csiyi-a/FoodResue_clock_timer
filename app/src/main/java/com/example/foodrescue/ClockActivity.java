package com.example.foodrescue;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AnalogClock;

import com.greendao.db.bean.User;
import com.greendao.db.helper.UserHelper;

import butterknife.ButterKnife;


public class ClockActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);
        ButterKnife.bind(this);




    }

}
