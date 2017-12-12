package com.thirio.android.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by florentchampigny on 21/06/2016.
 */
public class Travel implements Parcelable{
    public static final Creator<Travel> CREATOR = new Creator<Travel>() {
        @Override
        public Travel createFromParcel(Parcel in) {
            return new Travel(in);
        }

        @Override
        public Travel[] newArray(int size) {
            return new Travel[size];
        }
    };
    private String name;
    private int image;
    private String desc;
    private int imagedesc;

    public Travel(String name, int image, String desc, int imagedesc) {

        this.name = name;
        this.image = image;
        this.desc = desc;
        this.imagedesc = imagedesc;
    }

    public Travel(String name, int image) {
        this.name = name;
        this.image = image;
    }

    protected Travel(Parcel in) {
        name = in.readString();
        image = in.readInt();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getImagedesc() {
        return imagedesc;
    }

    public void setImagedesc(int imagedesc) {
        this.imagedesc = imagedesc;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(image);
    }
}
