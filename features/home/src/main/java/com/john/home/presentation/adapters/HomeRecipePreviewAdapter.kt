package com.john.home.presentation.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.john.home.domain.HomeRecipePreview
import com.john.ui_components.BaseViewHolder
import android.view.LayoutInflater
import com.john.home.databinding.HomeRecipeItemLayoutBinding
import com.john.ui_components.utils.renderImageFromUrl

class HomeRecipePreviewAdapter(
    private val onReceiptClicked: (id: Long) -> Unit
) : ListAdapter<HomeRecipePreview, BaseViewHolder<*>>(ReceiptQueryDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return ReceiptPreviewViewHolder(
            HomeRecipeItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        (holder as ReceiptPreviewViewHolder).bind(getItem(position), position)
    }

    inner class ReceiptPreviewViewHolder(private val binding: HomeRecipeItemLayoutBinding) :
        BaseViewHolder<HomeRecipePreview>(binding.root) {

        override fun bind(item: HomeRecipePreview, position: Int) {
            binding.titleTextView.text = item.name
            binding.imageView.renderImageFromUrl(item.image)
            binding.root.setOnClickListener { onReceiptClicked(item.id) }
        }
    }

    private object ReceiptQueryDiff : DiffUtil.ItemCallback<HomeRecipePreview>() {
        override fun areItemsTheSame(
            oldItem: HomeRecipePreview,
            newItem: HomeRecipePreview
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: HomeRecipePreview,
            newItem: HomeRecipePreview
        ): Boolean {
            return oldItem == newItem
        }
    }
}