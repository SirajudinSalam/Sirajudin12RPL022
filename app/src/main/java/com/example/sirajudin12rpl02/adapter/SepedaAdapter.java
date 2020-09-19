package com.example.sirajudin12rpl02.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sirajudin12rpl02.R;
import com.example.sirajudin12rpl02.activity.customer.SepedaActivity;
import com.example.sirajudin12rpl02.helper.AppHelper;
import com.example.sirajudin12rpl02.helper.Config;
import com.example.sirajudin12rpl02.model.SepedaModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SepedaAdapter extends RecyclerView.Adapter<SepedaAdapter.SepedaViewHolder> {

    private Context mContext;
    private List<SepedaModel> mList;
    private String mLoginToken = "";
    private boolean mBusy = false;
    private ProgressDialog mProgressDialog;
    private SepedaActivity mAdminUserActivity;

    public SepedaAdapter(Context context, List<SepedaModel> mList, String loginToken, Activity SepedaActivity) {
        this.mContext = context;
        this.mList = mList;
        this.mLoginToken = loginToken;
        this.mAdminUserActivity = (SepedaActivity) SepedaActivity;

    }

    @NonNull
    @Override
    public SepedaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_admin_sepeda_layout, parent, false);
        return new SepedaAdapter.SepedaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SepedaViewHolder holder, int position) {
        final SepedaModel Amodel = mList.get(position);
        holder.bind(Amodel);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void clearData() {
        int size = this.mList.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                this.mList.remove(0);
            }
        }
    }


    public class SepedaViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout divDetail;
        private ImageView iv_sepeda;
        public SepedaViewHolder(@NonNull View itemView) {
            super(itemView);
            divDetail = itemView.findViewById(R.id.item_sepeda);
            iv_sepeda = itemView.findViewById(R.id.ivUnitImg);
        }
        private void bind(final SepedaModel Amodel) {
            if(Amodel.getUNIT_GAMBAR().contains(Config.UPLOAD_FOLDER)) {
                Picasso.with(mContext)
                        .load(Config.BASE_URL + Amodel.getUNIT_GAMBAR())
                        .into(iv_sepeda);
            }
            else {
                Picasso.with(mContext)
                        .load(Config.BASE_URL_UPLOADS + Amodel.getUNIT_GAMBAR())
                        .into(iv_sepeda);
            }
            divDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppHelper.goToSepedaDetail(mContext, Amodel);
                }
            });

        }
    }
}
