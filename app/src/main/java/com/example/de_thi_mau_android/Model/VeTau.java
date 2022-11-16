package com.example.de_thi_mau_android.Model;

public class VeTau {
    private int mID;
    private String mGaDi;
    private String mGaDen;
    private String mDonGia;
    private String mKhuHoi;

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getmGaDi() {
        return mGaDi;
    }

    public void setmGaDi(String mGaDi) {
        this.mGaDi = mGaDi;
    }

    public String getmGaDen() {
        return mGaDen;
    }

    public void setmGaDen(String mGaDen) {
        this.mGaDen = mGaDen;
    }

    public String getmDonGia() {
        return mDonGia;
    }

    public void setmDonGia(String mDonGia) {
        this.mDonGia = mDonGia;
    }

    public String getmKhuHoi() {
        return mKhuHoi;
    }

    public void setmKhuHoi(String mKhuHoi) {
        this.mKhuHoi = mKhuHoi;
    }

    public VeTau() {
    }

    public VeTau(String mGaDi, String mGaDen, String mDonGia, String mKhuHoi) {
        this.mGaDi = mGaDi;
        this.mGaDen = mGaDen;
        this.mDonGia = mDonGia;
        this.mKhuHoi = mKhuHoi;
    }

    public VeTau(int mID, String mGaDi, String mGaDen, String mDonGia, String mKhuHoi) {
        this.mID = mID;
        this.mGaDi = mGaDi;
        this.mGaDen = mGaDen;
        this.mDonGia = mDonGia;
        this.mKhuHoi = mKhuHoi;
    }
}
