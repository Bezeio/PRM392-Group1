package com.example.projectnews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projectnews.dao.DBHelper;
import com.example.projectnews.dao.INewDao;
import com.example.projectnews.dao.NewDao;
import com.example.projectnews.model.Movie;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class fragtwo extends AppCompatActivity {
    Context context;
    String id, title;
    private TextView titleTV, time;
    private Button b1,b2,b3,b4,book;
    public static DBHelper dbHelper;
    INewDao newDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragtwo);
        dbHelper = new DBHelper(this);
        newDao = new NewDao(this);
        id = getIntent().getStringExtra("newId");
        title = getIntent().getStringExtra("newTitle");
        titleTV = findViewById(R.id.movieid);
        titleTV.setText(title);

        time = findViewById(R.id.timer);
        time.setVisibility(View.GONE);
        b1 = findViewById(R.id.one);
        b2 = findViewById(R.id.two);
        b3 = findViewById(R.id.three);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {time.setText("9:00 AM");time.setVisibility(View.VISIBLE);}
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {time.setText("11:00PM");time.setVisibility(View.VISIBLE);}
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {time.setText("13:00 PM");time.setVisibility(View.VISIBLE);}
        });
        book = findViewById(R.id.booking);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Booking.class);
                intent.putExtra("id", id);
                context.startActivity(intent);
            }
        });
    }

}
