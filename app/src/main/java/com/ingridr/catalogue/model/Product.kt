package com.ingridr.catalogue.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Product {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("brand")
    @Expose
    var brand: String? = null
    @SerializedName("short_description")
    @Expose
    var shortDescription: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("price")
    @Expose
    var price: Double? = null
    @SerializedName("image")
    @Expose
    var image: String? = null
    @SerializedName("colors")
    @Expose
    var colors: List<Color>? = null
    @SerializedName("size")
    @Expose
    var size: Size? = null
    @SerializedName("quantity")
    @Expose
    var quantity: Int? = null

}
