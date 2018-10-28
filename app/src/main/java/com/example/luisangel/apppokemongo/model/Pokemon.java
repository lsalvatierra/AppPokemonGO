package com.example.luisangel.apppokemongo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Pokemon implements Parcelable {


    private String url;

    protected Pokemon(Parcel in) {
        url = in.readString();
        name = in.readString();
        numero = in.readInt();
    }

    public static final Creator<Pokemon> CREATOR = new Creator<Pokemon>() {
        @Override
        public Pokemon createFromParcel(Parcel in) {
            return new Pokemon(in);
        }

        @Override
        public Pokemon[] newArray(int size) {
            return new Pokemon[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumero() {
        String [] urlPartes = url.split("/");

        return Integer.parseInt(urlPartes[urlPartes.length - 1]);
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    private String name;
    private int numero;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(name);
        dest.writeInt(numero);
    }
}
