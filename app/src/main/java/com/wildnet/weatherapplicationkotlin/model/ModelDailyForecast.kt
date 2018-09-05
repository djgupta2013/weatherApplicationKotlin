package com.wildnet.weatherapplicationkotlin.model

class ModelDailyForecast(day: String, title: String, temp: String, icon: String, date: String) {
    private var day: String? = null
    private var date: String? = null
    private var title: String? = null
    private var temp: String? = null
    private var icon: String? = null
    private var prob: String? = null
    private var precipType: String? = null
    private var cloudCover: Double = 0.toDouble()
    private var windSpeed: Int = 0
    private var precipIntensity: Double = 0.toDouble()

    init{
        this.day = day
        this.title = title
        this.temp = temp
        this.icon = icon
        this.date = date
    }

    /*fun ModelDailyForecast(day: String, title: String, temp: String, icon: String, prob: String, cloudCover: Double,
                           windSpeed: Int, precipType: String, precipIntensity: Double): ??? {
        this.day = day
        this.title = title
        this.temp = temp
        this.icon = icon
        this.prob = prob
        this.precipType = precipType
        this.cloudCover = cloudCover
        this.windSpeed = windSpeed
        this.precipIntensity = precipIntensity

    }*/

    fun getDate(): String? {
        return date
    }

    fun setDate(date: String) {
        this.date = date
    }

    fun getDay(): String? {
        return day
    }

    fun setDay(day: String) {
        this.day = day
    }

    fun getTitle(): String {
        return title!!.replace("-", " ")
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun getTemp(): String? {
        return temp
    }

    fun setTemp(temp: String) {
        this.temp = temp
    }

    fun getIcon(): String? {
        return icon
    }

    fun setIcon(icon: String) {
        this.icon = icon
    }

    fun getProb(): String? {
        return prob
    }

    fun setProb(prob: String) {
        this.prob = prob
    }

    fun getCloudCover(): Double {
        return cloudCover
    }

    fun setCloudCover(cloudCover: Double) {
        this.cloudCover = cloudCover
    }

    fun getWindSpeed(): Int {
        return windSpeed
    }

    fun setWindSpeed(windSpeed: Int) {
        this.windSpeed = windSpeed
    }

    fun getPrecipIntensity(): Double {
        return precipIntensity
    }

    fun setPrecipIntensity(precipIntensity: Double) {
        this.precipIntensity = precipIntensity
    }

    /*fun getPrecipType(): String {
        return if (precipType == null) ""
        else
            precipType
    }*/

    fun setPrecipType(precipType: String) {
        this.precipType = precipType
    }
}