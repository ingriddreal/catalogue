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

class WishProductsAdapter(
    private val products: List<Product>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<WishProductsAdapter.ProductsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.wish_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val product = products[position]

        val options = RequestOptions()
            .placeholder(R.mipmap.ic_launcher_round)
            .error(R.mipmap.ic_launcher_round)
        Glide.with(holder.itemView.context).load(product?.image).apply(options)
            .into(holder.image)

        holder.productName.text = product.title
        holder.productDescription.text = product.description
        holder.priceText.text = ("S" + product.price.toString())
        product.colors?.let {
            it.forEachIndexed { index, color ->
                if (index == 0) {
                    holder.colorView1.visibility = View.VISIBLE
                    holder.colorView1.setBackgroundColor(color.getColor())
                } else if (index == 1) {
                    holder.colorView2.visibility = View.VISIBLE
                    holder.colorView2.setBackgroundColor(color.getColor())
                } else {
                    holder.colorView3.visibility = View.VISIBLE
                    holder.colorView3.setBackgroundColor(color.getColor())
                }
            }
        }
        holder.bind(product, listener)
    }

    interface OnItemClickListener {
        fun onWishItemClick(product: Product)
    }

    inner class ProductsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val productName: TextView = itemView.findViewById(R.id.itemName)
        var productDescription: TextView = itemView.findViewById(R.id.itemDescription)
        var priceText: TextView = itemView.findViewById(R.id.priceText)

        var colorView1: View = itemView.findViewById(R.id.firstColour)
        var colorView2: View = itemView.findViewById(R.id.secondColour)
        var colorView3: View = itemView.findViewById(R.id.thirdColour)
        val image: ImageView = itemView.findViewById(R.id.imageItem)

        fun bind(product: Product, listener: OnItemClickListener) {
            itemView.setOnClickListener {
                listener.onWishItemClick(product)
            }
        }
    }
}