package com.wildnet.weatherapplicationkotlin.geoCodingApiModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LatLongModel {
    @SerializedName("ip")
    @Expose
    private var ip: String? = null
    @SerializedName("hostname")
    @Expose
    private var hostname: String? = null
    @SerializedName("type")
    @Expose
    private var type: String? = null
    @SerializedName("continent_code")
    @Expose
    private var continentCode: String? = null
    @SerializedName("continent_name")
    @Expose
    private var continentName: String? = null
    @SerializedName("country_code")
    @Expose
    private var countryCode: String? = null
    @SerializedName("country_name")
    @Expose
    private var countryName: String? = null
    @SerializedName("region_code")
    @Expose
    private var regionCode: String? = null
    @SerializedName("region_name")
    @Expose
    private var regionName: String? = null
    @SerializedName("city")
    @Expose
    private var city: String? = null
    @SerializedName("zip")
    @Expose
    private var zip: String? = null
    @SerializedName("latitude")
    @Expose
    private var latitude: Double? = null
    @SerializedName("longitude")
    @Expose
    private var longitude: Double? = null
    @SerializedName("location")
    @Expose
    private var location: Location? = null
    @SerializedName("time_zone")
    @Expose
    private var timeZone: TimeZone? = null
    @SerializedName("currency")
    @Expose
    private var currency: Currency? = null
    @SerializedName("connection")
    @Expose
    private var connection: Connection? = null
    @SerializedName("security")
    @Expose
    private var security: Security? = null

    fun getIp(): String? {
        return ip
    }

    fun setIp(ip: String) {
        this.ip = ip
    }

    fun getHostname(): String? {
        return hostname
    }

    fun setHostname(hostname: String) {
        this.hostname = hostname
    }

    fun getType(): String? {
        return type
    }

    fun setType(type: String) {
        this.type = type
    }

    fun getContinentCode(): String? {
        return continentCode
    }

    fun setContinentCode(continentCode: String) {
        this.continentCode = continentCode
    }

    fun getContinentName(): String? {
        return continentName
    }

    fun setContinentName(continentName: String) {
        this.continentName = continentName
    }

    fun getCountryCode(): String? {
        return countryCode
    }

    fun setCountryCode(countryCode: String) {
        this.countryCode = countryCode
    }

    fun getCountryName(): String? {
        return countryName
    }

    fun setCountryName(countryName: String) {
        this.countryName = countryName
    }

    fun getRegionCode(): String? {
        return regionCode
    }

    fun setRegionCode(regionCode: String) {
        this.regionCode = regionCode
    }

    fun getRegionName(): String? {
        return regionName
    }

    fun setRegionName(regionName: String) {
        this.regionName = regionName
    }

    fun getCity(): String? {
        return city
    }

    fun setCity(city: String) {
        this.city = city
    }

    fun getZip(): String? {
        return zip
    }

    fun setZip(zip: String) {
        this.zip = zip
    }

    fun getLatitude(): Double? {
        return latitude
    }

    fun setLatitude(latitude: Double?) {
        this.latitude = latitude
    }

    fun getLongitude(): Double? {
        return longitude
    }

    fun setLongitude(longitude: Double?) {
        this.longitude = longitude
    }

    fun getLocation(): Location? {
        return location
    }

    fun setLocation(location: Location) {
        this.location = location
    }

    fun getTimeZone(): TimeZone? {
        return timeZone
    }

    fun setTimeZone(timeZone: TimeZone) {
        this.timeZone = timeZone
    }

    fun getCurrency(): Currency? {
        return currency
    }

    fun setCurrency(currency: Currency) {
        this.currency = currency
    }

    fun getConnection(): Connection? {
        return connection
    }

    fun setConnection(connection: Connection) {
        this.connection = connection
    }

    fun getSecurity(): Security? {
        return security
    }

    fun setSecurity(security: Security) {
        this.security = security
    }

    inner class Connection {

        @SerializedName("asn")
        @Expose
        var asn: Int? = null
        @SerializedName("isp")
        @Expose
        var isp: String? = null

    }

    inner class Currency {

        @SerializedName("code")
        @Expose
        var code: String? = null
        @SerializedName("name")
        @Expose
        var name: String? = null
        @SerializedName("plural")
        @Expose
        var plural: String? = null
        @SerializedName("symbol")
        @Expose
        var symbol: String? = null
        @SerializedName("symbol_native")
        @Expose
        var symbolNative: String? = null

    }

    inner class Language {

        @SerializedName("code")
        @Expose
        var code: String? = null
        @SerializedName("name")
        @Expose
        var name: String? = null
        @SerializedName("native")
        @Expose
        var native: String? = null

    }

    inner class Location {

        @SerializedName("geoname_id")
        @Expose
        var geonameId: Int? = null
        @SerializedName("capital")
        @Expose
        var capital: String? = null
        @SerializedName("languages")
        @Expose
        var languages: List<Language>? = null
        @SerializedName("country_flag")
        @Expose
        var countryFlag: String? = null
        @SerializedName("country_flag_emoji")
        @Expose
        var countryFlagEmoji: String? = null
        @SerializedName("country_flag_emoji_unicode")
        @Expose
        var countryFlagEmojiUnicode: String? = null
        @SerializedName("calling_code")
        @Expose
        var callingCode: String? = null
        @SerializedName("is_eu")
        @Expose
        var isEu: Boolean? = null

    }


    inner class Security {

        @SerializedName("is_proxy")
        @Expose
        var isProxy: Boolean? = null
        @SerializedName("proxy_type")
        @Expose
        var proxyType: Any? = null
        @SerializedName("is_crawler")
        @Expose
        var isCrawler: Boolean? = null
        @SerializedName("crawler_name")
        @Expose
        var crawlerName: Any? = null
        @SerializedName("crawler_type")
        @Expose
        var crawlerType: Any? = null
        @SerializedName("is_tor")
        @Expose
        var isTor: Boolean? = null
        @SerializedName("threat_level")
        @Expose
        var threatLevel: String? = null
        @SerializedName("threat_types")
        @Expose
        var threatTypes: Any? = null

    }


    inner class TimeZone {

        @SerializedName("id")
        @Expose
        var id: String? = null
        @SerializedName("current_time")
        @Expose
        var currentTime: String? = null
        @SerializedName("gmt_offset")
        @Expose
        var gmtOffset: Int? = null
        @SerializedName("code")
        @Expose
        var code: String? = null
        @SerializedName("is_daylight_saving")
        @Expose
        var isDaylightSaving: Boolean? = null

    }
}