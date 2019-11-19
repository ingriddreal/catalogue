package com.ingridr.catalogue.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.ingridr.catalogue.BR
import com.ingridr.catalogue.CatalogueApplication
import com.ingridr.catalogue.CatalogueItemDetailBinding
import com.ingridr.catalogue.R
import com.ingridr.catalogue.model.Product
import com.ingridr.catalogue.network.AppDatabase

class CatalogueDetailFragment(private val product: Product) : Fragment() {
    companion object {
        fun newInstance(product: Product) = CatalogueDetailFragment(product)
    }

    private lateinit var viewModel: CatalogueDetailsViewModel
    private lateinit var binding: CatalogueItemDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProviders.of(this).get(CatalogueDetailsViewModel::class.java)

        activity?.let {
            viewModel.dataBase = AppDatabase.getAppDatabase(it.applicationContext)
        }

        binding = DataBindingUtil.inflate(inflater, R.layout.catalogue_item_detail, container, false)
        binding.setVariable(BR.model, viewModel)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.updateProductDetail(product)

        // TODO: Use the ViewModel
    }
}