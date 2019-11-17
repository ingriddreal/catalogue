package com.ingridr.catalogue.ui.main

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ingridr.catalogue.model.Product

class CatalogueDetailsViewModel : ViewModel(), LifecycleObserver {

    val product = MutableLiveData<Product>()

    var description = MutableLiveData<String>()
    var test = "Hola"

    fun updateProductDetail(product: Product) {
        this.product.value = product
        description.value = product.description
        test = "vaca"
    }

}