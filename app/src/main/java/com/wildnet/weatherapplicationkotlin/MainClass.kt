package com.wildnet.weatherapplicationkotlin

import android.app.Activity
import android.util.Log
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.database.*
import com.wildnet.weatherapplicationkotlin.darkSkyApiModel.DarkSkyApiModel
import com.wildnet.weatherapplicationkotlin.darkSkyApiModel.TimestampAndAPIKeyModel
import com.wildnet.weatherapplicationkotlin.model.SupportModel
import com.wildnet.weatherapplicationkotlin.retrofit.RetrofitApi
import com.wildnet.weatherapplicationkotlin.utils.Constantts
import java.util.*
import kotlin.collections.HashMap

class MainClass(activity: Activity) : RetrofitApi.ResponseListener, RetrofitApi.GeocodingListener {


    override fun _onError_(e: Throwable?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var mlistenerResponse: RetrofitApi.ResponseListener? = null
    private var mlistenerGeocoding: RetrofitApi.GeocodingListener? = null
    private var darkSkyApiModel: DarkSkyApiModel? = null
     var city: String? = null
    private var latlng: String = null.toString()
    internal var currDarkSkyApiKey = "AIzaSyAFgPQeKakaRvALK8xDLfbNi6-g6-EesXA"
    internal lateinit var directLoadInterface: directLoad





    companion object {
        private var mainClass: MainClass? = null
        private var activity: Activity? =null

        fun getInstance(activity: Activity): MainClass {

            if (mainClass != null)
                return mainClass as MainClass
            else {
                mainClass = MainClass(activity)
                this.activity =activity
                return mainClass as MainClass
            }
        }
    }

    fun callDarkSkyApi(directLoad: directLoad, activity: Activity, city: String, placeLatLng: LatLng?){
        directLoadInterface=directLoad
        mlistenerResponse=this
        mlistenerGeocoding=this
        this.city=city
        if(placeLatLng==null){
            RetrofitApi.getInstance().getLatLng(mlistenerGeocoding as MainClass, city)
        }else{
            val lat : Double =placeLatLng.latitude
            val lng : Double =placeLatLng.longitude
            Log.e("lat &lng callDarkSkyApi", ""+(lat + lng).toString())
            (mlistenerGeocoding as MainClass)._getLatLng(lat, lng)
        }
    }

    private fun hitDarkSkyApi() {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.reference
        val timestampAndAPIKeyModel = TimestampAndAPIKeyModel()
        val nextDarkSkyApiKey = getNextDarkSkyApiKey()
        timestampAndAPIKeyModel.setDarkSkryApiKey(nextDarkSkyApiKey)
        myRef.child("WeatherData").child(Constantts.TIMESTAMP_AND_API_MODEL).setValue(timestampAndAPIKeyModel)
        val timestampmodel = HashMap<String, Any>()
        timestampmodel["currentTimestamp"] = ServerValue.TIMESTAMP
        myRef.child("WeatherData").child(Constantts.TIMESTAMP_AND_API_MODEL).updateChildren(timestampmodel)

        val _city = city!!.toLowerCase()

        myRef.child("WeatherData").child(Constantts.TIMESTAMP_AND_API_MODEL).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val current_time = dataSnapshot.child("currentTimestamp").value as Long
                currDarkSkyApiKey = (dataSnapshot.child("darkSkryApiKey").value as String?)!!
                //TimestampAndAPIKeyModel timestampAndAPIKeyModel = dataSnapshot.child("WeatherData").child(Constants.TIMESTAMP_AND_API_MODEL).getValue(TimestampAndAPIKeyModel.class);

                myRef.child("WeatherData").child(_city).addListenerForSingleValueEvent(object : ValueEventListener {

                    override fun onDataChange(dataSnapshot: DataSnapshot) {

                        if (dataSnapshot.hasChild(_city) && dataSnapshot.child(_city) != null && dataSnapshot.child(_city).child("timestamp") != null) {
                            val update_time = dataSnapshot.child(_city).child("timestamp").value as Long
                            val time_diff = current_time - update_time

                            if (time_diff > Constantts.apiUpdateTimeDifference * 60 * 1000) {
                                RetrofitApi.getInstance().callDarkSkyApi(mlistenerResponse, latlng, currDarkSkyApiKey)
                                Log.d("status", "updated")
                            } else {
                                Log.d("status", "no update required")
                            }
                        } else {
                            RetrofitApi.getInstance().callDarkSkyApi(mlistenerResponse, latlng, currDarkSkyApiKey)
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        Log.d("status", "update check failed")
                    }
                })

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d("status", "update check failed")
            }
        })
    }

    private fun getNextDarkSkyApiKey(): String {

        Log.e("getNextDarkSkyApiKey","getNextDarkSkyApiKey()")
        val arr = activity?.resources?.getStringArray(R.array.dark_sky_api_key)
        Log.e("getNextDarkSkyApiKey","getNextDarkSkyApiKey()"+arr)
        val apiList = ArrayList(Arrays.asList(*arr))

        val index = apiList.indexOf(currDarkSkyApiKey)
        var updateIndex = 0
        if (index < apiList.size - 1)
            updateIndex = index + 1

        return apiList[updateIndex]
    }

    override fun _getCity(city: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun _getLatLng(latitude: Double, longitude: Double) {
        latlng = latitude.toString() + "," + longitude.toString()
        mlistenerResponse = this
        hitDarkSkyApi()
    }

    override fun _onNext(obj: Any) {
        if (obj is DarkSkyApiModel) {
            Log.d("_onNext", "_onNext method call")
            darkSkyApiModel = obj
            Log.d("DarkSkyApiModel", darkSkyApiModel.toString())

            val database = FirebaseDatabase.getInstance()
            val myRef = database.reference
            val nextDarkSkyApiKey = getNextDarkSkyApiKey()
            val _city = city!!.toLowerCase()

            myRef.child("WeatherData").child(_city).setValue(darkSkyApiModel)
            var timestamp= HashMap<String,Any>()
            timestamp.put("timestamp", ServerValue.TIMESTAMP)
            myRef.child("WeatherData").child(_city).updateChildren(timestamp)

            val timestampAndAPIKeyModel =TimestampAndAPIKeyModel()
            timestampAndAPIKeyModel.setDarkSkryApiKey(nextDarkSkyApiKey)
            myRef.child("WeatherData").child(Constantts.TIMESTAMP_AND_API_MODEL).setValue(timestampAndAPIKeyModel)
            var timestampmodel=HashMap<String, Any>()
            timestampmodel.put("currentTimestamp", ServerValue.TIMESTAMP)
            myRef.child("WeatherData").child(Constantts.TIMESTAMP_AND_API_MODEL).updateChildren(timestampmodel)

            directLoadInterface.dataReceived(darkSkyApiModel!!, "C")
        }
    }

    override fun _onError(e: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    interface directLoad {
        fun dataReceived(model: DarkSkyApiModel, tempUnit: String)
    }

    fun supportData(supportModel: SupportModel) {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.reference
        val nextDarkSkyApiKey = getNextDarkSkyApiKey()

        val email_ = supportModel.getEmail().replace(".", "")
        //String email_=supportModel.getEmail().replaceAll("[^a-zA-Z0-9@]", "");

        myRef.child("WeatherData").child(Constantts.SUPPORT_MODEL).child(email_).setValue(supportModel)

        val timestampAndAPIKeyModel = TimestampAndAPIKeyModel()
        timestampAndAPIKeyModel.setDarkSkryApiKey(nextDarkSkyApiKey)
        myRef.child("WeatherData").child(Constantts.TIMESTAMP_AND_API_MODEL).setValue(timestampAndAPIKeyModel)
        val timestampmodel = java.util.HashMap<String, Any>()
        timestampmodel["currentTimestamp"] = ServerValue.TIMESTAMP
        myRef.child("WeatherData").child(Constantts.TIMESTAMP_AND_API_MODEL).updateChildren(timestampmodel)

    }
}


