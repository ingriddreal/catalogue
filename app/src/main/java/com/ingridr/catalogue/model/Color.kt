package com.ingridr.catalogue.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Color {

    @SerializedName("code")
    @Expose
    var code: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null

}