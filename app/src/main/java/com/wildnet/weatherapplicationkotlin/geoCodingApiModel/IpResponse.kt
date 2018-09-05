package com.wildnet.weatherapplicationkotlin.geoCodingApiModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class IpResponse {
    private var ipResponse: IpResponse? = null

    @SerializedName("ip")
    @Expose
    private var ip: String? = null

    fun getIp(): String? {
        return ip
    }

    fun setIp(ip: String) {
        this.ip = ip
    }

    fun getInstance(): IpResponse {

        if (ipResponse != null)
            return ipResponse as IpResponse
        else {
            ipResponse = IpResponse()
            return ipResponse as IpResponse
        }
    }
}