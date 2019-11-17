package com.ingridr.catalogue.network

object ApiUtils {
    private const val BASE_URL = "https://api.myjson.com"

    val apiService: ApiService
    get() = RetrofitClient.getClient(BASE_URL)!!.create(ApiService::class.java)
}