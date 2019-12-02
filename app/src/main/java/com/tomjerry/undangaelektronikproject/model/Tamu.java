package com.tomjerry.undangaelektronikproject.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Tamu implements Parcelable{

    private int id;
    private String nama;
    private String alamat;
    private int no;


    public Tamu() {
    }

    public Tamu(String nama , String alamat , int no) {
        super();
        this.nama = nama;
        this.alamat = alamat;
        this.no = no;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nama);
        dest.writeString(this.alamat);
        dest.writeInt(this.no);
    }

    protected Tamu(Parcel in) {
        this.id = in.readInt();
        this.nama = in.readString();
        this.alamat = in.readString();
        this.no = in.readInt();

    }

    public static final Parcelable.Creator<Tamu> CREATOR = new Parcelable.Creator<Tamu>() {
        public Tamu createFromParcel(Parcel source) {
            return new Tamu(source);
        }

        public Tamu[] newArray(int size) {
            return new Tamu[size];
        }
    };

}

