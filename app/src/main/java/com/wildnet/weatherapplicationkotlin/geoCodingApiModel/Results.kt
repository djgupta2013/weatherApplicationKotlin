package com.wildnet.weatherapplicationkotlin.geoCodingApiModel

import java.util.ArrayList

class Results {
    private var place_id: String? = null

    private var address_components: ArrayList<Address_components>? = null

    private var geometry: Geometry? = null

    private var formatted_address: String? = null

    fun getPlace_id(): String? {
        return place_id
    }

    fun setPlace_id(place_id: String) {
        this.place_id = place_id
    }

    fun getAddress_components(): ArrayList<Address_components>? {
        return address_components
    }

    fun setAddress_components(address_components: ArrayList<Address_components>) {
        this.address_components = address_components
    }

    fun getGeometry(): Geometry? {
        return geometry
    }

    fun setGeometry(geometry: Geometry) {
        this.geometry = geometry
    }

    fun getFormatted_address(): String? {
        return formatted_address
    }

    fun setFormatted_address(formatted_address: String) {
        this.formatted_address = formatted_address
    }
}