package com.ingridr.catalogue.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Size {
    @SerializedName("H")
    @Expose
    var h: String? = null
    @SerializedName("W")
    @Expose
    var w: String? = null
    @SerializedName("D")
    @Expose
    var d: String? = null

}
