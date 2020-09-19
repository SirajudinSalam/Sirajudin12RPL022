package com.example.sirajudin12rpl02.activity.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.sirajudin12rpl02.R;

public class DashboardActivity extends AppCompatActivity {
    private RelativeLayout div_sepeda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        div_sepeda = findViewById(R.id.list_sepeda);
//        div_sepeda.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(DashboardActivity.this,SepedaActivity.class);
//                startActivity(i);
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
