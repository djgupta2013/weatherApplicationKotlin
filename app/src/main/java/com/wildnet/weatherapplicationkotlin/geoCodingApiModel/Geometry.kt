package com.wildnet.weatherapplicationkotlin.geoCodingApiModel




class Geometry {
    private var location: Location? = null

    fun getLocation(): Location? {
        return location
    }

    fun setLocation(location: Location) {
        this.location = location
    }
}