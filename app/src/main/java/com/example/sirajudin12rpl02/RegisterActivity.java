package com.example.sirajudin12rpl02;

import androidx.appcompat.app.AppCompatActivity;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private TextView tv_Login;
    private EditText et_username, et_password, et_ktp, et_hp, et_email, et_alamat;
    private Button btn_register;
    private boolean mIsFormFilled = false;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_username = findViewById(R.id.tv_username);
        et_password = findViewById(R.id.tv_password);
        et_ktp = findViewById(R.id.tv_noKtp);
        et_hp = findViewById(R.id.tv_phone);
        et_email = findViewById(R.id.tv_email);
        et_alamat = findViewById(R.id.tv_alamat);
        tv_Login = findViewById(R.id.tv_login);
        tv_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
        btn_register = findViewById(R.id.btn_Register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsFormFilled = true;
                final String name = et_username.getText().toString();
                final String phone = et_hp.getText().toString();
                final String address = et_alamat.getText().toString();
                final String ktp = et_ktp.getText().toString();
                final String password = et_password.getText().toString();
                final String email = et_email.getText().toString().trim();

                if (name.isEmpty() || phone.isEmpty() || address.isEmpty() || ktp.isEmpty() || password.isEmpty() || email.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Harap lengkapi isian yang tersedia", Toast.LENGTH_SHORT).show();
                    mIsFormFilled = false;
                }

//                if(tilReferal.getVisibility() == View.VISIBLE) {
//                    if(referal.isEmpty()) {
//                        Toast.makeText(RegisterActivity.this, "Harap lengkapi isian yang tersedia", Toast.LENGTH_SHORT).show();
//                        mIsFormFilled = false;
//                    }
//                }

//                if(password.length() < 8) {
//                    Toast.makeText(RegisterActivity.this, "Panjang password minimal 8 karakter", Toast.LENGTH_SHORT).show();
//                    mIsFormFilled = false;
//                }
//                if(!password.equalsIgnoreCase(passwordConfirm)) {
//                    Toast.makeText(RegisterActivity.this, "Harap samakan isian password dan konfirmasi password", Toast.LENGTH_SHORT).show();
//                    mIsFormFilled = false;
//                }

//                if(tilReferal.getVisibility() == View.VISIBLE && referal.isEmpty()) {
//                    Toast.makeText(RegisterActivity.this, "Harap isikan nama marketing yang mereferensikan", Toast.LENGTH_SHORT).show();
//                    mIsFormFilled = false;
//                }

                if (mIsFormFilled) {
                    HashMap<String, String> body = new HashMap<>();
                    body.put("act", "register_konsumen");
//                    try {
//                        final MCrypt mcrypt = new MCrypt();
//                        String encrypted = MCrypt.bytesToHex(mcrypt.encrypt(password));
//                        body.put("passwordEnc", encrypted);
//                    }
//                    catch (Exception e) {
//                        Log.d("RBA", "Exception : " + e.getMessage());
//                        body.put("password", password);
//                    }



                    body.put("id", phone);
                    body.put("name", name);
                    body.put("address", address);
                    body.put("noktp", ktp);
                    body.put("password", password);
                    body.put("email", email);

                    AndroidNetworking.post(Config.BASE_URL_API + "auth.php")
                            .addBodyParameter(body)
                            .setPriority(Priority.MEDIUM)
                            .setOkHttpClient(((RS) getApplication()).getOkHttpClient())
                            .build()
                            .getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        String message = response.getString(Config.RESPONSE_MESSAGE_FIELD);
                                        String status = response.getString(Config.RESPONSE_STATUS_FIELD);

                                        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();

                                        if (status.equalsIgnoreCase(Config.RESPONSE_STATUS_VALUE_SUCCESS)) {
                                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                            startActivity(intent);
                                            finishAffinity();
                                        }
                                    }
                                    catch (JSONException e) {
                                        e.printStackTrace();
                                        Log.d("HBB", "JSONException: " + e.getMessage());
                                    }


                                }

                                @Override
                                public void onError(ANError anError) {

                                    Toast.makeText(RegisterActivity.this, Config.TOAST_AN_EROR, Toast.LENGTH_SHORT).show();
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
