package com.ingridr.catalogue.model

import androidx.room.*

@Dao
interface ProductsDao {

    @Query("SELECT * FROM product")
    fun getAll() : List<Product>

    @Query("SELECT * FROM product")
    fun getWishList() : List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWishList(vararg product:  Product)
    @Delete
    fun deleteWishList(product: Product)
}