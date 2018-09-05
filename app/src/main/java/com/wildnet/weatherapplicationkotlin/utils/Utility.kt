package com.wildnet.weatherapplicationkotlin.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.util.Log
import java.lang.Long
import java.text.SimpleDateFormat
import java.util.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class Utility {
    companion object {
        val dateFormat_dd_MMMM_yyyy = "dd MMMM, yyyy"
        val timeFormat_hh_mm_a = "hh:mm a"
        val timeFormat_h_a = "ha"
        val dateFormat_EEEE = "EEEE"

        fun getCurrTime(timeFormat: String): String {
            val sdf = SimpleDateFormat(timeFormat, Locale.getDefault())
            var str = sdf.format(Date())
            str = str.replace("AM", "am").replace("PM", "pm")
            return str
        }

        fun getCurrDate(dateFormat: String): String {

            val sdf = SimpleDateFormat(dateFormat, Locale.ENGLISH)

            return sdf.format(Date())
        }

        fun getDateTime(timestamp: String, format: String, timeZone: String): String {

            val sdf = SimpleDateFormat(format, Locale.getDefault())
            val tz = TimeZone.getTimeZone(timeZone)
            sdf.timeZone = tz

            var str = sdf.format(Date(java.lang.Long.parseLong(timestamp) * 1000))
            str = str.replace("AM", " am").replace("PM", " pm")
            return str
        }

        fun getDate(timeStamp: String, format: String, timeZone: String): String {
            val sdf = SimpleDateFormat(format, Locale.getDefault())
            val timeZone = TimeZone.getTimeZone(timeZone)
            sdf.timeZone = timeZone
            val str = sdf.format(Date(Long.parseLong(timeStamp) * 1000))
            return str
        }

        fun getDateTime(timeStamp: String, format: String): String {
            val sdf = SimpleDateFormat(format, Locale.getDefault())
            sdf.timeZone = TimeZone.getDefault()
            var str = sdf.format(Date(Long.parseLong(timeStamp) * 1000))
            str = str.replace("AM", "am").replace("PM", "pm")
            return str
        }

        fun convertStringDecimalToInt(str: String?): String {
            if (str == null)
                return ""
            if (str.length == 0)
                return ""
            val d = java.lang.Double.parseDouble(str)
            val i = d.toInt()
            return "" + i
        }

        fun convertStringDecimalToInt2(str: String): Int {
            if (str == null)
                return 0
            if (str.length == 0)
                return 0
            val double = java.lang.Double.parseDouble(str)
            var int: Int = double.toInt()
            return int
        }


        fun convertStringToDouble(str: String?): Double {
            if (str == null)
                return 0.0
            if (str.length == 0)
                return 0.0
            try {
                return java.lang.Double.parseDouble(str)
            } catch (e: Exception) {
                return 0.0
            }
        }

        fun getIconDrawable(context: Activity, icon: String, prefix: String, cloudCover: Double, windSpeed: Int,
                            precipIntensity: Double): Drawable {
            var uri: String = "@drawable/" + prefix;
            when (icon) {

                "clear-day=" ->
                    uri += "sunny"

                "clear-night" ->
                    uri += "moon_clear"

                "rain" ->
                    if (precipIntensity < 3)
                        uri += "drizzling"
                    else
                        uri += "rain_heavy"

                "snow" ->
                    if (precipIntensity < 3)
                        uri += "snow"
                    else
                        uri += "snow_heavy"

                "sleet" ->
                    uri += "dustandhaze";

                "wind" ->
                    if (windSpeed < 10)
                        uri += "wind"
                    else
                        uri += "waves_wind_heavy"

                "fog" ->
                    uri += "fog"

                "cloudy" ->
                    if (cloudCover < 0.75)
                        uri += "cloudy"
                    else
                        uri += "cloudy_mostly"

                "partly-cloudy-day" ->
                    uri += "cloudy_partly"

                "partly-cloudy-night" ->
                    uri += "moon_cloudy"

                "hail" ->
                    uri += "dustandhaze"

                "thunderstorm" ->
                    uri += "thunderstorm"

                "tornado" ->
                    uri += "dustandhaze"
            }
            val imageResource = context.resources.getIdentifier(uri, null, context.packageName)

            return context.resources.getDrawable(imageResource)
        }

        fun convertStringToPercentage(str: String): String {
            var value: Float = java.lang.Float.parseFloat(str)
            return "" + (value * 100).toInt() + "%"
        }

        // Converts to celcius
        fun convertFahrenheitToCelcius(fahrenheit: Float): Float {
            return ((fahrenheit - 32) * 5 / 9)
        }

        // Converts to fahrenheit
        fun convertCelciusToFahrenheit(celsius: Float): Float {
            return ((celsius * 9) / 9) + 32
        }

        fun addPreferencesLocations(context: Context, locations: java.util.ArrayList<String>) {
            val editor = context.getSharedPreferences("Preferences_", Context.MODE_PRIVATE).edit()
            val set = HashSet<String>()
            set.addAll(locations)
            editor.putStringSet("locations", set)
            editor.apply()
            editor.commit()
            Log.d("addPreferencesLocations", "" + set)
        }

        fun getPreferencesLocations(context: Context): java.util.ArrayList<String> {
            var set: Set<String>? = null
            val locations = java.util.ArrayList<String>()
            val preferences: SharedPreferences = context.getSharedPreferences("Preferences_", Context.MODE_PRIVATE)

            set = preferences.getStringSet("locations", set)
            if (set != null)
                locations.addAll(set)
            Log.d("getPreferencesLocations", "" + set)
            return locations
        }

        fun addPreferences(context: Context?, key: String, value: String) {
            if (context != null) {
                val editor = context.getSharedPreferences("Preferences_", Context.MODE_PRIVATE).edit()
                editor.putString(key, value)
                editor.commit()
            }
        }

        fun getPreferences(context: Context, key: String): String {
            val prefs = context.getSharedPreferences("Preferences_", Context.MODE_PRIVATE)
            val text = prefs.getString(key, "") ?: return ""
            return if (text == "null")
                ""
            else text
        }

        fun getBgIconDrawable(context: Activity, icon: String, prefix: String): Drawable {
            var uri = "@drawable/$prefix"
            when (icon) {

                "clear-day" -> uri += "sunny"

                "clear-night" -> uri += "moon_clear"

                "rain" -> uri += "drizzling"

                "snow" -> uri += "snow"

                "sleet" -> uri += "dustandhaze"

                "wind" -> uri += "wind"

                "fog" -> uri += "fog"

                "cloudy" -> uri += "cloudy"

                "partly-cloudy-day" -> uri += "cloudy_partly"

                "partly-cloudy-night" -> uri += "moon_cloudy"

                "hail" -> uri += "dustandhaze"

                "thunderstorm" -> uri += "thunderstorm"

                "tornado" -> uri += "dustandhaze"
            }

            val imageResource = context.resources.getIdentifier(uri, null, context.packageName)

            return context.resources.getDrawable(imageResource)
        }

        fun saveCurrentLocation(activity: Activity, location: String) {
            addPreferences(activity, Constantts.keyCurrentLocation, location)

            val listLocations = ArrayList<String>()
            listLocations.clear()
            listLocations.addAll(getPreferencesLocations(activity))

            if (!listLocations.contains(location)) {
                listLocations.add(location)
            }

            addPreferencesLocations(activity, listLocations)
        }

        fun saveIpLocation(activity: Activity, location: String) {
            addPreferences(activity, Constantts.keyIpLocation, location)

            val listLocations = ArrayList<String>()
            listLocations.clear()
            listLocations.addAll(getPreferencesLocations(activity))

            if (!listLocations.contains(location)) {
                listLocations.add(location)
            }

            addPreferencesLocations(activity, listLocations)
        }

        fun isInternetAvailable(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val activeNetwork = cm.activeNetworkInfo

            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }
    }
}