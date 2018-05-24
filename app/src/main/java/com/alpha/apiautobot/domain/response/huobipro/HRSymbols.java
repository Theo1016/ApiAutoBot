package com.alpha.apiautobot.domain.response.huobipro;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * <pre>
 *     author : hedong
 *     e-mail : 739814501@qq.com
 *     time   : 2018/05/23
 *     desc   : xxxx描述
 *     version: 1.0
 * </pre>
 */
public class HRSymbols implements Serializable{
    public String status;
    public ArrayList<Data> data;

    public static class Data implements Parcelable {
        @SerializedName("base-currency")
        public String baseCurrency;
        @SerializedName("quote-currency")
        public String quoteCurrency;
        @SerializedName("price-precision")
        public Integer pricePrecision;
        @SerializedName("amount-precision")
        public Integer amountPrecision;
        @SerializedName("symbol-partition")
        public String symbolPartition;

        public Data() {
        }

        protected Data(Parcel in) {
            baseCurrency = in.readString();
            quoteCurrency = in.readString();
            if (in.readByte() == 0) {
                pricePrecision = null;
            } else {
                pricePrecision = in.readInt();
            }
            if (in.readByte() == 0) {
                amountPrecision = null;
            } else {
                amountPrecision = in.readInt();
            }
            symbolPartition = in.readString();
        }

        public static final Creator<Data> CREATOR = new Creator<Data>() {
            @Override
            public Data createFromParcel(Parcel in) {
                return new Data(in);
            }

            @Override
            public Data[] newArray(int size) {
                return new Data[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(baseCurrency);
            parcel.writeString(quoteCurrency);
            if (pricePrecision == null) {
                parcel.writeByte((byte) 0);
            } else {
                parcel.writeByte((byte) 1);
                parcel.writeInt(pricePrecision);
            }
            if (amountPrecision == null) {
                parcel.writeByte((byte) 0);
            } else {
                parcel.writeByte((byte) 1);
                parcel.writeInt(amountPrecision);
            }
            parcel.writeString(symbolPartition);
        }
    }
}
