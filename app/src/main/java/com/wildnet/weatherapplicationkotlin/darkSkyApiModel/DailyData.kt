package com.wildnet.weatherapplicationkotlin.darkSkyApiModel

class DailyData {
    private var summary: String? = null

    private var icon: String? = null

    private var pressure: String? = null

    private var cloudCover: String? = null

    private var precipType: String? = null

    private var precipIntensity: String? = null

    private var dewPoint: String? = null

    private var ozone: String? = null

    private var time: String? = null

    private var windSpeed: String? = null

    private var humidity: String? = null

    private var windBearing: String? = null

    private var precipProbability: String? = null

    private var visibility: String? = null

    private var sunriseTime: String? = null

    private var sunsetTime: String? = null

    private var moonPhase: String? = null

    private var precipIntensityMax: String? = null

    private var precipIntensityMaxTime: String? = null

    private var temperatureMin: String? = null

    private var temperatureMinTime: String? = null

    private var temperatureMax: String? = null

    private var temperatureMaxTime: String? = null

    private var apparentTemperatureMin: String? = null

    private var apparentTemperatureMinTime: String? = null

    private var apparentTemperatureMax: String? = null

    private var apparentTemperatureMaxTime: String? = null


    fun getSummary(): String? {
        return summary
    }

    fun setSummary(summary: String) {
        this.summary = summary
    }

    fun getIcon(): String? {
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

    fun getPrecipType(): String? {
        return precipType
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

    fun getWindSpeed(): String? {
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

    fun getVisibility(): String? {
        return visibility
    }

    fun setVisibility(visibility: String) {
        this.visibility = visibility
    }

    fun getSunriseTime(): String? {
        return sunriseTime
    }

    fun setSunriseTime(sunriseTime: String) {
        this.sunriseTime = sunriseTime
    }

    fun getSunsetTime(): String? {
        return sunsetTime
    }

    fun setSunsetTime(sunsetTime: String) {
        this.sunsetTime = sunsetTime
    }

    fun getMoonPhase(): String? {
        return moonPhase
    }

    fun setMoonPhase(moonPhase: String) {
        this.moonPhase = moonPhase
    }

    fun getPrecipIntensityMax(): String? {
        return precipIntensityMax
    }

    fun setPrecipIntensityMax(precipIntensityMax: String) {
        this.precipIntensityMax = precipIntensityMax
    }

    fun getPrecipIntensityMaxTime(): String? {
        return precipIntensityMaxTime
    }

    fun setPrecipIntensityMaxTime(precipIntensityMaxTime: String) {
        this.precipIntensityMaxTime = precipIntensityMaxTime
    }

    fun getTemperatureMin(): String? {
        return temperatureMin
    }

    fun setTemperatureMin(temperatureMin: String) {
        this.temperatureMin = temperatureMin
    }

    fun getTemperatureMinTime(): String? {
        return temperatureMinTime
    }

    fun setTemperatureMinTime(temperatureMinTime: String) {
        this.temperatureMinTime = temperatureMinTime
    }

    fun getTemperatureMax(): String? {
        return temperatureMax
    }

    fun setTemperatureMax(temperatureMax: String) {
        this.temperatureMax = temperatureMax
    }

    fun getTemperatureMaxTime(): String? {
        return temperatureMaxTime
    }

    fun setTemperatureMaxTime(temperatureMaxTime: String) {
        this.temperatureMaxTime = temperatureMaxTime
    }

    fun getApparentTemperatureMin(): String? {
        return apparentTemperatureMin
    }

    fun setApparentTemperatureMin(apparentTemperatureMin: String) {
        this.apparentTemperatureMin = apparentTemperatureMin
    }

    fun getApparentTemperatureMinTime(): String? {
        return apparentTemperatureMinTime
    }

    fun setApparentTemperatureMinTime(apparentTemperatureMinTime: String) {
        this.apparentTemperatureMinTime = apparentTemperatureMinTime
    }

    fun getApparentTemperatureMax(): String? {
        return apparentTemperatureMax
    }

    fun setApparentTemperatureMax(apparentTemperatureMax: String) {
        this.apparentTemperatureMax = apparentTemperatureMax
    }

    fun getApparentTemperatureMaxTime(): String? {
        return apparentTemperatureMaxTime
    }

    fun setApparentTemperatureMaxTime(apparentTemperatureMaxTime: String) {
        this.apparentTemperatureMaxTime = apparentTemperatureMaxTime
    }

}