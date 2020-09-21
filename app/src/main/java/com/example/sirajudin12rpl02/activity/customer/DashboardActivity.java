package com.example.sirajudin12rpl02.activity.customer;

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

public class DashboardActivity extends AppCompatActivity {
    private RelativeLayout div_sepeda;
    private TextView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
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
    private void logout() {
        new AlertDialog.Builder(DashboardActivity.this)
                .setTitle("Logout")
                .setMessage("Anda yakin akan logout ?")
                .setNegativeButton("Tidak", null)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    private void doNothing() {

                    }

                    public void onClick(DialogInterface arg0, int arg1) {
                        Config.forceLogout(DashboardActivity.this);
                    }
                }).create().show();
    }
}
