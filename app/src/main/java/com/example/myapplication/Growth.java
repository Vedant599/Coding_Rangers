package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

public class Growth {
    @SerializedName("Year")
    private int Year;
    @SerializedName("Growth_Rate")
    private float Growth_Rate;

    public int getYear() {
        return Year;
    }

    public float getGrowth_rate() {
        return Growth_Rate;
    }
}
