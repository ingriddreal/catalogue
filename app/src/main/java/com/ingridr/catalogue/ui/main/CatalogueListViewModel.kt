package com.ingridr.catalogue.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ingridr.catalogue.model.Product
import com.ingridr.catalogue.model.Products
import com.ingridr.catalogue.network.ApiUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CatalogueListViewModel : ViewModel() {

    val products: MutableLiveData<List<Product>> = MutableLiveData()
    val wishList: MutableLiveData<MutableList<Product>> = MutableLiveData(mutableListOf())
    private val disposable = CompositeDisposable()

    fun init() {
        getProducts()
    }

    fun getProducts() {
        ApiUtils.apiService.fetchProducts().enqueue(object : Callback<Products> {
            override fun onResponse(call: Call<Products>?, response: Response<Products>?) {
                if (response?.isSuccessful() == true) {
                    products.value = response.body()?.products
                    Log.d("CatalogueListViewModel", "Products loaded from API")
                } else {
                    val status = response?.code()
                    Log.d("CatalogueListViewModel", " error %s " + status)
                }
            }

            override fun onFailure(call: Call<Products>?, t: Throwable?) {
                //TODO: show error message
            }
        })
    }

    fun updateWishList(product: Product) {
        if(wishList.value?.contains(product) == false){
            wishList.value?.add(product)
        }
    }
}
