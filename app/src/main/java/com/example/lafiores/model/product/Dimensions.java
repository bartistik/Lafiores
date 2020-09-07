package com.example.lafiores.model.product;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dimensions implements Parcelable
{

    @SerializedName("length")
    @Expose
    private String length;
    @SerializedName("width")
    @Expose
    private String width;
    @SerializedName("height")
    @Expose
    private String height;
    public final static Parcelable.Creator<Dimensions> CREATOR = new Creator<Dimensions>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Dimensions createFromParcel(Parcel in) {
            return new Dimensions(in);
        }

        public Dimensions[] newArray(int size) {
            return (new Dimensions[size]);
        }

    }
            ;

    protected Dimensions(Parcel in) {
        this.length = ((String) in.readValue((String.class.getClassLoader())));
        this.width = ((String) in.readValue((String.class.getClassLoader())));
        this.height = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Dimensions() {
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(length);
        dest.writeValue(width);
        dest.writeValue(height);
    }

    public int describeContents() {
        return 0;
    }

}