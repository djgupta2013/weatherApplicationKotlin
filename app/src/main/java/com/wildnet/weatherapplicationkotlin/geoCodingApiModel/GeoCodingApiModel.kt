package com.wildnet.weatherapplicationkotlin.geoCodingApiModel

import java.util.ArrayList

class GeoCodingApiModel {
    private var results: ArrayList<Results>? = null

    private var status: String? = null

    fun getResults(): ArrayList<Results>? {
        return results
    }

    fun setResults(results: ArrayList<Results>) {
        this.results = results
    }

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String) {
        this.status = status
    }
}