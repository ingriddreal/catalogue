package com.ingridr.catalogue.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ingridr.catalogue.R
import com.ingridr.catalogue.model.Product

enum class ProductType {
    CATALOGUE,
    WISHLIST
}
class ProductsAdapter(
    private val products: List<Product>,
    private val listener: OnItemClickListener,
    private val productType: ProductType = ProductType.CATALOGUE
) :
    RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return when(productType) {
            ProductType.CATALOGUE->
                ProductsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.catalogue_item, parent, false))

            ProductType.WISHLIST ->
                ProductsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.wish_list_item, parent, false))
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val product = products[position]

        val options = RequestOptions()
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher_round)
            .error(R.mipmap.ic_launcher_round)
        Glide.with(holder.itemView.context).load(product?.image).apply(options)
            .into(holder.image)

        holder.productName.text = product.title
        holder.bind(product, listener)
    }

    interface OnItemClickListener {
        fun onItemClick(product: Product)
    }

    inner class ProductsViewHolder(itemView: View, private val productType: ProductType = ProductType.CATALOGUE) : RecyclerView.ViewHolder(itemView) {

        val productName: TextView = itemView.findViewById(R.id.itemName)
        var productDescription: TextView? = null
        var colorView1: View? = null
        var colorView2:View? = null
        var colorView3: View? = null
        val image: ImageView = itemView.findViewById(R.id.itemImage)

        fun bind(product: Product, listener: OnItemClickListener) {
            itemView.setOnClickListener {
                listener.onItemClick(product)
            }
        }
    }
}