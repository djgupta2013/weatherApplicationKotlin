package com.wildnet.weatherapplicationkotlin.model

class SupportModel(name: String, email: String, message: String) {
    private val name: String
    private val email: String
    private val message: String

    init {
        this.name = name
        this.email = email
        this.message = message
    }

    fun getName(): String {
        return name
    }

    fun getEmail(): String {
        return email
    }

    fun getMessage(): String {
        return message
    }

}
