package com.example.sirajudin12rpl02.activity.admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.example.sirajudin12rpl02.R;
import com.example.sirajudin12rpl02.RS;
import com.example.sirajudin12rpl02.activity.LoginActivity;
import com.example.sirajudin12rpl02.activity.RegisterActivity;
import com.example.sirajudin12rpl02.helper.Config;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;

public class AdminSepedaCreateActivity extends AppCompatActivity {

    private EditText et_kode, et_merk, et_warna, et_hargasewa;
    private Button btn_buat;
    private ImageView iv_sepeda;
    private String mSelectedImagePath = "";
    private boolean mIsFormFilled = false;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sepeda_create);
        binding();
    }
    private void binding(){
        iv_sepeda = findViewById(R.id.ivUnitImg);
        iv_sepeda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage(AdminSepedaCreateActivity.this);
            }
        });
        et_kode = findViewById(R.id.et_kode);
        et_merk = findViewById(R.id.et_merk);
        et_warna = findViewById(R.id.et_warna);
        et_hargasewa = findViewById(R.id.et_hargasewa);
        btn_buat = findViewById(R.id.btn_buat);
        btn_buat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsFormFilled = true;
                File uploadFile = new File(mSelectedImagePath);
                final String name = et_kode.getText().toString();
                final String phone = et_merk.getText().toString();
                final String address = et_warna.getText().toString();
                final String ktp = et_hargasewa.getText().toString();


                if (name.isEmpty() || phone.isEmpty() || address.isEmpty() || ktp.isEmpty()) {
                    Toast.makeText(AdminSepedaCreateActivity.this, "Harap lengkapi isian yang tersedia", Toast.LENGTH_SHORT).show();
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
                    body.put("act", "create_unit");
//                    try {
//                        final MCrypt mcrypt = new MCrypt();
//                        String encrypted = MCrypt.bytesToHex(mcrypt.encrypt(password));
//                        body.put("passwordEnc", encrypted);
//                    }
//                    catch (Exception e) {
//                        Log.d("RBA", "Exception : " + e.getMessage());
//                        body.put("password", password);
//                    }



                    body.put("unitKode", phone);
                    body.put("unitMerk", name);
                    body.put("unitWarna", address);
                    body.put("unitHargasewa", ktp);

                    final ProgressDialog uploadLoading = getUploadLoading();
                    AndroidNetworking.upload(Config.BASE_URL_API + "unitfrontend.php")
                            .addMultipartFile(body)
                            .addMultipartFile("uploadFile", uploadFile)
                            .setPriority(Priority.HIGH)
                            .setOkHttpClient(((RS) getApplication()).getOkHttpClient())
                            .build()
                            .setUploadProgressListener(new UploadProgressListener() {
                                @Override
                                public void onProgress(long bytesUploaded, long totalBytes) {
                                    uploadLoading.setProgress((int) bytesUploaded);
                                    uploadLoading.setMax((int) totalBytes);
                                    if (!uploadLoading.isShowing()) uploadLoading.show();

                                }
                            })
                            .getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {

                                    uploadLoading.dismiss();

                                    Log.d("HBB", "onResponseImage: " + response);
                                    String status = response.optString(Config.RESPONSE_STATUS_FIELD);
                                    String message = response.optString(Config.RESPONSE_MESSAGE_FIELD);

                                    Toast.makeText(AdminSepedaCreateActivity.this, message, Toast.LENGTH_LONG).show();

                                    if (status.trim().equalsIgnoreCase(Config.RESPONSE_STATUS_VALUE_SUCCESS)) {
                                        AdminSepedaCreateActivity.this.getSupportFragmentManager().popBackStack();
                                    }
                                    else {
                                        Toast.makeText(AdminSepedaCreateActivity.this,"proses gagal",Toast.LENGTH_SHORT).show();
                                    }

                                }

                                @Override
                                public void onError(ANError anError) {


                                    uploadLoading.dismiss();
                                    Log.d("HBB", "onError: " + anError.getErrorBody());
                                    Log.d("HBB", "onError: " + anError.getLocalizedMessage());
                                    Log.d("HBB", "onError: " + anError.getErrorDetail());
                                    Log.d("HBB", "onError: " + anError.getResponse());
                                    Log.d("HBB", "onError: " + anError.getErrorCode());
                                    Toast.makeText(AdminSepedaCreateActivity.this, Config.TOAST_AN_EROR, Toast.LENGTH_SHORT).show();

                                }
                            });
                }

            }
        });
    }

    private ProgressDialog getUploadLoading() {
        ProgressDialog dialog = new ProgressDialog(AdminSepedaCreateActivity.this);
        dialog.setTitle("Proses Upload");
        dialog.setMessage("Mohon tunggu ...");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setCancelable(false);
        return dialog;
    }


    private void selectImage(Context context) {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 1);//one can be replaced with any action code

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("uploadFile");
                        iv_sepeda.setImageBitmap(selectedImage);
                    }

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();

                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                iv_sepeda.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                                cursor.close();
                            }
                        }

                    }
                    break;
            }
        }
    }
}
