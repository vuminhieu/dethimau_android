package com.example.de_thi_mau_android.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.de_thi_mau_android.Activity.MainActivity;
import com.example.de_thi_mau_android.Model.VeTau;
import com.example.de_thi_mau_android.R;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<VeTau> {
    private MainActivity context;
    private int resource;
    private List<VeTau> veTauList;


    public CustomAdapter(@NonNull MainActivity context, int resource, @NonNull List<VeTau> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.veTauList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.dong_ve_tau, parent,false);
            viewHolder.textViewGaDi = convertView.findViewById(R.id.tv_gia_di);
            viewHolder.textViewGaDen = convertView.findViewById(R.id.tv_gia_den);
            viewHolder.textViewDonGia = convertView.findViewById(R.id.tv_gia);
            viewHolder.textViewKhuHoi = convertView.findViewById(R.id.tv_khu_hoi);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        VeTau veTau = veTauList.get(position);
        viewHolder.textViewGaDi.setText(String.valueOf(veTau.getmGaDi()));
        viewHolder.textViewGaDen.setText(String.valueOf(veTau.getmGaDen()));
        viewHolder.textViewDonGia.setText(String.valueOf(veTau.getmDonGia()));
        viewHolder.textViewKhuHoi.setText(String.valueOf(veTau.getmKhuHoi()));

        return convertView;
    }

    public class ViewHolder {
        TextView textViewGaDi, textViewGaDen, textViewDonGia, textViewKhuHoi;
    }
}
