package com.john.ui_components.utils

import android.widget.ImageView
import com.john.ui_component.R
import com.squareup.picasso.Picasso

fun ImageView.renderImageFromUrl(url : String){
    Picasso.get()
        .load(url)
        .error(R.drawable.ic_no_photo)
        .into(this)
}