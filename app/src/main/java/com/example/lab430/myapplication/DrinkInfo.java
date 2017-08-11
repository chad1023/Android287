package com.example.lab430.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lab430 on 2017/8/9.
 */

public class DrinkInfo implements Parcelable {
    public int imgId;
    public String name;
    public int price;
    public int heat;
    public float sugar;

    public DrinkInfo()
    {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.imgId);
        dest.writeString(this.name);
        dest.writeInt(this.price);
        dest.writeInt(this.heat);
        dest.writeFloat(this.sugar);
    }

    protected DrinkInfo(Parcel in) {
        this.imgId = in.readInt();
        this.name = in.readString();
        this.price = in.readInt();
        this.heat = in.readInt();
        this.sugar = in.readFloat();
    }

    public static final Parcelable.Creator<DrinkInfo> CREATOR = new Parcelable.Creator<DrinkInfo>() {
        @Override
        public DrinkInfo createFromParcel(Parcel source) {
            return new DrinkInfo(source);
        }

        @Override
        public DrinkInfo[] newArray(int size) {
            return new DrinkInfo[size];
        }
    };
}
