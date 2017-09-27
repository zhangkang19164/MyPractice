package com.example.practice.customview.imooc.pie;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * create time : 2017/09/26
 * desc        :
 */

public class PieChartBean implements Parcelable {

    private String date;
    private List<ObjBean> obj;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ObjBean> getObj() {
        return obj;
    }

    public void setObj(List<ObjBean> obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "PieChartBean{" +
                "date='" + date + '\'' +
                ", obj=" + obj.toString() +
                '}';
    }




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
        dest.writeList(this.obj);
    }

    public PieChartBean() {
    }

    protected PieChartBean(Parcel in) {
        this.date = in.readString();
        this.obj = new ArrayList<ObjBean>();
        in.readList(this.obj, ObjBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<PieChartBean> CREATOR = new Parcelable.Creator<PieChartBean>() {
        @Override
        public PieChartBean createFromParcel(Parcel source) {
            return new PieChartBean(source);
        }

        @Override
        public PieChartBean[] newArray(int size) {
            return new PieChartBean[size];
        }
    };

    public static class ObjBean implements Parcelable {

        private String title;
        private int value;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "ObjBean{" +
                    "title='" + title + '\'' +
                    ", value=" + value +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.title);
            dest.writeInt(this.value);
        }

        public ObjBean() {
        }

        protected ObjBean(Parcel in) {
            this.title = in.readString();
            this.value = in.readInt();
        }

        public static final Creator<ObjBean> CREATOR = new Creator<ObjBean>() {
            @Override
            public ObjBean createFromParcel(Parcel source) {
                return new ObjBean(source);
            }

            @Override
            public ObjBean[] newArray(int size) {
                return new ObjBean[size];
            }
        };
    }
}
