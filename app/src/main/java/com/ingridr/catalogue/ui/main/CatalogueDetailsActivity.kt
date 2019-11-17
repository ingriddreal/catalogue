package com.ingridr.catalogue.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ingridr.catalogue.R
import com.ingridr.catalogue.model.Product
import com.google.gson.Gson
import com.ingridr.catalogue.databinding.CatalogueItemDetailBinding
import kotlinx.android.synthetic.main.catalogue_item_detail.*
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import com.ingridr.catalogue.model.Color

class CatalogueDetailsActivity : AppCompatActivity() {

    companion object {
        const val SELECTED_PRODUCT = "selectedProduct"

        fun createArguments(product: Product) : Bundle {
            val gson = Gson()
            val bundle = Bundle()
            bundle.putString(SELECTED_PRODUCT, gson.toJson(product))
            return bundle
        }
    }
    private lateinit var viewModel: CatalogueDetailsViewModel
    private lateinit var binding: CatalogueItemDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val gson = Gson()
        binding = DataBindingUtil.setContentView(this, R.layout.catalogue_item_detail)
        viewModel = ViewModelProviders.of(this).get(CatalogueDetailsViewModel::class.java)
        setContentView(R.layout.catalogue_item_detail)

        binding.setVariable(BR.model,viewModel)
        binding.lifecycleOwner = this

        val productStr = intent.extras?.get(SELECTED_PRODUCT)
        productStr?.let {
            val product = gson.fromJson<Product>(it.toString(), Product::class.java)
            if (product != null) {
                viewModel.updateProductDetail(product)
            }
        }
        val toolBar = findViewById<Toolbar>(R.id.customToolBar)
        viewModel.product.observe(this, Observer {
            initToolbar(toolBar, it.title)
            updateProduct(it)
        })
        super.onCreate(savedInstanceState)
    }

    private fun updateProduct(product: Product) {
        Glide.with(applicationContext)
            .load(product.image).apply(RequestOptions().circleCrop())
            .into(itemImage)
        itemPrice.text = product.price.toString()

//        productDescription.text = product.description
        product.colors?.let {
            it.forEachIndexed { index, color ->
                if(index == 0) {
                    firstColour?.visibility = View.VISIBLE
                    firstColour.setBackgroundColor(android.graphics.Color.parseColor(color.code))
                }
                else if (index == 1){
                    secondColour?.visibility = View.VISIBLE
                    secondColour.setBackgroundColor(android.graphics.Color.parseColor(color.code))
                }
                else {
                    thirdColour?.visibility = View.VISIBLE
                    thirdColour.setBackgroundColor(android.graphics.Color.parseColor(color.code))
                }
            }
        }
    }

    private fun initToolbar(toolbar: Toolbar, title: String?) {

        toolbar.title = title
        setSupportActionBar(toolbar)

        val actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        actionbar.setHomeAsUpIndicator(android.R.drawable.arrow_up_float)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.backButton -> {
            this.finish()
            true
        }
//        R.id.home -> {
//            NavUtils.navigateUpFromSameTask(this)
//            //this.finish()
//            true
//        }


        else -> {
            super.onOptionsItemSelected(item)
        }
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
////        viewModel.init()
////        viewModel.products.observe(this, androidx.lifecycle.Observer {
////            listProducts.adapter = ProductsAdapter(it,this)
////        })
//        // TODO: Use the ViewModel
//    }
}