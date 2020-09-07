package com.example.lafiores.model.product;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DefaultAttribute implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("option")
    @Expose
    private String option;
    public final static Parcelable.Creator<DefaultAttribute> CREATOR = new Creator<DefaultAttribute>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DefaultAttribute createFromParcel(Parcel in) {
            return new DefaultAttribute(in);
        }

        public DefaultAttribute[] newArray(int size) {
            return (new DefaultAttribute[size]);
        }

    }
            ;

    protected DefaultAttribute(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.option = ((String) in.readValue((String.class.getClassLoader())));
    }

    public DefaultAttribute() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(option);
    }

    public int describeContents() {
        return 0;
    }

}