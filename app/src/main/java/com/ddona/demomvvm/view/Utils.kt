package com.ddona.demomvvm.view

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ddona.demomvvm.R

object Utils {
    @JvmStatic
    @BindingAdapter("loadImage")
    open fun loadImage(iv: ImageView, imageId: Int) {
        iv.setImageResource(imageId)
    }
    @JvmStatic
    @BindingAdapter("loadImage")
    open fun loadImage(iv: ImageView, linkImage: String?) {
        if (linkImage == null || linkImage.equals("")){
            iv.setImageResource(R.drawable.aodai)
        }else {
            Glide.with(iv)
                .load(linkImage)
                .placeholder(R.drawable.aodai)
                .error(R.drawable.aodai)
                .into(iv)
        }
    }


    @JvmStatic
    @BindingAdapter("setText")
    open fun setText(tv: TextView, text: String?) {
        tv.setText(text)
    }

}