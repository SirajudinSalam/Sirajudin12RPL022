package com.example.sirajudin12rpl02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    private TextView tv_register;
    private EditText et_username, et_password;
    private Button btn_login;
    private boolean isFormFilled = false;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_username = findViewById(R.id.tv_username);
        et_password = findViewById(R.id.tv_password);
        tv_register = findViewById(R.id.tv_Register);
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFormFilled = true;
                final String username = et_username.getText().toString();
                final String password = et_password.getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Harap lengkapi isian yang tersedia", Toast.LENGTH_SHORT).show();
                    isFormFilled = false;
                }
                if (isFormFilled) {
                    HashMap<String, String> body = new HashMap<>();
                    body.put("act", "login");
                    body.put("username", username);
                    body.put("password", password);
                    AndroidNetworking.post(Config.BASE_URL_API + "auth.php")
                            .addBodyParameter(body)
                            .setOkHttpClient(((RS) getApplication()).getOkHttpClient())
                            .setPriority(Priority.MEDIUM)
                            .build()
                            .getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Log.d("YZD", "respon : " + response);

                                    String status = response.optString(Config.RESPONSE_STATUS_FIELD);
                                    String message = response.optString(Config.RESPONSE_MESSAGE_FIELD);
                                    if (status.equalsIgnoreCase(Config.RESPONSE_STATUS_VALUE_SUCCESS)) {
                                        JSONObject payload = response.optJSONObject(Config.RESPONSE_PAYLOAD_FIELD);
                                        String U_ID = payload.optString("U_ID");
                                        String U_NAME = payload.optString("U_NAME");
                                        String U_PHONE = payload.optString("U_PHONE");
                                        String U_GROUP_ROLE = payload.optString("GROUP_ROLE");
                                        String U_EMAIL = payload.optString("U_EMAIL");

//                                        preferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
//                                        preferences.edit()
//                                                .putString(Config.LOGIN_ID_SHARED_PREF, U_ID)
//                                                .putString(Config.LOGIN_NAME_SHARED_PREF, U_NAME)
//                                                .putString(Config.LOGIN_PHONE_SHARED_PREF, U_PHONE)
//                                                .putString(Config.LOGIN_GROUP_ID_SHARED_PREF, U_GROUP_ROLE)
//                                                .putString(Config.LOGIN_EMAIL_SHARED_PREF, U_EMAIL)
//                                                .apply();


                                            if (U_GROUP_ROLE.equalsIgnoreCase("GR_KONSUMEN")){
                                                Intent i = new Intent(LoginActivity.this, CustomerActivity.class);
                                                startActivity(i);
                                                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
//                                                finish();
//                                                finishAffinity();
                                            }else {
                                                Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                                                startActivity(intent);
                                                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
//                                                finish();
//                                                finishAffinity();
                                            }


                                    }
                                    else {
                                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                                    }


                                }

                                @Override
                                public void onError(ANError anError) {

                                    Toast.makeText(LoginActivity.this, Config.TOAST_AN_EROR, Toast.LENGTH_SHORT).show();
                                    Log.d("HBB", "onError: " + anError.getErrorBody());
                                    Log.d("HBB", "onError: " + anError.getLocalizedMessage());
                                    Log.d("HBB", "onError: " + anError.getErrorDetail());
                                    Log.d("HBB", "onError: " + anError.getResponse());
                                    Log.d("HBB", "onError: " + anError.getErrorCode());
                                }
                            });
                }
            }
        });

    }

}
