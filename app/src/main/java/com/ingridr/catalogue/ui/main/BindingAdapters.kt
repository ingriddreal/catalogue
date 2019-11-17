package com.ingridr.catalogue.ui.main

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("setItemImage")
fun loadImage(view: ImageView, imageUrl: String?) {
    imageUrl?.let {
        val tets = it
        Glide.with(view.context)
            .load(it).apply(RequestOptions().circleCrop())
            .into(view)
    }

}