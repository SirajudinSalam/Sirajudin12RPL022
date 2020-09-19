package com.example.sirajudin12rpl02.helper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.sirajudin12rpl02.activity.admin.AdminSepedaDetailActivity;
import com.example.sirajudin12rpl02.activity.customer.SepedaDetailActivity;
import com.example.sirajudin12rpl02.model.AdminUserModel;
import com.example.sirajudin12rpl02.model.SepedaModel;

import org.json.JSONObject;

public final class AppHelper {
    public static AdminUserModel mapUserAdminModel(JSONObject rowData) {
        AdminUserModel item = new AdminUserModel();
        item.setU_ID(rowData.optString("U_ID"));
        item.setU_NAME(rowData.optString("U_NAME"));
        item.setU_ADDRESS(rowData.optString("U_ADDRESS"));
        item.setU_AUTHORITY_ID_1(rowData.optString("U_AUTHORITY_ID_1"));
        item.setU_EMAIL(rowData.optString("U_EMAIL"));
        item.setU_PHONE(rowData.optString("U_PHONE"));
        item.setU_GROUP_ROLE(rowData.optString("GROUP_ROLE"));


        return item;
    }

    public static void goToUserAdminDetail(Context context, AdminUserModel rowData) {
        Bundle bundle = new Bundle();

        bundle.putString("U_ID", String.valueOf(rowData.getU_ID()));
        bundle.putString("U_NAME", rowData.getU_NAME().toUpperCase());
        bundle.putString("U_ADDRESS", rowData.getU_ADDRESS().toUpperCase());
        bundle.putString("U_AUTHORITY_ID_1", rowData.getU_AUTHORITY_ID_1());
        bundle.putString("U_EMAIL", rowData.getU_EMAIL());
        bundle.putString("U_PHONE", rowData.getU_PHONE().toUpperCase());
        bundle.putString("GROUP_ROLE", rowData.getU_GROUP_ROLE().toUpperCase());

//        Intent i = new Intent(context, CustomerDetailActivity.class);
//        i.putExtra("extra_customer", rowData);
//        context.startActivity(i);
    }

    public static SepedaModel mapAdminSepedaModel(JSONObject rowData) {
        SepedaModel item = new SepedaModel();
        item.setUNIT_ID(rowData.optString("UNIT_ID"));
        item.setUNIT_KODE(rowData.optString("UNIT_KODE"));
        item.setUNIT_MERK(rowData.optString("UNIT_MERK"));
        item.setUNIT_WARNA(rowData.optString("UNIT_WARNA"));
        item.setUNIT_HARGASEWA(rowData.optString("UNIT_HARGASEWA"));
        item.setUNIT_GAMBAR(rowData.optString("UNIT_GAMBAR"));


        return item;
    }

    public static void goToAdminSepedaDetail(Context context, SepedaModel rowData) {
        Bundle bundle = new Bundle();

        bundle.putString("UNIT_ID", String.valueOf(rowData.getUNIT_ID()));
        bundle.putString("UNIT_KODE", rowData.getUNIT_KODE().toUpperCase());
        bundle.putString("UNIT_MERK", rowData.getUNIT_MERK().toUpperCase());
        bundle.putString("UNIT_WARNA", rowData.getUNIT_WARNA());
        bundle.putString("UNIT_HARGASEWA", rowData.getUNIT_HARGASEWA());
        bundle.putString("UNIT_GAMBAR", rowData.getUNIT_GAMBAR().toUpperCase());

        Intent i = new Intent(context, AdminSepedaDetailActivity.class);
        i.putExtra("extra_sepeda", rowData);
        context.startActivity(i);
    }

    public static SepedaModel mapSepedaModel(JSONObject rowData) {
        SepedaModel item = new SepedaModel();
        item.setUNIT_ID(rowData.optString("UNIT_ID"));
        item.setUNIT_KODE(rowData.optString("UNIT_KODE"));
        item.setUNIT_MERK(rowData.optString("UNIT_MERK"));
        item.setUNIT_WARNA(rowData.optString("UNIT_WARNA"));
        item.setUNIT_HARGASEWA(rowData.optString("UNIT_HARGASEWA"));
        item.setUNIT_GAMBAR(rowData.optString("UNIT_GAMBAR"));


        return item;
    }

    public static void goToSepedaDetail(Context context, SepedaModel rowData) {
        Bundle bundle = new Bundle();

        bundle.putString("UNIT_ID", String.valueOf(rowData.getUNIT_ID()));
        bundle.putString("UNIT_KODE", rowData.getUNIT_KODE().toUpperCase());
        bundle.putString("UNIT_MERK", rowData.getUNIT_MERK().toUpperCase());
        bundle.putString("UNIT_WARNA", rowData.getUNIT_WARNA());
        bundle.putString("UNIT_HARGASEWA", rowData.getUNIT_HARGASEWA());
        bundle.putString("UNIT_GAMBAR", rowData.getUNIT_GAMBAR().toUpperCase());

        Intent i = new Intent(context, SepedaDetailActivity.class);
        i.putExtra("extra_sepeda", rowData);
        context.startActivity(i);
    }
}
