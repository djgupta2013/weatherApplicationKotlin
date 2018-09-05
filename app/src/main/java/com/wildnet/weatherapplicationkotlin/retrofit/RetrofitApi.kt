package com.wildnet.weatherapplicationkotlin.retrofit

import android.util.Log
import com.wildnet.weatherapplicationkotlin.darkSkyApiModel.DarkSkyApiModel
import com.wildnet.weatherapplicationkotlin.geoCodingApiModel.GeoCodingApiModel
import com.wildnet.weatherapplicationkotlin.geoCodingApiModel.IpResponse
import com.wildnet.weatherapplicationkotlin.geoCodingApiModel.LatLongModel
import com.wildnet.weatherapplicationkotlin.utils.Constantts
import com.wildnet.weatherapplicationkotlin.utils.Constantts.Companion.ip
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

class RetrofitApi {

    private var mlistenerResponse: ResponseListener? = null
    private var mlistenerGeocoding: GeocodingListener? = null

    companion object {
        private var retrofitApi: RetrofitApi? = null
        val DARK_SKY_BASE_URL = "https://api.darksky.net/"
        val GEOCODING_BASE_URL = "https://maps.googleapis.com/"
        val REVERSE_GEOCODING_BASE_URL = "https://maps.google.com/"
        val IP_ADDRESS_URL = "https://api.ipify.org/"
        val IP_ACCESS_KEY = "f9106e69c4295a8bc8c265302334d6a0"
        val IP_ACCESS_URL = "http://api.ipstack.com/"

        fun getInstance(): RetrofitApi {

            if (retrofitApi != null)
                return retrofitApi as RetrofitApi
            else {
                retrofitApi = RetrofitApi()
                return retrofitApi as RetrofitApi
            }
        }
    }

    interface ResponseListener {

        fun _onNext(obj: Any)

        fun _onError(e: Throwable)

    }

    interface GeocodingListener {

        fun _getCity(city: String)

        fun _getLatLng(latitude: Double, longitude: Double)

        fun _onError_(e: Throwable?)
    }

    // --------------------- Retrofit Api Methods ----------------------------------------------------------

    fun getIpAddress(respose : MainUIResponse) {

        RetrofitClient.getClient(IP_ADDRESS_URL).getIP("json")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<IpResponse>() {
                    override fun onCompleted() {
                        Log.e("dj ip response", "getIpAddress() complete")
                    }

                    override fun onError(e: Throwable) {
                        val i = 0
                    }

                    override fun onNext(ipResponse: IpResponse) {
                        ip = ipResponse.getIp()!!
                        Constantts.ip = ip
                        respose.onIpResponse(ip)
                    }
                })
    }



    fun getLatLng(_mlistenerGeocoding: GeocodingListener, city: String){
        mlistenerGeocoding = _mlistenerGeocoding
        RetrofitClient.getClient(REVERSE_GEOCODING_BASE_URL).getLatLng(city)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<GeoCodingApiModel>() {
                    override fun onCompleted() {
                        Log.e("LatLng response", "getLatLng(city) complete")
                    }

                    override fun onError(e: Throwable) {
                        Log.e("LatLng response error", "" + e.stackTrace)
                        for (s in e.stackTrace) {
                            Log.e("StackTraceElement ", "getLatLng(city) error " + s.toString())
                        }
                        //mlistenerGeocoding._getLatLng(11.3530022, 76.7959095);
                        //                        mlistenerGeocoding._onError(e);
                    }

                    override fun onNext(geoCodingApiModel: GeoCodingApiModel) {
                        Log.e("response", "dhananjay$geoCodingApiModel")
                        Log.e("response", "dhananjay google api status " + geoCodingApiModel.getStatus())

                        if (geoCodingApiModel.getStatus().equals("OK") && geoCodingApiModel.getResults()!!.size > 0) {

                            val lat = java.lang.Double.parseDouble(geoCodingApiModel.getResults()!![0].getGeometry()!!.getLocation()!!.getLat())
                            val lng = java.lang.Double.parseDouble(geoCodingApiModel.getResults()!![0].getGeometry()?.getLocation()!!.getLng())
                            Log.e("lat & lng", (+lat).toString() + " " + lng)
                            mlistenerGeocoding!!._getLatLng(lat, lng)

                        } else {
                            Log.e("StackTraceElement ", "getLatLng(city) error ")
                            mlistenerGeocoding!!._onError_(null)
                        }
                    }
                })
    }

    fun getLatLongNAddress(ip: String, listener: MainUIResponse){
        RetrofitClient.getClient(IP_ACCESS_URL).getLatLongNAddress(ip, IP_ACCESS_KEY)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<LatLongModel>() {
                    override fun onCompleted() {
                        Log.e("response", "getIpAddress() complete")
                    }

                    override fun onError(e: Throwable) {
                        val i = 0
                    }

                    override fun onNext(lat_long_model: LatLongModel) {
                        val a = 0
                        //Utility.ad
                        lat_long_model.getCity()
                        Log.e("response ip", "getIpAddress() complete " + lat_long_model.getCity())
                        listener.onLatLngResponse(lat_long_model)

                    }

                })
    }

    fun callDarkSkyApi(mlistenerResponse: RetrofitApi.ResponseListener?, latlng: String, currDarkSkyApiKey: String) {
        this.mlistenerResponse = mlistenerResponse
        RetrofitClient.getClient(DARK_SKY_BASE_URL).callDarkSkyApi(currDarkSkyApiKey,latlng,"si")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<DarkSkyApiModel>(){
                    override fun onNext(darkSkyApiModel : DarkSkyApiModel?) {
                        Log.e("dj response", "" + darkSkyApiModel)


                        if (darkSkyApiModel != null) {
                            mlistenerResponse?._onNext(darkSkyApiModel)
                        }

                    }

                    override fun onCompleted() {
                        Log.e("callDarkSkyApi response", "okk")
                         }

                    override fun onError(e: Throwable?) {
                        Log.e("callDarkSkyApi response", "Error")
                          }

                })

    }


}