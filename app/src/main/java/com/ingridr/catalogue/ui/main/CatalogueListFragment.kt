package com.ingridr.catalogue.ui.main

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.ingridr.catalogue.BR
import com.ingridr.catalogue.R
import com.ingridr.catalogue.databinding.MainFragmentBinding
import com.ingridr.catalogue.model.Product
import com.ingridr.catalogue.network.AppDatabase
import kotlinx.android.synthetic.main.main_fragment.*


class CatalogueListFragment : Fragment(), ProductsAdapter.OnItemClickListener,
    WishProductsAdapter.OnItemClickListener {

    companion object {
        fun newInstance() = CatalogueListFragment()
    }

    private lateinit var viewModel: CatalogueListViewModel
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProviders.of(this).get(CatalogueListViewModel::class.java)

        activity?.let {
            viewModel.dataBase = AppDatabase.getAppDatabase(it.applicationContext)
        }

        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        binding.setVariable(BR.model, viewModel)
        binding.lifecycleOwner = this

        return binding.root//inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.init()

        viewModel.products.observe(this, androidx.lifecycle.Observer {
            viewModel.updateWishList()

            listProducts.adapter = ProductsAdapter(it, this)
        })

        viewModel.wishList.observe(this, androidx.lifecycle.Observer {
            wishListRecyclerView?.adapter = WishProductsAdapter(it as List<Product>, this)

        })
    }

    override fun onItemClick(product: Product) {
        val bundle = CatalogueDetailsActivity.createArguments(product)

        val intent = Intent(this.context, CatalogueDetailsActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun onWishItemClick(product: Product) {

    }
}
