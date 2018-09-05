package com.wildnet.weatherapplicationkotlin.darkSkyApiModel

import java.util.ArrayList

class Daily {
    var summary: String? = null

    var icon: String? = null

    var data: ArrayList<DailyData>? = null

    /* @Override
    public String toString()
    {
        return "ClassPojo [summary = "+summary+", icon = "+icon+", data = "+data+"]";
    }*/
}