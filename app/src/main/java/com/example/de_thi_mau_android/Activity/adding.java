package com.example.de_thi_mau_android.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.de_thi_mau_android.Adapter.CustomAdapter;
import com.example.de_thi_mau_android.Database.DBManager;
import com.example.de_thi_mau_android.Model.VeTau;
import com.example.de_thi_mau_android.R;

import java.util.List;

public class adding extends AppCompatActivity {
    Button buttonSave;
    EditText editTextGia, editTextGaDi, editTextGaDen, editTextId;
    RadioButton radioButtonMotChieu, radioButtonKhuHoi;
    public static final int My_Result_Code = 33;
    private CustomAdapter customAdapter;
    private List<VeTau> veTaus;
    DBManager dbManager = new DBManager(this);

    private void mapping() {
        buttonSave = (Button) findViewById(R.id.btn_luu);
        editTextGaDi = (EditText) findViewById(R.id.edt_ga_di);
        editTextGaDen = (EditText) findViewById(R.id.edt_ga_den);
        editTextId = (EditText) findViewById(R.id.edt_id);
        editTextGia = (EditText) findViewById(R.id.edt_gia);
        radioButtonMotChieu = (RadioButton) findViewById(R.id.rdo_mot_chieu);
        radioButtonKhuHoi = (RadioButton) findViewById(R.id.rdo_khu_hoi);
    }

    private VeTau createStudent() {
        String gadi = editTextGaDi.getText().toString().trim();
        String gaden = editTextGaDen.getText().toString().trim();
        String gia = editTextGia.getText().toString().trim();
        Intent intent = new Intent(adding.this, MainActivity.class);
//                intent.putExtra("key_id", id);
        intent.putExtra("key_gadi", gadi);
        intent.putExtra("key_gaden", gaden);
        intent.putExtra("key_gia", gia);
        String khuhoi = "";
        if (radioButtonMotChieu.isChecked()) {
            khuhoi = khuhoi + radioButtonMotChieu.getText().toString();
        }
        if (radioButtonKhuHoi.isChecked()) {
            khuhoi = khuhoi + radioButtonKhuHoi.getText().toString();
        }
        intent.putExtra("key_khuhoi", khuhoi);
        setResult(My_Result_Code, intent);
        VeTau veTau = new VeTau(gadi, gaden, gia, khuhoi);
        return veTau;
    }

    public void updateListStudent() {
        veTaus.clear();
        veTaus.addAll(dbManager.getAllVeTau());
        if (customAdapter != null) {
            customAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);
        mapping();

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VeTau veTau = createStudent();
                if (veTau != null) {
                    dbManager.addVeTau(veTau);
                }
                updateListStudent();
                finish();
            }
        });

    }


}