package com.wildnet.weatherapplicationkotlin.retrofit

import com.wildnet.weatherapplicationkotlin.geoCodingApiModel.LatLongModel

interface MainUIResponse {
    abstract fun onIpResponse(ip: String)
    abstract fun onLatLngResponse(model: LatLongModel)
}