package com.wildnet.weatherapplicationkotlin.darkSkyApiModel

class DarkSkyApiModel {
    private var timezone: String? = null

    private var flags: Flags? = null

    private var currently: Currently? = null

    private var longitude: String? = null

    private var latitude: String? = null

    private var hourly: Hourly? = null

    private var offset: String? = null

    private var timestamp: Long = 0

    private var daily: Daily? = null

    fun getTimezone(): String? {
        return timezone
    }

    fun setTimezone(timezone: String) {
        this.timezone = timezone
    }

    fun getFlags(): Flags? {
        return flags
    }

    fun setFlags(flags: Flags) {
        this.flags = flags
    }

    fun getCurrently(): Currently? {
        return currently
    }

    fun setCurrently(currently: Currently) {
        this.currently = currently
    }

    fun getLongitude(): String? {
        return longitude
    }

    fun setLongitude(longitude: String) {
        this.longitude = longitude
    }

    fun getLatitude(): String? {
        return latitude
    }

    fun setLatitude(latitude: String) {
        this.latitude = latitude
    }

    fun getHourly(): Hourly? {
        return hourly
    }

    fun setHourly(hourly: Hourly) {
        this.hourly = hourly
    }

    fun getOffset(): String? {
        return offset
    }

    fun setOffset(offset: String) {
        this.offset = offset
    }

    fun getDaily(): Daily? {
        return daily
    }

    fun setDaily(daily: Daily) {
        this.daily = daily
    }

    fun getTimestamp(): Long {
        return timestamp
    }

    fun setTimestamp(timestamp: Long) {
        this.timestamp = timestamp
    }
}