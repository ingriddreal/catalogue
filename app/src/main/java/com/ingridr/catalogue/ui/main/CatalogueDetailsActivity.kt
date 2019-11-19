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
import kotlinx.android.synthetic.main.catalogue_item_detail.*
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import com.ingridr.catalogue.CatalogueItemDetailBinding
import com.ingridr.catalogue.model.Color

class CatalogueDetailsActivity : AppCompatActivity() {

    companion object {
        const val SELECTED_PRODUCT = "selectedProduct"

        fun createArguments(product: Product): Bundle {
            val gson = Gson()
            val bundle = Bundle()
            bundle.putString(SELECTED_PRODUCT, gson.toJson(product))
            return bundle
        }
    }

    private lateinit var viewModel: CatalogueDetailsViewModel
    private lateinit var binding: CatalogueItemDetailBinding
    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gson = Gson()
        binding = DataBindingUtil.setContentView(this, R.layout.catalogue_item_detail)
        viewModel = ViewModelProviders.of(this).get(CatalogueDetailsViewModel::class.java)
        setContentView(R.layout.detail_activity)
        val toolBar = findViewById<Toolbar>(R.id.my_toolbar)

        viewModel.uiEvent.observe(this, Observer {
            when(it){
                UiEvents.FINISH ->
                    this.finish()
            }
        })
        val productStr = intent.extras?.get(SELECTED_PRODUCT)
        productStr?.let {
            product = gson.fromJson<Product>(it.toString(), Product::class.java)
            if (product != null) {
                initToolbar(toolBar, product.title)
            }
        }
        startCatalogueDetails()

    }

    private fun startCatalogueDetails() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.detailsContainer, CatalogueDetailFragment.newInstance(product))
        transaction.addToBackStack(null)
        transaction.commit()
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
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

}