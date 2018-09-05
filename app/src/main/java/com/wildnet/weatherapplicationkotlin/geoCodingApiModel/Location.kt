package com.wildnet.weatherapplicationkotlin.geoCodingApiModel

class Location {
    private var lng: String? = null

    private var lat: String? = null

    fun getLng(): String? {
        return lng
    }

    fun setLng(lng: String) {
        this.lng = lng
    }

    fun getLat(): String? {
        return lat
    }

    fun setLat(lat: String) {
        this.lat = lat
    }
}
