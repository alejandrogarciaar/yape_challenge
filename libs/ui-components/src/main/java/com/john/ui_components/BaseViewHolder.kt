package com.john.ui_components

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView.rootView) {
    abstract fun bind(item: T, position: Int)
}