package com.example.de_thi_mau_android.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.de_thi_mau_android.Adapter.CustomAdapter;
import com.example.de_thi_mau_android.Database.DBManager;
import com.example.de_thi_mau_android.Model.VeTau;
import com.example.de_thi_mau_android.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button buttonThem;
    ListView listViewDS;
    CustomAdapter customAdapter;
    List<VeTau> veTaus;
    private static final int My_Request_Code = 99;
    DBManager dbManager = new DBManager(this);


    private  void  mapping() {
        listViewDS = (ListView) findViewById(R.id.lv_danh_sach);
        buttonThem = (Button) findViewById(R.id.btn_them);
    }

    private void setAdapter() {
        if (customAdapter == null) {
            customAdapter = new CustomAdapter(this, R.layout.dong_ve_tau, veTaus);
            listViewDS.setAdapter(customAdapter);
        } else {
            customAdapter.notifyDataSetChanged();
            listViewDS.setSelection(customAdapter.getCount() - 1);
        }
    }

    public void updateListVeTau() {
        veTaus.clear();
        veTaus.addAll(dbManager.getAllVeTau());
        if (customAdapter != null) {
            customAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        veTaus = dbManager.getAllVeTau();
        setAdapter();
        registerForContextMenu(listViewDS);


        buttonThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, adding.class);
                startActivityForResult(intent, My_Request_Code);
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo i = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        VeTau veTau = veTaus.get(i.position);

        if (item.getItemId() == R.id.item_edit) {
            DialogUpdate(veTau);
        }
        if (item.getItemId() == R.id.item_delete) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Thông báo");
            alertDialog.setIcon(R.mipmap.ic_launcher);
            alertDialog.setMessage("Bạn có chắc chắn xoá không");
            alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    int result = dbManager.Delete(veTau.getmID());
                    if (result > 0) {
                        updateListVeTau();
                        Toast.makeText(MainActivity.this, "Xoá thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Xoá bị lỗi", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            alertDialog.show();




        }

        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (My_Request_Code == requestCode && resultCode == adding.My_Result_Code) {
            String gadi = data.getStringExtra("key_gadi");
            String gaden = data.getStringExtra("key_gaden");
            String gia = data.getStringExtra("key_gia");
            String khuhoi = data.getStringExtra("key_khuhoi");
            veTaus.add(new VeTau(gadi,gaden,gia,khuhoi));
            setAdapter();
        }
    }


    public void DialogUpdate(VeTau veTau) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_repair);

        EditText editTextIDUpdate = (EditText) dialog.findViewById(R.id.edt_sua_id);
        EditText editTextGaDiUpdate = (EditText) dialog.findViewById(R.id.edt_sua_ga_di);
        EditText editTextGaDenUpdate = (EditText) dialog.findViewById(R.id.edt_sua_ga_den);
        EditText editTextGiaUpdate = (EditText) dialog.findViewById(R.id.edt_sua_gia);
        RadioButton radioButtonMotChieu = (RadioButton) dialog.findViewById(R.id.rdo_sua_mot_chieu);
        RadioButton radioButtonKhuHoi = (RadioButton) dialog.findViewById(R.id.rdo_sua_khu_hoi);
        Button buttonSua = (Button) dialog.findViewById(R.id.btn_update);
        Button buttonHuy = (Button) dialog.findViewById(R.id.btn_huy);

        editTextIDUpdate.setText(veTau.getmID()+ "");
        editTextGaDiUpdate.setText(veTau.getmGaDi() + "");
        editTextGaDenUpdate.setText(veTau.getmGaDen()+ "");
        editTextGiaUpdate.setText(veTau.getmDonGia() + "");
//        radioButtonMotChieu.setText(veTau.getmKhuHoi() + "");
//        if ("Một chiều" == radioButtonMotChieu.getText().toString().trim()) {
//            radioButtonMotChieu.isChecked();
//        }
//        if ("Khứ hồi" == radioButtonKhuHoi.getText().toString().trim()) {
//            radioButtonKhuHoi.isChecked();
//        }
        buttonHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        buttonSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VeTau veTau1 = new VeTau();
                veTau1.setmID(Integer.parseInt(String.valueOf(editTextIDUpdate.getText())));
                veTau1.setmGaDi(editTextGaDiUpdate.getText() + "");
                veTau1.setmGaDen(editTextGaDenUpdate.getText() + "");
                veTau1.setmDonGia(editTextGiaUpdate.getText() + "");
                String khuhoi = "";
                if (radioButtonMotChieu.isChecked()) {
                    khuhoi = khuhoi + radioButtonMotChieu.getText().toString();
                }
                if (radioButtonKhuHoi.isChecked()) {
                    khuhoi = khuhoi + radioButtonKhuHoi.getText().toString();
                }
                veTau1.setmKhuHoi(khuhoi+ "");
                int result = dbManager.updateVeTau(veTau1);
                if (result > 0) {
                    dbManager.updateVeTau(veTau1);
                    updateListVeTau();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }


}