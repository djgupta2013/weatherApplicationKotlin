package com.wildnet.weatherapplicationkotlin.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.location.places.ui.PlaceAutocomplete
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.wildnet.weatherapplicationkotlin.MainClass
import com.wildnet.weatherapplicationkotlin.R
import com.wildnet.weatherapplicationkotlin.adapter.DailyRecyclerViewAdapter
import com.wildnet.weatherapplicationkotlin.darkSkyApiModel.Daily
import com.wildnet.weatherapplicationkotlin.darkSkyApiModel.DarkSkyApiModel
import com.wildnet.weatherapplicationkotlin.geoCodingApiModel.LatLongModel
import com.wildnet.weatherapplicationkotlin.model.ModelDailyForecast
import com.wildnet.weatherapplicationkotlin.retrofit.MainUIResponse
import com.wildnet.weatherapplicationkotlin.retrofit.RetrofitApi
import com.wildnet.weatherapplicationkotlin.utils.Constantts
import com.wildnet.weatherapplicationkotlin.utils.Utility
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener, ValueEventListener, MainUIResponse, MainClass.directLoad {

    private val TAG = "FragmentActivity"
    lateinit var activity: Activity
    lateinit var context: Context
    internal var value: Boolean = false
    internal var PLACE_AUTOCOMPLETE_REQUEST_CODE: Int = 1
    internal var _localCity: String = ""
    lateinit var city: String
    internal var localCity: String = ""
    internal var model: DarkSkyApiModel? = null
    private var mAdView: AdView? = null
    private var placeLatLng: LatLng? = null
    internal lateinit var rotation: Animation
    internal var flag: Boolean = true
    lateinit var builder : AlertDialog.Builder
    lateinit var dialog : AlertDialog

    lateinit var recyclerViewLayoutManager: RecyclerView.LayoutManager
    internal lateinit var horizontalLayout: LinearLayoutManager
    internal var sevenDaysForecastsList = ArrayList<ModelDailyForecast>()
    internal var dailyData = ArrayList<ModelDailyForecast>()

    internal var database = FirebaseDatabase.getInstance()
    internal var myRef = database.reference

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        setContentView(R.layout.activity_main)
        activity = this
        context = this
        builder =  AlertDialog.Builder(context)
        builder.setCancelable(true) // if you want user to wait for some process to finish,
        builder.setView(R.layout.layout_loading_dialog)
         dialog = builder.create()
        dialog.show()
        setViewListeners()
        showAds()
        updateWeather()
        refreshData()

    }

    fun showAds() {
        val adRequest = AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                // Check the LogCat to get your test device ID
                .addTestDevice("F7F9B93A01B4DF7FB440402A57FE0A62")
                .build()
        mAdView?.loadAd(adRequest)
    }

    fun refreshData() {
        if (Utility.isInternetAvailable(context)) {
            updateData(true)
        }
        showLocalTime()
    }

    fun updateData(isUpdate: Boolean) {
        var _city: String = Utility.getPreferences(this, Constantts.keyCurrentLocation)
        _localCity = Utility.getPreferences(this, Constantts.keyIpLocation)


        if (!_localCity.equals("")) {
            localCity = _localCity
            if (_city == "") {
                _city = _localCity
            }
            callApi(_city)
            city = _city
            if(city.length>10) {
                var cityName: String = city.substring(0, 10)
                Log.e("cityName", cityName)
            }
            var city_: String = _city.toLowerCase()
            updateWeather()
        } else {
            RetrofitApi.getInstance().getIpAddress(this)
        }
    }

    fun callApi(_city: String) {
        MainClass.getInstance(this).callDarkSkyApi(this, this, _city, this.placeLatLng)
    }

    //weather update check
    private fun updateWeather() {
        val background = object : Thread() {

            override fun run() {
                try {
                    // Thread will sleep for 5 minutes
                    Thread.sleep((5 * 1000 * 60).toLong())

                    Log.e("weatherRfrshTime check", "check")
                    updateData(false)

                } catch (e: Exception) {
                }
            }
        }
        // start thread
        background.start()
    }


    //show Ip address time..
    fun showLocalTime() {
        localCity = Utility.getPreferences(this, Constantts.keyIpLocation)
        val someHandler = Handler(mainLooper)
        someHandler.postDelayed(object : Runnable {
            override fun run() {
                val df = SimpleDateFormat("EEEE, d MMM yyyy, hh:mm aa")
                val date = df.format(Calendar.getInstance().time)
                tv_currentDateTime.text = ("$localCity : $date")
                someHandler.postDelayed(this, 0)
            }
        }, 1000)
    }

    fun setViewListeners() {
        tv_about.setOnClickListener(this)
        iv_settings.setOnClickListener(this)
        et_locationSearch.setOnClickListener(this)
        tv_support.setOnClickListener(this)
        tv_terms.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_settings -> {
                if (!value) {
                    value = true
                    rv_7daysForecast.visibility = GONE
                    ll_setting_support.visibility = VISIBLE
                    ll_setting_terms.visibility = VISIBLE
                    ll_setting_about.visibility = VISIBLE
                } else {
                    value = false
                    ll_setting_support.visibility = GONE
                    ll_setting_terms.visibility = GONE
                    ll_setting_about.visibility = GONE
                    rv_7daysForecast.visibility = VISIBLE
                }
            }
            R.id.tv_about -> {
                startActivity(Intent(this, AboutActivity::class.java))
                ll_setting_support.visibility = GONE
                ll_setting_terms.visibility = GONE
                ll_setting_about.visibility = GONE
            }
            R.id.tv_terms -> {
                val intent = Intent(this, TermsActivity::class.java)
                startActivity(intent)
                ll_setting_support.visibility = GONE
                ll_setting_terms.visibility = GONE
                ll_setting_about.visibility = GONE
            }
            R.id.tv_support -> {
                startActivity(Intent(this@MainActivity, SupportActivity::class.java))
                ll_setting_support.visibility = GONE
                ll_setting_terms.visibility = GONE
                ll_setting_about.visibility = GONE
            }
            R.id.et_locationSearch -> {
                ll_setting_support.visibility = GONE
                ll_setting_terms.visibility = GONE
                ll_setting_about.visibility = GONE
                searchNewLocation()
            }
        }
        Log.d("hello", "hi.............")
    }

    //Search new Location
    private fun searchNewLocation() {
        try {
            ll_setting_support.visibility = GONE
            ll_setting_terms.visibility = GONE
            ll_setting_about.visibility = GONE
            if (flag) {
                flag = false
                val intent: Intent = PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.RESULT_ERROR)
                        .build(this)
                //Log.e("flag", "flag "+flag);
                startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE)
            }
        } catch (e: GooglePlayServicesRepairableException) {
            e.printStackTrace()
        } catch (e: GooglePlayServicesNotAvailableException) {
            e.printStackTrace()
        } finally {
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        dialog.show()
        flag = true

        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            when (resultCode) {
                RESULT_OK -> {
                    //progressBar.progress=(ProgressBar.VISIBLE)
                    // progressBar.visibility = View.VISIBLE
                    val place = PlaceAutocomplete.getPlace(this, data)
                    Log.e(TAG, "Place: " + place.name)
                    city = place.name as String
                    placeLatLng = null
                    if (place.latLng != null) {
                        placeLatLng = place.latLng
                    }
                    Utility.saveCurrentLocation(activity, city)
                    updateData(false)

                }
                PlaceAutocomplete.RESULT_ERROR -> {
                    val status = PlaceAutocomplete.getStatus(this, data)
                    Log.i(TAG, status.statusMessage)

                }
                RESULT_CANCELED -> {
                    // The user canceled the operation.
                    dialog.dismiss()
                }
            }
        }
        if (resultCode != -1) {
            if (requestCode == 1) {
                if (resultCode == RESULT_OK) {
                    //createCityUi(data.getStringExtra("result"));
                    Log.e("callback", "result")
                    refreshData()
                }
                if (resultCode == RESULT_CANCELED) {
                    //Write your code if there's no result
                }
            }
        }
    }

    override fun onIpResponse(ip: String) {
        RetrofitApi.getInstance().getLatLongNAddress(ip, this)
    }

    override fun onLatLngResponse(model: LatLongModel) {
        Utility.saveIpLocation(this, model.getCity()!!)
        updateData(false)
    }

    override fun onCancelled(p0: DatabaseError) {
    }

    override fun onDataChange(p0: DataSnapshot) {
    }

    override fun dataReceived(model: DarkSkyApiModel, tempUnit: String) {
        if (model != null) {
            Log.e("model", "dark city " + city)
            setData(model, "C")
        } else {
            Log.e("DarkSkyApiModel", "empty ")
        }
        dialog.dismiss()
    }

    @SuppressLint("SetTextI18n")
    private fun setData(model: DarkSkyApiModel, s: String) {
        this.model = model
        if (model.getCurrently() != null) {
            Log.e("model.getCurrently()", "called model.getCurrently()")
            bg_view.background = Utility.getIconDrawable(activity,
                    model.getCurrently()!!.getIcon(),
                    Constantts.iconPrefixBg,
                    Utility.convertStringToDouble(model.getCurrently()!!.getCloudCover()),
                    Utility.convertStringDecimalToInt2(model.getCurrently()!!.getWindSpeed()),
                    Utility.convertStringToDouble(model.getCurrently()!!.getPrecipIntensity()))

            iv_icon.setImageDrawable(Utility.getIconDrawable(activity,
                    model.getCurrently()!!.getIcon(),
                    Constantts.iconPrefixMain,
                    Utility.convertStringToDouble(model.getCurrently()!!.getCloudCover()),
                    Utility.convertStringDecimalToInt2(model.getCurrently()!!.getWindSpeed()),
                    Utility.convertStringToDouble(model.getCurrently()!!.getPrecipIntensity())))

            if(city.length>35){
                var locationCity : String =city.substring(0,35)+"..."
                tv_city.text=locationCity
            }else {
                tv_city.text = city
            }
            tv_temp!!.text = Utility.convertStringDecimalToInt(model.getCurrently()!!.getTemperature()) + 0x00B0.toChar() + s

            tv_status.text = model.getCurrently()!!.getSummary()
            if(city.length>12){
                var locationCity : String =city.substring(0,10)+"..."
                tv_locationName.text=locationCity
            }else {
                tv_locationName.text = city
            }
            tv_time.text = "Updated on: " + Utility.getCurrTime(Utility.timeFormat_hh_mm_a)
            tv_date.text = Utility.getCurrDate(Utility.dateFormat_dd_MMMM_yyyy)

            tv_windSpeed.text = "  " + Utility.convertStringDecimalToInt(model.getCurrently()!!.getWindSpeed()) + " km/h"

            tv_windPressure.text = "" + Utility.convertStringDecimalToInt(model.getCurrently()!!.getPressure()) + " mb"
            tv_wind_humidity.text = Utility.convertStringToPercentage(model.getCurrently()!!.getHumidity()!!)
            tv_wind_precip.text = Utility.convertStringToPercentage(model.getCurrently()!!.getPrecipProbability()!!)
            // tv_wind_dewPoint.text = ("" + Utility.convertStringDecimalToInt(model.getCurrently()!!.getDewPoint()) + (Char)0x0B0)
            tv_wind_windGust.text = "" + Utility.convertStringDecimalToInt(model.getCurrently()!!.getWindGust()) + " miles/hr"
            tv_wind_cloudCover.text = Utility.convertStringToPercentage(model.getCurrently()!!.getCloudCover()!!)
            tv_wind_visibility.text = model.getCurrently()!!.getVisibility()

            showLocationTime()
            //set Wind animation
            setWindFanAnimation(iv_windFan, Utility.convertStringDecimalToInt("" + model.getCurrently()!!.getWindSpeed()))
            bg_view.invalidate()
        }

        if (model.getDaily() != null)
            setDailyData(model.getDaily()!!)

        //progressBar.visibility = View.GONE
    }

    private fun setDailyData(daily: Daily) {
        recyclerViewLayoutManager = LinearLayoutManager(applicationContext)
        rv_7daysForecast.layoutManager = recyclerViewLayoutManager
        sevenDaysForecastsList.clear()

        for (dailyData in daily.data!!) {
            sevenDaysForecastsList.add(ModelDailyForecast(Utility.getDateTime(dailyData.getTime()!!, Utility.dateFormat_EEEE, model!!.getTimezone()!!),
                    dailyData.getIcon()!!,
                    Utility.convertStringDecimalToInt(dailyData.getTemperatureMin()) + 0x00B0.toChar() + "/" + Utility.convertStringDecimalToInt(dailyData.getTemperatureMax()) + 0x00B0.toChar(),
                    dailyData.getIcon()!!, Utility.getDate(dailyData.getTime()!!, Utility.dateFormat_dd_MMMM_yyyy, model!!.getTimezone()!!)))
        }

        //AddItemsToRecyclerViewArrayList(sevenDaysForecastsList)
        val dailyRecyclerViewAdapter = DailyRecyclerViewAdapter(sevenDaysForecastsList, context)
        horizontalLayout = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_7daysForecast.layoutManager = horizontalLayout
        rv_7daysForecast.adapter = dailyRecyclerViewAdapter
    }

    private fun AddItemsToRecyclerViewArrayList(sevenDaysForecastsList: ArrayList<ModelDailyForecast>) {
        dailyData.clear()
        for (j in sevenDaysForecastsList.indices) {
            dailyData.add(sevenDaysForecastsList[j])
        }
    }

    private fun showLocationTime() {
        val someHandler = Handler(mainLooper)
        someHandler.postDelayed(object : Runnable {
            @SuppressLint("SetTextI18n", "SimpleDateFormat")
            override fun run() {
                try {
                    val df = SimpleDateFormat("EEEE, d MMM yyyy,hh:mm aa")

                    val tz = TimeZone.getTimeZone(model!!.getTimezone())
                    df.timeZone = tz
                    val date = df.format(Date())
                    tv_locationTime.text = " time : $date"

                    someHandler.postDelayed(this, 0)
                } catch (e: Exception) {
                }
            }
        }, 1000)
    }

    private fun setWindFanAnimation(imageView: ImageView, speed: String) {
        var _speed: Int
        try {
            _speed = Integer.parseInt(speed)
        } catch (e: NumberFormatException) {
            println("Wrong number")
            _speed = 0
        }
        if (_speed > 0) {
            var duration = 10000 / _speed
            if (duration < 800)
                duration = 800

            rotation = AnimationUtils.loadAnimation(activity, R.anim.rotate)
            rotation.fillAfter = true
            rotation.duration = duration.toLong()
            imageView.startAnimation(rotation)
        } else {
            imageView.clearAnimation()
        }
    }

    var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }
}


