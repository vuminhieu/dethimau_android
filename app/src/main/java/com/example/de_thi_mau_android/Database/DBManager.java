package com.example.de_thi_mau_android.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.de_thi_mau_android.Activity.MainActivity;
import com.example.de_thi_mau_android.Adapter.CustomAdapter;
import com.example.de_thi_mau_android.Model.VeTau;
import com.example.de_thi_mau_android.R;

import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {

    private Context context;
    public static final String DATABASE_NAME = "vetaus_manager";
    private static final String TABLE_NAME = "vetaus";
    private static final String ID = "id";
    private static final String GIADI = "giadi";
    private static final String GIADEN = "gaden";
    private static final String GIA = "gia";
    private static final String KHUHOI = "khuhoi";
    private static int VERSION = 1;

    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    private String SQLQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            GIADI + " TEXT, " +
            GIADEN + " TEXT, " +
            GIA + " TEXT, " +
            KHUHOI + " TEXT)";

    private String SQLQuery1 = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            GIADI + " TEXT, " +
            GIADEN + " TEXT, " +
            GIA + " TEXT, " +
            KHUHOI + " TEXT)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery);
    }
    public void them() {
        SQLiteDatabase db1 = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GIADI, "Thai Nguyen");
        values.put(GIADEN, "TPHCM");
        values.put(GIA, "300000");
        values.put(KHUHOI,"Co");
        db1.insert(TABLE_NAME, null, values);
        db1.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void addVeTau(VeTau veTau) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GIADI, veTau.getmGaDi());
        values.put(GIADEN, veTau.getmGaDen());
        values.put(GIA, veTau.getmDonGia());
        values.put(KHUHOI, veTau.getmKhuHoi());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // get tat ca cac gia tri cua ve tau
    public List<VeTau> getAllVeTau() {
        List<VeTau> veTauList = new ArrayList<>();
        String abc = "INSERT INTO vetaus VALUES (null, '" + "Thai Nguyen" + "', '" + "Thai Binh" + "', '" + "300000" + "', '" + "abc" + "')";

        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                VeTau veTau = new VeTau();
                veTau.setmID(cursor.getInt(0));
                veTau.setmGaDi(cursor.getString(1));
                veTau.setmGaDen(cursor.getString(2));
                veTau.setmDonGia(cursor.getString(3));
                veTau.setmKhuHoi(cursor.getString(4));
                veTauList.add(veTau);
            }
            while (cursor.moveToNext());
        }
        db.close();
        return veTauList;
    }

    public int updateVeTau(VeTau veTau) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GIADI, veTau.getmGaDi());
        contentValues.put(GIADEN, veTau.getmGaDen());
        contentValues.put(GIA, veTau.getmDonGia());
        contentValues.put(KHUHOI, veTau.getmKhuHoi());
        String where = ID + " = " + veTau.getmID();
        return db.update(TABLE_NAME, contentValues, where, null);
    }

    public int Delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = ID + " = " + id;
        return db.delete(TABLE_NAME, where, null);
    }

}
