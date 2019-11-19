package com.ingridr.catalogue.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "product")
class Product {

    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
    @SerializedName("title")
    @ColumnInfo(name = "title")
    var title: String? = null
    @SerializedName("brand")
    @Ignore
    var brand: String? = null
    @SerializedName("short_description")
    @ColumnInfo(name = "short_description")
    var shortDescription: String? = null
    @SerializedName("description")
    @ColumnInfo(name = "description")
    var description: String? = null
    @SerializedName("price")
    @ColumnInfo(name = "price")
    var price: Double? = null
    @SerializedName("image")
    @ColumnInfo(name = "image")
    var image: String? = null
    @SerializedName("colors")
    @Ignore
//    @ColumnInfo(name = "colors")
    var colors: List<Color>? = null
    @SerializedName("size")
//    @ColumnInfo(name = "size")
    @Ignore
    var size: Size? = null
    @SerializedName("quantity")
    @ColumnInfo(name = "quantity")
    var quantity: Int? = null

}
