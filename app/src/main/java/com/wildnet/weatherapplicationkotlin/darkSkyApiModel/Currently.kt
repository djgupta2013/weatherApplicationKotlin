package com.wildnet.weatherapplicationkotlin.darkSkyApiModel

class Currently {
    private var summary: String? = null

    private lateinit var icon: String

    private var pressure: String? = null

    private var cloudCover: String? = null

    private var apparentTemperature: String? = null

    private var precipType: String? = null

    private var precipIntensity: String? = null

    private var temperature: String? = null

    private var dewPoint: String? = null

    private var ozone: String? = null

    private var time: String? = null

    private lateinit var windSpeed: String

    private var humidity: String? = null

    private var windBearing: String? = null

    private var precipProbability: String? = null

    private var nearestStormDistance: String? = null

    private var precipIntensityError: String? = null

    private var windGust: String? = null

    private var visibility: String? = null


    fun getSummary(): String {
        return summary!!.replace("-", " ")
    }

    fun setSummary(summary: String) {
        this.summary = summary
    }

    fun getIcon(): String {
        return icon
    }

    fun setIcon(icon: String) {
        this.icon = icon
    }

    fun getPressure(): String? {
        return pressure
    }

    fun setPressure(pressure: String) {
        this.pressure = pressure
    }

    fun getCloudCover(): String? {
        return cloudCover
    }

    fun setCloudCover(cloudCover: String) {
        this.cloudCover = cloudCover
    }

    fun getApparentTemperature(): String? {
        return apparentTemperature
    }

    fun setApparentTemperature(apparentTemperature: String) {
        this.apparentTemperature = apparentTemperature
    }

    fun setPrecipType(precipType: String) {
        this.precipType = precipType
    }

    fun getPrecipIntensity(): String? {
        return precipIntensity
    }

    fun setPrecipIntensity(precipIntensity: String) {
        this.precipIntensity = precipIntensity
    }

    fun getTemperature(): String? {
        return temperature
    }

    fun setTemperature(temperature: String) {
        this.temperature = temperature
    }

    fun getDewPoint(): String? {
        return dewPoint
    }

    fun setDewPoint(dewPoint: String) {
        this.dewPoint = dewPoint
    }

    fun getOzone(): String? {
        return ozone
    }

    fun setOzone(ozone: String) {
        this.ozone = ozone
    }

    fun getTime(): String? {
        return time
    }

    fun setTime(time: String) {
        this.time = time
    }

    fun getWindSpeed(): String {
        return windSpeed
    }

    fun setWindSpeed(windSpeed: String) {
        this.windSpeed = windSpeed
    }

    fun getHumidity(): String? {
        return humidity
    }

    fun setHumidity(humidity: String) {
        this.humidity = humidity
    }

    fun getWindBearing(): String? {
        return windBearing
    }

    fun setWindBearing(windBearing: String) {
        this.windBearing = windBearing
    }

    fun getPrecipProbability(): String? {
        return precipProbability
    }

    fun setPrecipProbability(precipProbability: String) {
        this.precipProbability = precipProbability
    }

    fun getNearestStormDistance(): String? {
        return nearestStormDistance
    }

    fun setNearestStormDistance(nearestStormDistance: String) {
        this.nearestStormDistance = nearestStormDistance
    }

    fun getPrecipIntensityError(): String? {
        return precipIntensityError
    }

    fun setPrecipIntensityError(precipIntensityError: String) {
        this.precipIntensityError = precipIntensityError
    }

    fun getWindGust(): String? {
        return windGust
    }

    fun setWindGust(windGust: String) {
        this.windGust = windGust
    }

    fun getVisibility(): String? {
        return visibility
    }

    fun setVisibility(visibility: String) {
        this.visibility = visibility
    }


}
