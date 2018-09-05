package com.wildnet.weatherapplicationkotlin.retrofit

import com.wildnet.weatherapplicationkotlin.darkSkyApiModel.DarkSkyApiModel
import com.wildnet.weatherapplicationkotlin.geoCodingApiModel.GeoCodingApiModel
import com.wildnet.weatherapplicationkotlin.geoCodingApiModel.IpResponse
import com.wildnet.weatherapplicationkotlin.geoCodingApiModel.LatLongModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

interface AppRequestService {

    @GET("forecast/{key}/{value}")
    abstract fun callDarkSkyApi(@Path("key") darkSkyApiKey: String, @Path("value") str: String, @Query("units") unit: String): Observable<DarkSkyApiModel>

    @GET(".")
    abstract fun getIP(@Query("format") format: String): Observable<IpResponse>

    @GET("maps/api/geocode/json")
    abstract fun getLatLng(@Query("address") city: String): Observable<GeoCodingApiModel>

    @GET("/{ip}")
    abstract fun getLatLongNAddress(@Path("ip") ip: String, @Query("access_key") key: String): Observable<LatLongModel>
}