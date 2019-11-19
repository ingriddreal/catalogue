package com.ingridr.catalogue.ui.main

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ingridr.catalogue.CatalogueApplication
import com.ingridr.catalogue.model.Color
import com.ingridr.catalogue.model.Product
import com.ingridr.catalogue.network.AppDatabase

class CatalogueDetailsViewModel : ViewModel(), LifecycleObserver {

    val product = MutableLiveData<Product>()
    val colors = MutableLiveData<List<Color>>()
    var dataBase: AppDatabase? = null

    var description = MutableLiveData<String>()

    fun updateProductDetail(product: Product) {
        this.product.value = product
        description.value = product.description
        colors.value = product.colors
    }

    fun addToWishList() {
        dataBase?.let {
            if (product.value != null)
                it.productDao().insertWishList(product.value!!)
        }
    }

    fun removeFromWishList() {
        dataBase?.let {
            if (product.value != null)
                it.productDao().deleteWishList(product.value!!)
        }
    }

}