package com.example.sirajudin12rpl02.activity.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.sirajudin12rpl02.R;

public class AdminDashboardActivity extends AppCompatActivity {
    private RelativeLayout div_Sepeda, div_Customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        div_Sepeda = findViewById(R.id.daftarsepeda);
//        div_Sepeda.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(AdminDashboardActivity.this,AdminSepedaActivity.class);
//                startActivity(i);
//            }
//        });
        div_Customer = findViewById(R.id.daftarcustomer);
        div_Customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminDashboardActivity.this,AdminUserActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
