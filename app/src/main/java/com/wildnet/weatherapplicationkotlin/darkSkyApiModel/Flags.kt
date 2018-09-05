package com.wildnet.weatherapplicationkotlin.darkSkyApiModel

import com.google.firebase.database.Exclude

import java.util.ArrayList

class Flags {
    @Exclude
    var madisStations: ArrayList<String>? = null
    @Exclude
    var isdStations: ArrayList<String>? = null

    var units: String? = null
    @Exclude
    var sources: ArrayList<String>? = null

    /* @Override
    public String toString() {
        return "ClassPojo [madis-stations = " + madisStations + ", isd-stations = " + isdStations + ", units = " + units + ", sources = " + sources + "]";
    }*/
}
