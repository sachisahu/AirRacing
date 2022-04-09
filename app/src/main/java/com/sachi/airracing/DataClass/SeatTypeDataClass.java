package com.sachi.airracing.DataClass;

public class SeatTypeDataClass {
    public int id;
    public String tktType;
    public int price;

    public SeatTypeDataClass(int id, String tktType, int price) {
        this.id = id;
        this.tktType = tktType;
        this.price = price;
    }

    @Override
    public String toString() {
        return tktType;
    }
}
