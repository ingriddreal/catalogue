package com.ingridr.catalogue.ui.main

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.ingridr.catalogue.R
import com.ingridr.catalogue.model.Product
import kotlinx.android.synthetic.main.main_fragment.*

class CatalogueListFragment : Fragment(), ProductsAdapter.OnItemClickListener {

    companion object {
        fun newInstance() = CatalogueListFragment()
    }

    private lateinit var viewModel: CatalogueListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CatalogueListViewModel::class.java)
        viewModel.init()
        viewModel.products.observe(this, androidx.lifecycle.Observer {
            listProducts.adapter = ProductsAdapter(it, this)
        })
        // TODO: Use the ViewModel
    }

    override fun onItemClick(product: Product) {
        val bundle = CatalogueDetailsActivity.createArguments(product)

        val intent = Intent(this.context, CatalogueDetailsActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
//        val catalogueDetailFragment = CatalogueDetailsFragment.newInstance()
//        catalogueDetailFragment.arguments = bundle
//
//        val transaction = fragmentManager?.beginTransaction()
//        transaction?.let {
//            it.add(R.id.wishListItems, catalogueDetailFragment)
//            it.addToBackStack(null)
//            it.commit()
//        }
    }
}
