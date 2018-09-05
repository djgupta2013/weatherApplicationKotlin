package com.wildnet.weatherapplicationkotlin.constant

class Constant {
    companion object {
        val GOOGLE_API_KEY  = "AIzaSyAFgPQeKakaRvALK8xDLfbNi6-g6-EesXA"
        val ADDRESS_TYPE_LOCALITY = "locality"
        val ADDRESS_TYPE_ADMINISTRATIVE_2 = "administrative_area_level_2"
        val TIMESTAMP_AND_API_MODEL = "currentTimeStampAndAPIKeyModel"
        val SUPPORT_MODEL = "supportedData"
        var apiUpdateTimeDifference: Long = 30 // in minutes
    }
}