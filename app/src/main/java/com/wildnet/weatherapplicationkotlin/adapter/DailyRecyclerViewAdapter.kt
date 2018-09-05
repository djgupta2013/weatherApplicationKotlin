package com.wildnet.weatherapplicationkotlin.adapter

import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wildnet.weatherapplicationkotlin.R
import com.wildnet.weatherapplicationkotlin.model.ModelDailyForecast
import com.wildnet.weatherapplicationkotlin.utils.Constantts
import com.wildnet.weatherapplicationkotlin.utils.Utility
import kotlinx.android.synthetic.main.daily_recycler_view_adapter.view.*


class DailyRecyclerViewAdapter(modelDailyForecastList : List<ModelDailyForecast>,context : Context) :
        RecyclerView.Adapter<DailyRecyclerViewAdapter.MyView>() {

    private var modelDailyForecastList: List<ModelDailyForecast>? = null
      var context: Activity

    init {
        this.modelDailyForecastList=modelDailyForecastList
        this.context= context as Activity
    }
    inner class MyView(view: View) : RecyclerView.ViewHolder(view) {

        init {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val itemView :View = LayoutInflater.from(parent.context)
                .inflate(R.layout.daily_recycler_view_adapter, parent, false)
        return MyView(itemView)
     }

    override fun getItemCount(): Int {
        return modelDailyForecastList!!.size
         }

    override fun onBindViewHolder(holder: MyView, position: Int) {
        Log.e("icon", "icon " + modelDailyForecastList!![position].getIcon())
        holder.itemView.iv_dailyIcon.setImageDrawable(Utility.getBgIconDrawable(context, modelDailyForecastList!![position].getIcon()!!,Constantts.iconPrefixBg))
        holder.itemView.iv_status.setImageDrawable(Utility.getBgIconDrawable(context, modelDailyForecastList!![position].getIcon()!!, Constantts.iconPrefixWeekly));
        holder.itemView.tv_date.text= modelDailyForecastList!![position].getDate()
        holder.itemView.tv_day.text=modelDailyForecastList!![position].getDay()
        holder.itemView.tv_temperature.text=modelDailyForecastList!![position].getTemp()
        }
}