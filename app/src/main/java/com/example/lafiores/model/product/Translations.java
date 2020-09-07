package com.example.lafiores.model.product;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Translations implements Parcelable {

    @SerializedName("ru")
    @Expose
    private Integer ru;
    @SerializedName("uk")
    @Expose
    private Integer uk;
    public final static Parcelable.Creator<Translations> CREATOR = new Creator<Translations>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Translations createFromParcel(Parcel in) {
            return new Translations(in);
        }

        public Translations[] newArray(int size) {
            return (new Translations[size]);
        }

    };

    protected Translations(Parcel in) {
        this.ru = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.uk = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Translations() {
    }

    public Integer getRu() {
        return ru;
    }

    public void setRu(Integer ru) {
        this.ru = ru;
    }

    public Integer getUk() {
        return uk;
    }

    public void setUk(Integer uk) {
        this.uk = uk;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(ru);
        dest.writeValue(uk);
    }

    public int describeContents() {
        return 0;
    }

}