package com.ingridr.catalogue.ui.main

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ingridr.catalogue.CatalogueApplication
import com.ingridr.catalogue.model.Color
import com.ingridr.catalogue.model.Product
import com.ingridr.catalogue.network.AppDatabase

enum class UiEvents{
    LOADING,
    ERROR,
    FINISH
}
class CatalogueDetailsViewModel : ViewModel(), LifecycleObserver {

    val product = MutableLiveData<Product>()
    val colors = MutableLiveData<List<Color>>()
    var dataBase: AppDatabase? = null

    val uiEvent = MutableLiveData<Pair<UiEvents, String>>()
    var description = MutableLiveData<String>()
    var inWishList = MutableLiveData<Boolean>()

    fun updateProductDetail(product: Product) {
        this.product.value = product
        description.value = product.description
        colors.value = product.colors
        val products  = dataBase?.productDao()?.getAll()
        val pr = products?.find { it.id == product.id }
        inWishList.value = (pr != null)

    }

    fun addToWishList() {
        dataBase?.let {
            if (product.value != null) {
                it.productDao().insertWishList(product.value!!)
                uiEvent.value = Pair(UiEvents.FINISH, "")
            }
        }
    }

    fun removeFromWishList() {
        dataBase?.let {
            if (product.value != null) {
                it.productDao().deleteWishList(product.value!!)
                uiEvent.value = Pair(UiEvents.FINISH, "")
            }
        }
    }

}