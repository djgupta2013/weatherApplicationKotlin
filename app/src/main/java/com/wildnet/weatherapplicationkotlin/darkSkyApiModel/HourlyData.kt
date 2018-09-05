package com.wildnet.weatherapplicationkotlin.darkSkyApiModel

class HourlyData {
    var summary: String? = null

    var icon: String? = null

    var pressure: String? = null

    var cloudCover: String? = null

    var apparentTemperature: String? = null

    var precipType: String? = null

    var precipIntensity: String? = null

    var temperature: String? = null

    var dewPoint: String? = null

    var ozone: String? = null

    var time: String? = null

    var windSpeed: String? = null

    var humidity: String? = null

    var windBearing: String? = null

    var precipProbability: String? = null

    var visibility: String? = null

    var windGust: String? = null


    /*@Override
    public String toString()
    {
        return "ClassPojo [summary = "+summary+", icon = "+icon+", pressure = "+pressure+", cloudCover = "+cloudCover+", apparentTemperature = "+apparentTemperature+", precipType = "+precipType+", precipIntensity = "+precipIntensity+", temperature = "+temperature+", dewPoint = "+dewPoint+", ozone = "+ozone+", time = "+time+", windSpeed = "+windSpeed+", humidity = "+humidity+", windBearing = "+windBearing+", precipProbability = "+precipProbability+"]";
    }*/
}