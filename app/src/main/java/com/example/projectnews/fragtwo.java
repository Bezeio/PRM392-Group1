package com.example.projectnews;

import android.app.AlertDialog;
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
    Context context= fragtwo.this;
    String id, title;
    private TextView titleTV, time, amountsel,ticketse;
    private Button b1,b2,b3,b4,book, a1,a2,a3,a4,a5;
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

        amountsel = findViewById(R.id.amountsel);
        amountsel.setVisibility(View.GONE);
        ticketse = findViewById(R.id.ticketsel);
        ticketse.setVisibility(View.GONE);
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
        a1 = findViewById(R.id.a1);
        a2 = findViewById(R.id.a2);
        a3 = findViewById(R.id.a3);
        a4 = findViewById(R.id.a4);
        a5 = findViewById(R.id.a5);
        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticketse.setText("1");
                amountsel.setText("140");
                ticketse.setVisibility(View.VISIBLE);
                amountsel.setVisibility(View.VISIBLE);
            }
        });
        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticketse.setText("2");
                amountsel.setText("280");
                ticketse.setVisibility(View.VISIBLE);
                amountsel.setVisibility(View.VISIBLE);
            }
        });
        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticketse.setText("3");
                amountsel.setText("420");
                ticketse.setVisibility(View.VISIBLE);
                amountsel.setVisibility(View.VISIBLE);
            }
        });
        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticketse.setText("4");
                amountsel.setText("560");
                ticketse.setVisibility(View.VISIBLE);
                amountsel.setVisibility(View.VISIBLE);
            }
        });
        a5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticketse.setText("5");
                amountsel.setText("700");
                ticketse.setVisibility(View.VISIBLE);
                amountsel.setVisibility(View.VISIBLE);
            }
        });
        book = findViewById(R.id.booking);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v = getLayoutInflater().inflate(R.layout.popup_layout, null);
                AlertDialog.Builder bb = new AlertDialog.Builder(context);
                bb.setView(v);
                TextView titleTextView = v.findViewById(R.id.title);
                titleTextView.setText("Bạn đã đặt vé thành công");
                TextView messageTextView = v.findViewById(R.id.message);
                messageTextView.setText("Your Ticket is Booked :\n\nTiming: "+time.getText().toString()+"\nTotal Tickets :"+ ticketse.getText().toString()+"\nAmount Paid: "+amountsel.getText().toString());
                AlertDialog alertDialog = bb.create();
                alertDialog.show();
            }
        });
    }

}
