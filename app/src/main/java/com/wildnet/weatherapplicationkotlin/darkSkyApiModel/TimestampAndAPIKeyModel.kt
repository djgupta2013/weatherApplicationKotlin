package com.wildnet.weatherapplicationkotlin.darkSkyApiModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TimestampAndAPIKeyModel {

    @SerializedName("currentTimestamp")
    @Expose
    private var currentTimestamp: Long? = null
    @SerializedName("darkSkyApiKey")
    @Expose
    private var darkSkyApiKey: String? = null

    fun getCurrentTimestamp(): Long? {
        return currentTimestamp
    }

    fun setCurrentTimestamp(currentTimestamp: Long?) {
        this.currentTimestamp = currentTimestamp
    }

    fun getDarkSkryApiKey(): String? {
        return darkSkyApiKey
    }

    fun setDarkSkryApiKey(darkSkryApiKey: String) {
        this.darkSkyApiKey = darkSkryApiKey
    }
}