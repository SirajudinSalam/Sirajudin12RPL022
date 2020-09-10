package com.example.sirajudin12rpl02.helper;

import android.content.Context;
import android.os.Bundle;

import com.example.sirajudin12rpl02.model.AdminUserModel;

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

}
