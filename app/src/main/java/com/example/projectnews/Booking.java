package com.example.projectnews;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Booking extends AppCompatActivity {
    Button a1,a2,a3,a4,a5;
    TextView amountsel,ticketse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        a1 = (Button) findViewById(R.id.a1);
        a2 = (Button) findViewById(R.id.a2);
        a3 = (Button) findViewById(R.id.a3);
        a4 = (Button) findViewById(R.id.a4);
        a5 = (Button) findViewById(R.id.a5);
        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticketse.setText("1");
                amountsel.setText("140");
            }
        });
        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticketse.setText("2");
                amountsel.setText("280");
            }
        });
        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticketse.setText("3");
                amountsel.setText("420");
            }
        });
        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticketse.setText("4");
                amountsel.setText("560");
            }
        });
        a5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticketse.setText("5");
                amountsel.setText("700");
            }
        });
    }
}
