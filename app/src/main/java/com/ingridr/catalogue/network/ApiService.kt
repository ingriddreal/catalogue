package com.ingridr.catalogue.network

import com.ingridr.catalogue.model.Products
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/bins/1g7osg")
    fun fetchProducts() : Call<Products>
}