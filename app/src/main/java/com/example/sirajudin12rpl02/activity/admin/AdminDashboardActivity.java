package com.example.sirajudin12rpl02.activity.admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sirajudin12rpl02.R;
import com.example.sirajudin12rpl02.helper.Config;

public class AdminDashboardActivity extends AppCompatActivity {
    private RelativeLayout div_Sepeda, div_Customer;
    private TextView tv_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        tv_logout = findViewById(R.id.logout);
        tv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
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
    private void logout() {
        new AlertDialog.Builder(AdminDashboardActivity.this)
                .setTitle("Logout")
                .setMessage("Anda yakin akan logout ?")
                .setNegativeButton("Tidak", null)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    private void doNothing() {

                    }

                    public void onClick(DialogInterface arg0, int arg1) {
                        Config.forceLogout(AdminDashboardActivity.this);
                    }
                }).create().show();
    }
}
