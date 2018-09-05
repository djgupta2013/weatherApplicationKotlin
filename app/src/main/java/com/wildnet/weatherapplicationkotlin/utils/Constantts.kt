package com.wildnet.weatherapplicationkotlin.utils

class Constantts {
    companion object {
        var locationArrayMaxSize = 500
        var weatherRefreshTime = 30.0 // in minutes
        var iconPrefixHourly = "hourly_"
        var iconPrefixWeekly = "weekly_"
        var iconPrefixMain = "main_"
        var iconPrefixBg = "bg_"
        var iconPrefixWaves = "waves_"
        var keyCurrentLocation = "keyCurrentLocation"
        var textAddLocation = "Add\nLocation"
        var textUnit = "Unit"
        var textTC = "Terms\n&\nConditions"
        var textSupport = "Support"
        var textAbout = "about"
        var ip = ""
        var keyIpLocation = "keyIpLocation"

        val GOOGLE_API_KEY = "AIzaSyAU-mUQUzkiHIY781Fg0Sb5lyxUnXJPCp4"
        val ADDRESS_TYPE_LOCALITY = "locality"
        val ADDRESS_TYPE_ADMINISTRATIVE_2 = "administrative_area_level_2"
        val TIMESTAMP_AND_API_MODEL = "currentTimeStampAndAPIKeyModel"
        val SUPPORT_MODEL = "supportedData"
        var apiUpdateTimeDifference: Long = 30 // in minutes
    }
}