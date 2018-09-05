package com.wildnet.weatherapplicationkotlin.darkSkyApiModel

import com.google.firebase.database.Exclude

import java.util.ArrayList

class Hourly {
    var summary: String? = null

    var icon: String? = null
    @Exclude
    var data: ArrayList<HourlyData>? = null

    /* @Override
    public String toString()
    {
        return "ClassPojo [summary = "+summary+", icon = "+icon+", data = "+data+"]";
    }*/
}