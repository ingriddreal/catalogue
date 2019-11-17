package com.ingridr.catalogue.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.Gson
import com.ingridr.catalogue.model.Products
import java.text.DateFormat


object RetrofitClient {
    private var retrofit: Retrofit? = null

//    var gson = GsonBuilder()
//        .registerTypeAdapter(Products::class.java, IdTypeAdapter())
//        .enableComplexMapKeySerialization()
//        .serializeNulls()
//        .setDateFormat(DateFormat.LONG)
//        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
//        .setPrettyPrinting()
//        .setVersion(1.0)
//        .create()
    fun getClient(baseUrl: String): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}